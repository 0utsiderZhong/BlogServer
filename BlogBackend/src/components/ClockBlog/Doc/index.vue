<template>
  <div>
    <svg-icon icon-class="skill" @click="goto"/>
  </div>
</template>

<script>
  export default {
    name: 'ClockBlogDoc',
    data() {
      return {
        url: 'https://www.google.com'
      }
    },
    methods: {
      goto() {
        window.open(this.url)
      }
    }
  }
</script>
