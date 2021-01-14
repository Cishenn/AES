<template>
  <div class="personalinfo">
    <div class="mainarea">
      <div class="Settinginfo" align="center">
        <el-tooltip effect="light" content="查看当前审核进度" >
          <el-button icon="el-icon-view" circle @click="checkStatus" class="checkBtn" />
        </el-tooltip>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="上传头像" prop="avatar">
            <el-upload
              class="avatar-uploader"
              action="#"
              :http-request="uploadBehavior"
              :show-file-list="false"
              list-type="picture-card"
              :multiple="false"
              :before-upload="beforeAvatarUpload"
              :auto-upload="true">
              <img v-if="imageUrl" :src="imageUrl" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
          <el-form-item label="姓名" prop="name">
            <el-input v-model="form.name" placeholder="请输入姓名"></el-input>
          </el-form-item>
          <el-form-item label="年龄" prop="age">
            <el-input v-model="form.age" placeholder="请输入年龄"></el-input>
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
          <el-form-item label="科目" prop="subject">
            <el-input v-model="form.subject" placeholder="请输入科目"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="save">保存</el-button>
            <el-button type="primary" @click="checkCard">查看监考证</el-button>
          </el-form-item>
        </el-form>
      </div>
      <el-dialog title="监考信息" :visible.sync="dialogFormVisible" width="40%">
        <div class="dialogForm" id="dialogInfo">
          <div class="cardTitle">{{cardTitle}}</div>
          <img :src="imageUrl" crossorigin="anonymous" class="dialogAvatar">
          <div class="cardLine">
            <span class="cardLabel">姓名</span>
            <span class="cardValue"> {{inspectCard.name}}</span>
          </div>
          <div class="cardLine">
            <span class="cardLabel">考点</span>
            <span class="cardValue"> {{inspectCard.school}}</span>
          </div>
          <div class="cardLine">
            <span class="cardLabel">类型</span>
            <span class="cardValue"> {{inspectCard.type}}</span>
          </div>
          <div class="cardLine">
            <span class="cardLabel">组别</span>
            <span class="cardValue"> {{inspectCard.groupId}}</span>
          </div>
        </div>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="exportPdf">导 出</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import COS from 'cos-js-sdk-v5'
import htmlToPdf from '../../../public/htmlToPdf'

