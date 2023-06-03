<template>
  <div class="about">
    <img class="background" alt="avatar" src=./../assets/background.jpg>
    <div class="name" style="text-align: center">ClockZhong</div>
    <div class="desc" style="text-align: center">
      一个啥也不会的肤浅开发工程师
    </div>
    <div style="padding-top: 10px"></div>
    <el-link :underline=false style="padding-left: 100px">
      <img src="../assets/email.svg" height="30px" @click="click('email')"/>
    </el-link>
    <el-link :underline=false style="padding-left: 50px">
      <img src="../assets/github.svg" height="30px" @click="click('github')"/>
    </el-link>
    <div style="padding-top: 10px;text-align: center">
      <a href="/about" style="font-size: 25px">ABOUT ME</a>
    </div>
    <el-divider></el-divider>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      github: 'https://github.com/0utsiderZhong',
      email: 'https://mail.google.com/mail/u/0/#inbox'
    }
  },
  name: 'About',

  methods: {
    click(request) {
      if (request === 'github') {
        window.open(this.github)
      } else if (request === 'email') {
        window.open(this.email)
      }
    },
  },
};
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
@import "../common/stylus/theme.styl";
.about
  position relative
  //text-align center
  padding-bottom 20px
  margin-top 0
  background-color $default-background-color

  img.background
    text-align center
    position relative
    top 0
    left 10%
    width 80%
    height 300px
    border-radius 1%

  .name
    font-size 22px
    color $default-title-color
    line-height 30px
    font-weight 700

  .desc
    font-size 18px
    color $default-desc-color
    line-height 30px
    font-weight 100
</style>
