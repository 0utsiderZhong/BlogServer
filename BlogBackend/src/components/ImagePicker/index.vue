<template>
  <div>
    <el-tabs tab-position="left">
      <el-tab-pane label="本地上传">
<!--        action="https://jsonplaceholder.typicode.com/posts/"-->
        <el-upload
          class="avatar-uploader"
          action="https://httpbin.org/post"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload">
          <img v-if="imageUrl" :src="imageUrl" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  export default {
    name: "ImagePicker",
    data() {
      return {
        imageBase64: '',
        imageUrl: ''
      }
    },
    created() {
    },
    methods: {
      onImgSelect(url) {
        this.$emit('onImgSelect', url);
      },
      handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
        this.imageBase64 = res.files.file;
        return this.imageBase64;
      },
      beforeAvatarUpload(file) {
        const isJPG = (file.type === 'image/jpeg') || (file.type === 'image/png');
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG/PNG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      }
    }
  }
</script>

<style scoped>
  .selectImg {
    /*filter: blur(1px);*/
    border-radius: 4px !important;
    border: 1px solid #67C23A !important;
    background-color: #DCDFE6 !important;
    overflow: hidden !important;
    color: #409EFF !important;
  }

  .selectImg .el-card__body {
    background-color: cornflowerblue;
  }

  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    height: 100px;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 100%;
    height: 224px;
    line-height: 224px;
    text-align: center;
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
