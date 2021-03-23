<template>
  <div>
    <h1>您好，欢迎进入控制台！</h1>
  </div>
</template>

<script>
export default {
  name: 'welcome',
  mounted: function() {
    //子组件welcome调用父组件admin的方法，可以使用$parent
    this.$parent.activeSideBar("welcome-sidebar");
  },
  methods: {

  }
}
</script>