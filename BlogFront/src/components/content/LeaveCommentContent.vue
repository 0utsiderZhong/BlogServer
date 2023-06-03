<template>
  <div class="link-content layout-content">
    <Row>
      <Column :xs="24" :sm="24" :md="24" :lg="17" :xl="17">
        <div class="layout-left">
          <Divider orientation="left">
            <div class="header" style=";">
              <Icon type="ios-at"/>
              <h4 style="font-weight: bold"> &nbsp;&nbsp;ClockBlog welcome your message</h4>
            </div>
          </Divider>
          <SocialSection
              :page-id="-1000"
              :allowComment="true"
              :commentList="list"/>
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
import Recommend from "../../views/Recommend";
import Hot from "../../views/Hot";
import SocialSection from "../../views/comment/SocialSection";
import {listComment} from "@/api"

export default {
  name: "LinkContent",
  data() {
    return {
      list: [],
      pageId: -1000
    }
  },
  created() {
    listComment(this.pageId).then(resposne => {
      this.list = resposne.data;
    });
  },
  components: {
    Recommend, Hot, SocialSection
  },
}
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
</style>
