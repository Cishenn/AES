<!--  -->
<template>
  <div class="box">
    <div class="title-box">个人信息</div>
    <div class="Settinginfo">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="上传头像">
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
            <el-avatar style="margin-left:10px" shape="square" :size="150" :src="imageUrl"></el-avatar>
          </div>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="form.phone"></el-input>
        </el-form-item>
        <el-form-item label="性别" >
          <el-select v-model="form.gender" placeholder="请选择SEX" style="width:310px">
            <el-option label="男" value="Man"></el-option>
            <el-option label="女" value="Women"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="学校">
          <el-select v-model="value" placeholder="请选择学校" style="width:310px">
            <el-option
              v-for="item in Schools"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="年级">
          <el-select v-model="grade" placeholder="请选择年级" style="width:310px">
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
      esId: 1,
      value: '',
      grade: '',
      grades: [{
        value: 1,
        label: '高一'
      },
      {
        value: 2,
        label: '高二'
      },
      {
        value: 3,
        label: '高三'
      }],
      Schools: [{
        value: 1,
        label: '合工大'
      },
      {
        value: 2,
        label: '安徽大学'
      }],
      form: {
        name: 'Crilias',
        gender: 'Man',
        phone: '110'
      }
    }
  },
  created () {
    if (this.$store.state.teacherId === '') {
      // alert('不要随便乱进哦!')
      this.$router.push('/login')
    }
  },
  components: {},

  computed: {},

  mounted () {
    this.getinfo()
  },

  methods: {
    getinfo () {
      this.$axios.get('examStaff/examStaff', {
        params: {
          esid: this.esId
        }
      }).then(resp => {
        console.log(resp)
      })
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
