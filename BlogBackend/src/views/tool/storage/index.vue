<template>
  <div class="app-container">
    <el-tabs v-model="activeName" style="padding-left: 8px;" @tab-click="tabClick">
      <el-tab-pane label="本地存储" name="first">
        <Local ref="local"/>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  import Local from './local/index'

  export default {
    name: 'Storage',
    components: { Local},
    data() {
      return {
        activeName: 'first'
      }
    },
    methods: {
      tabClick() {
        this.$refs.local.init()
      }
    }
  }
</script>

<style scoped>
</style>
