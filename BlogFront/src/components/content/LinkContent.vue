<template>
  <div class="link-content layout-content">
    <Row>
      <Column :xs="24" :sm="24" :md="24" :lg="17" :xl="17">
        <div class="layout-left">
          <Divider orientation="left">
            <div class="header" style=";">
              <Icon type="ios-at"/>
              <h4> &nbsp;&nbsp;Site Links</h4>
            </div>
          </Divider>
          <Row>
            <Column v-for="link in list" v-bind:key="link" :xs="8" :sm="8" :md="7" :lg="7" :xl="7">
              <a :href="link.url" style="color:#333;" @click="updateCount(link.id)">
                <Card style="height: 10rem;margin: 0.8rem 0.8rem 0.8rem 0">
                  <div slot="title">
                    <Avatar :src="link.headerImg"/>
                    <span class="link-title" :title="link.title" style="">{{ link.title }}</span>
                  </div>
                  <div class="link-desc" :title="link.description" style="">
                    {{ link.description }}
                  </div>
                </Card>
              </a>
            </Column>
          </Row>
          <Divider orientation="left">
            <div class="header" style=";">
              <Icon type="md-notifications"/>
              <h4> &nbsp;&nbsp;Description of application requirements</h4>
            </div>
          </Divider>
          <div class="desc">
            Your site needs to add a link to this site first, see below for the link information, this site will regularly scan all the links, please ensure that the site is properly accessed, the site automatic audit process is running, the latest will be within 24 hours to follow the link, thank you!<br>
            If there is no other reason after the application is submitted, it will be audited within 24 hours, if it is not passed after the time, please contact me through the home page email address.<br>
          </div>
          <Divider orientation="left">
            <div class="header" style=";">
              <Icon type="md-pin"/>
              <h4> &nbsp;&nbsp;Site Information</h4>
            </div>
          </Divider>
          <div class="desc">
            Website address: www.clockblog.life <br>
            Website Name: Clock's blog<br>
            Website Description: Record Learning, Record Life<br>
          </div>
          <Divider orientation="left">
            <div class="header" style=";">
              <Icon type="ios-create"/>
              <h4> &nbsp;&nbsp;Start applying</h4>
            </div>
          </Divider>
          <div style="width: 60%">
            <Form ref="form" :model="form" :rules="rules" :label-width="120">
              <FormItem prop="url" label="Website address">
                <InputComponent v-model="form.url" placeholder="Website address">
                  <Tooltip content="Enter the URL for automatic search" placement="top" slot="append">
                    <Button @click="searchSiteInfo()" icon="ios-search"></Button>
                  </Tooltip>
                </InputComponent>
              </FormItem>
              <FormItem prop="title" label="Website Name">
                <InputComponent v-model="form.title"
                                placeholder="Please enter the website name">
                </InputComponent>
              </FormItem>
              <FormItem prop="headerImg" label="Website icon address">
                <InputComponent v-model="form.headerImg"
                                placeholder="Website icon address">
                </InputComponent>
              </FormItem>
              <FormItem prop="description" label="Website Introduction">
                <InputComponent type="textarea" v-model="form.description"
                                placeholder="Website Introduction">
                </InputComponent>
              </FormItem>
              <FormItem prop="email" label="Webmaster Email">
                <InputComponent v-model="form.email"
                                placeholder="Webmaster Email">
                </InputComponent>
              </FormItem>
              <FormItem>
                <Button :type="buttonType" :loading="loading" @click="handleSubmit()">Submit your application</Button>
              </FormItem>
            </Form>
          </div>
        </div>
      </Column>
      <Column :xs="24" :sm="24" :md="24" :lg="7">
        <div class="layout-right">
          <Recommend style="margin-top:15px;"/>
          <Hot style="margin-top:15px;"/>
        </div>
      </Column>
    </Row>
  </div>
</template>

<script>
import {listFriendLinks, insertFriendLink, updateLinkCount} from "@/api"
import Recommend from "../../views/Recommend";
import Hot from "../../views/Hot";
import {mapState} from "vuex";

export default {
  name: "LinkContent",
  components: {
    Recommend, Hot
  },
  data() {
    return {
      list: [],
      form: {},
      loading: false,
      rules: {
        url: [
          {required: true, message: "邮箱地址不能为空", trigger: "blur"},
          {type: 'url', message: '请输入正确的网址', trigger: 'change'},
          {min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'change'}
        ],
        title: [
          {required: true, message: "网站名不能为空", trigger: "blur"},
          {min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'change'}
        ],
        headerImg: [
          {required: true, message: "网站图标不能为空", trigger: "blur"},
          {type: 'url', message: '请输入正确的网站图标', trigger: 'change'},
          {min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'change'}
        ],
        description: [
          {required: true, message: "描述不能为空", trigger: "blur"},
          {min: 1, max: 100, message: '长度在 1 到 500 个字符', trigger: 'change'}
        ],
        email: [
          {required: true, message: "邮箱不能为空", trigger: "blur"},
          {type: 'email', message: '请输入正确的邮箱地址', trigger: 'change'},
          {min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'change'}
        ]
      },
    }
  },
  computed: {
    ...mapState({
      siteTheme: state => state.base.siteTheme
    }),
    buttonType: function () {
      return this.siteTheme === 'dark' ? 'warning' : 'primary';
    },
  },
  created() {
    listFriendLinks().then(response => {
      this.list = response.data;
    });
  },
  methods: {
    updateCount(id) {
      updateLinkCount(id);
    },
    searchSiteInfo() {
      this.$Notice.warning({
        title: '网站信息查询失败,请手动输入',
      });
    },
    handleSubmit() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          this.loading = true;
          let obj = JSON.parse(JSON.stringify(this.form));
          insertFriendLink(obj).then(response => {
            if (response.code == 200) {
              this.$Notice.success({
                title: '申请成功,请等待系统自动审核!',
              });
              this.form = {};
            } else {
              this.$Notice.error({
                title: '申请失败',
              })
            }
            this.loading = false;
          });
        } else {
          this.$Notice.error({
            title: '校验失败',
          });
          this.loading = false;
        }
      });
    }
  }
}
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
@import "../../common/stylus/theme.styl";
.header
  display -webkit-box

.ivu-divider
  h4
    font-size 18px

body.dark
  .link-content
    .link-title, .link-desc
      color $dark-info-color

    .ivu-card-bordered, .ivu-card-head
      border 1px solid $dark-border-color

    .ivu-form-item-label
    .ivu-input
    .ivu-input-group-append
      background $dark-background-color
      color $dark-desc-color

    .ivu-card
      background-color $dark-cell-background-color

    .desc
      background $dark-background-color
      color $dark-info-color

    .ivu-divider
      color $dark-info-color

      &:before, &:after
        border-top 1px solid $dark-border-color!important

      h4
        color $dark-title-color

.ivu-card-body
  padding 7px !important


.link-title
  overflow hidden
  width 80%
  font-weight bold
  font-size 1.2rem
  display inline-block
  line-height inherit
  text-overflow ellipsis
  white-space nowrap

.link-desc
  /*padding-bottom 3px*/
  overflow hidden
  display inline
  text-overflow ellipsis
  display -webkit-box
  -webkit-box-orient vertical
  -webkit-line-clamp 3

</style>
