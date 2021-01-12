<template>
  <div class="box">
    <div class="title-box">个人信息
      <el-tooltip effect="light" content="查看当前审核进度">
        <el-button icon="el-icon-view" circle @click="checkStatus" class="checkBtn" />
      </el-tooltip>
    </div>
    <div class="Settinginfo">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="上传头像" prop="avatar">
          <el-upload
            class="avatar-uploader"
            action="https://jsonplaceholder.typicode.com/posts/"
            :show-file-list="false"
            list-type="picture-card"
            multiple="false"
            :before-upload="beforeAvatarUpload"
            :on-success="handleAvatarSuccess"
            :auto-upload="true">
            <img v-if="personInfo.imageUrl" :src="personInfo.imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入号码"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-select v-model="form.gender" placeholder="请选择SEX" style="width:310px">
            <el-option label="男" value="男"></el-option>
            <el-option label="女" value="女"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="学校" prop="school">
          <el-select v-model="form.school" placeholder="请选择学校" style="width:310px">
            <el-option
              v-for="item in schools"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="年级" prop="grade">
          <el-select v-model="form.grade" placeholder="请选择年级" style="width:310px">
            <el-option
              v-for="item in grades"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="save">保存</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      form: {
        name: '',
        phone: '',
        gender: '',
        school: '',
        grade: ''
      },
      rules: {
        // avatar: [
        //   { required: true, message: '请上传头像', trigger: 'blur' }
        // ],
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入号码', trigger: 'blur' },
          { min: 11, max: 11, message: '请写正确号码', trigger: 'blur' }
        ],
        gender: [
          { required: true, message: '请输入性别', trigger: 'blur' }
        ],
        school: [
          { required: true, message: '请输入学校', trigger: 'blur' }
        ],
        grade: [
          { required: true, message: '请输入年级', trigger: 'blur' }
        ]
      },
      grades: [
        {
          value: '高一',
          label: '高一'
        },
        {
          value: '高二',
          label: '高二'
        },
        {
          value: '高三',
          label: '高三'
        }
      ],
      schools: [],
      personInfo: {
        imageUrl: '',
        esId: '',
        name: '',
        telephoneNumber: '',
        sex: '',
        schoolId: '',
        grade: ''
      }
    }
  },
  created () {
    if (this.$store.state.teacherId === '') {
      this.$router.push('/login')
    }
    this.personInfo.esId = this.$store.getters.getTeacherId
    this.personInfo.imageUrl = `https://avatar-1301419632.cos.ap-nanjing.myqcloud.com/avatar/${this.personInfo.esId}.jpg`
    this.getSchools()
    this.getPersonalInfo()
  },

  methods: {
    getSchools () {
      this.$axios
        .get('school/school')
        .then(resp => {
          const schoolArray = resp.data.School
          for (const schoolItem of schoolArray) {
            const tempSchool = {
              value: schoolItem.schoolId,
              label: schoolItem.schoolName
            }
            this.schools.push(tempSchool)
          }
        })
    },
    getPersonalInfo () {
      this.$axios
        .get('exStaff/exStaff/exStaffId', {
          params: {
            esId: this.personInfo.esId
          }
        })
        .then(resp => {
          this.form.name = resp.data.name
          this.form.phone = resp.data.telephoneNumber
          this.form.gender = resp.data.sex
          this.form.school = resp.data.schoolId
          this.form.grade = resp.data.grade
          this.updatePersonalInfo()
        })
    },
    save () {
      this.$refs.form.validate((valid) => {
        if (valid) {
          if (this.hasSomeChanges()) {
            this.updatePersonalInfo()
            this.saveChanges()
            this.$message({
              type: 'success',
              message: '修改信息成功'
            })
          } else {
            this.$message({
              type: 'info',
              message: '您没有修改任何信息'
            })
          }
        } else {
          this.$message({
            type: 'error',
            message: '您的表单尚未完成'
          })
          return false
        }
      })
    },
    saveChanges () {
      this.$axios
        .post('exStaff/update', JSON.stringify(this.personInfo), {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          }
        })
    },
    hasSomeChanges () {
      if (this.personInfo.name !== this.form.name ||
        this.personInfo.telephoneNumber !== this.form.phone ||
        this.personInfo.sex !== this.form.gender ||
        this.personInfo.schoolId !== this.form.school ||
        this.personInfo.grade !== this.form.grade) {
        return true
      }
      return false
    },
    updatePersonalInfo () {
      this.personInfo.name = this.form.name
      this.personInfo.telephoneNumber = this.form.phone
      this.personInfo.sex = this.form.gender
      this.personInfo.schoolId = this.form.school
      this.personInfo.grade = this.form.grade
    },
    checkStatus () {
      this.$axios
        .get('exStaff/exStaff/exStaffId', {
          params: {
            esId: this.personInfo.esId
          }
        })
        .then(resp => {
          const checkCode = resp.data.schoolExamine
          const desc = resp.data.finalRejection
          const msg = this.getStatusInfo(checkCode, desc)
          this.$notify({
            title: '当前审核进度',
            message: msg,
            duration: 3000
          })
        })
    },
    getStatusInfo (checkCode, desc) {
      let msg = ''
      switch (checkCode) {
        case 2:
          msg = '已通过审核'
          break
        case 1:
          msg = '信息审核中'
          break
        case 0:
          msg = '信息未提交'
          break
        case -1:
          msg = '提交被拒绝' + '\n' + desc
          break
        default:
          msg = '出错了！'
          break
      }
      return msg
    },
    handleAvatarSuccess (res, file) {
      this.personInfo.imageUrl = URL.createObjectURL(file.raw)
      this.$message.success('头像已成功上传！')
    },
    beforeAvatarUpload (file) {
      const isJPG = file.type === 'image/jpeg'
      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      return isJPG
    }
  }
}

</script>
<style scoped>
.box {
  border-radius: 5px;
  padding: 20px;
}

.checkBtn {
  float: right;
}

.title-box {
  font-size: 20px;
  margin-bottom: 20px;
}

.Settinginfo {
  margin-left: 60px;
}

.upload-image {
  display: flex;
  align-items: center;
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 148px;
  height: 148px;
  line-height: 148px;
  text-align: center;
}

.avatar {
  width: 146px;
  height: 146px;
  display: block;
}
</style>
