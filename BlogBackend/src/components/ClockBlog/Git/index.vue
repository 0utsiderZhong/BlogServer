<template>
  <div>
    <svg-icon icon-class="github" @click="goto"/>
  </div>
</template>

<script>
  export default {
    name: 'ClockBlogGit',
    data() {
      return {
        url: 'https://github.com/0utsiderZhong'
      }
    },
    methods: {
      goto() {
        window.open(this.url)
      }
    }
  }
</script>
