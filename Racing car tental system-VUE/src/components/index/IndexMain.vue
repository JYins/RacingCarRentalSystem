<template>
  <el-main class="console-main">
    <bread-crumbs :title="title" class="bread-crumbs" />
    <router-view class="view-shell" />
  </el-main>
</template>

<script>
export default {
  computed: {
    title() {
      return this.$route.meta && this.$route.meta.title
        ? this.$route.meta.title
        : this.$route.name || ''
    }
  }
}
</script>

<style lang="scss" scoped>
.console-main {
  margin-top: 80px;
  margin-left: 280px;
  min-height: calc(100vh - 80px);
  padding: 18px 22px 22px;
  background: #eef2f7;
}

.bread-crumbs {
  margin-bottom: 14px;
}

.view-shell {
  min-height: calc(100vh - 164px);
}

@media (max-width: 980px) {
  .console-main {
    margin-left: 240px;
  }
}

@media (max-width: 760px) {
  .console-main {
    margin-left: 0;
    padding: 14px;
  }
}
</style>
