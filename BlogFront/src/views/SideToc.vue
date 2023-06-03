<template>
  <div class="side-toc hide-scrollbar">
    <panel title="Contents">
      <div id="side-toc" class="list" slot="content" ref="list">
      </div>
    </panel>
  </div>
</template>

<script type="text/ecmascript-6">
import Panel from "./Panel";

export default {
  name: 'SideToc',
  components: {
    'panel': Panel
  }
};
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
@import "../common/stylus/theme.styl";
@import "../common/stylus/toc.styl";
.side-toc
  position relative
  background #fff
  max-height 90vh
  overflow scroll
  h4
    font-size 30px
    line-height 30px
    text-align left

  .list
    color #1482f0
    font-size 15px
    position relative
</style>