export default {
  data () {
    return {
      avatarFile: '',
      form: {
        name: '',
        age: '',
        phone: '',
        gender: '',
        school: '',
        grade: '',
        subject: ''
      },
      rules: {
        // avatar: [
        //   { required: true, message: '请上传头像', trigger: 'blur' }
        // ],
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' }
        ],
        age: [
          { required: true, message: '请输入年龄', trigger: 'blur' }
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
        ],
        subject: [
          { required: true, message: '请输入科目', trigger: 'blur' }
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
      imageUrl: '',
      personInfo: {
        esId: '',
        name: '',
        age: '',
        telephoneNumber: '',
        sex: '',
        schoolId: '',
        grade: '',
        subject: ''
      },
      changeAvatar: false,
      inspectCard: {
        name: '',
        school: '',
        type: '',
        groupId: ''
      },
      cardTitle: '',
      dialogFormVisible: false,
      formLabelWidth: '70px'
    }
  },
  created () {
    if (this.$store.state.teacherId === '') {
      this.$router.push('/login')
    }
    this.personInfo.esId = this.$store.getters.getTeacherId
    this.imageUrl = `https://avatar-1301419632.cos.ap-nanjing.myqcloud.com/avatar/${this.personInfo.esId}.jpg`
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
          this.form.age = resp.data.age
          this.form.phone = resp.data.telephoneNumber
          this.form.gender = resp.data.sex
          this.form.school = resp.data.schoolId
          this.form.grade = resp.data.grade
          this.form.subject = resp.data.subject
          this.updatePersonalInfo()
        })
    },
    save () {
      this.$refs.form.validate((valid) => {
        if (valid) {
          if (this.hasSomeChanges() || this.changeAvatar) {
            this.updatePersonalInfo()
            this.saveChanges()
            this.uploadAvatar()
            this.changeAvatar = false
            this.$message.success('修改信息成功')
          } else {
            this.$message.info('您没有修改任何信息')
          }
        } else {
          this.$message.error('您的表单尚未完成')
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
      this.personInfo.age !== this.form.age ||
        this.personInfo.telephoneNumber !== this.form.phone ||
        this.personInfo.sex !== this.form.gender ||
        this.personInfo.schoolId !== this.form.school ||
        this.personInfo.grade !== this.form.grade ||
        this.personInfo.subject !== this.form.subject) {
        return true
      }
      return false
    },
    updatePersonalInfo () {
      this.personInfo.name = this.form.name
      this.personInfo.age = this.form.age
      this.personInfo.telephoneNumber = this.form.phone
      this.personInfo.sex = this.form.gender
      this.personInfo.schoolId = this.form.school
      this.personInfo.grade = this.form.grade
      this.personInfo.subject = this.form.subject
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
    beforeAvatarUpload (file) {
      const isJPG = file.type === 'image/jpeg'
      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      return isJPG
    },
    uploadBehavior (data) {
      this.avatarFile = data.file
      this.imageUrl = URL.createObjectURL(this.avatarFile)
      this.changeAvatar = true
    },
    uploadAvatar () {
      const cos = new COS({
        SecretId: 'AKIDjFcm9tmYE8RXO0znJ22qOjpBLM8g58ZF',
        SecretKey: 'weB5HlqNhkZXWOdfdOvDH0WbS6nJm9GR'
      })
      cos.putObject({
        Bucket: 'avatar-1301419632',
        Region: 'ap-nanjing',
        Key: `avatar/${this.personInfo.esId}.jpg`,
        StorageClass: 'STANDARD',
        Body: this.avatarFile,
        onProgress: function (progressData) {
          console.log(JSON.stringify(progressData))
        }
      },
      function (err, data) {
        console.log(err || data)
        console.log(data.Location)
      })
    },
    checkCard () {
      this.$axios
        .get('qualification/qualification', {
          params: {
            esId: this.personInfo.esId
          }
        })
        .then(resp => {
          console.log(this.personInfo.esId)
          console.log(resp)
          if (resp.data.type === 0) {
            this.$message.warning('当前未被审核或未排考！')
          } else {
            this.inspectCard.name = resp.data.name
            this.inspectCard.school = resp.data.school
            this.inspectCard.type = resp.data.type === 1 ? '巡考组' : '监考组'
            this.cardTitle = resp.data.type === 1 ? '巡考证' : '监考证'
            this.inspectCard.groupId = resp.data.groupId
            console.log(this.inspectCard)
            this.dialogFormVisible = true
          }
        })
    },
    exportPdf () {
      this.dialogFormVisible = false
      htmlToPdf.downloadPDF(document.querySelector('#dialogInfo'), `${this.personInfo.name}的${this.cardTitle}`)
    }
  }
}

</script>
<style scoped>
.personalinfo {
    background-color: #e4eeff;
    width: 89.7%;
    height: 91.5%;
    margin-left: -150px;
    margin-top: 3%;
}
.mainarea{
  background-color: #FFFFFF;
  width: 95%;
  height: 96%;
  margin-top: 1%;
  margin-left: 2.5%;
}
.checkBtn {
  float: right;
  margin-right: -80px;
  z-index: 999;
}

.title-box {
  font-size: 20px;
  margin-bottom: 20px;
}

.Settinginfo {
  margin-top: 2%;
  margin-left: 30%;
  position: fixed;
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

.dialogForm {
  padding-left: 20%;
  padding-right: 20%;
}

.dialogAvatar {
  width: 146px;
  height: 146px;
  display: block;
  margin: auto;
  margin-bottom: 5%;
}

.cardTitle {
  font-size: 35px;
  text-align: center;
  padding-top: 10%;
  padding-bottom: 3%;
}

.cardLine {
  margin-top: 8%;
  margin-bottom: 8%;
  margin-left: 20%;
}

.cardLabel {
  font-size: 24px;
}

.cardValue {
  font-size: 26px;
}
</style>
