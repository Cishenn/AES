<template>
  <div class="box">
    <div class="title-box">个人信息</div>
    <div class="Settinginfo">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="上传头像" prop="avatar">
          <div class="upload-image">
            <el-upload
              action="#"
              list-type="picture-card"
              :auto-upload="false">
                <i slot="default" class="el-icon-plus"></i>
                <div slot="file" slot-scope="{file}">
                  <img
                    class="el-upload-list__item-thumbnail"
                    :src="file.url" alt=""
                  >
                  <span class="el-upload-list__item-actions">
                    <span
                      class="el-upload-list__item-preview"
                      @click="handlePictureCardPreview(file)"
                    >
                      <i class="el-icon-zoom-in"></i>
                    </span>
                    <span
                      v-if="!disabled"
                      class="el-upload-list__item-delete"
                      @click="handleDownload(file)"
                    >
                      <i class="el-icon-download"></i>
                    </span>
                    <span
                      v-if="!disabled"
                      class="el-upload-list__item-delete"
                      @click="handleRemove(file)"
                    >
                      <i class="el-icon-delete"></i>
                    </span>
                  </span>
                </div>
            </el-upload>
          </div>
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
      // alert('不要随便乱进哦!')
      this.$router.push('/login')
    }
    this.personInfo.esId = this.$store.getters.getTeacherId
    console.log(this.personInfo.esId)
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
    }
  }
}

</script>
<style scoped>
.box{

  border-radius: 5px;
  padding: 20px;
}
.title-box{
  font-size: 20px;
  /* color: red; */
  margin-bottom: 20px;
}
.Settinginfo{
  margin-left: 60px;
}
.upload-image{
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
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
