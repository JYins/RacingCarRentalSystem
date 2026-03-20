<template>
  <div class="crumb-shell">
    <p class="crumb-label">Navigation</p>
    <el-breadcrumb class="app-breadcrumb" separator="/">
      <el-breadcrumb-item
        v-for="(item, index) in levelList"
        :key="`${item.path}-${index}`"
      >
        <span
          v-if="item.redirect === 'noRedirect' || index === levelList.length - 1"
          class="current-item"
        >
          {{ item.label }}
        </span>
        <a v-else @click.prevent="handleLink(item)">
          {{ item.label }}
        </a>
      </el-breadcrumb-item>
    </el-breadcrumb>
  </div>
</template>

<script>
export default {
  data() {
    return {
      levelList: []
    }
  },
  watch: {
    $route() {
      this.getBreadcrumb()
    }
  },
  created() {
    this.getBreadcrumb()
  },
  methods: {
    getBreadcrumb() {
      const matched = this.$route.matched.filter(item => item.meta || item.name)
      const breadcrumbs = [
        {
          path: '/index',
          label: 'Console'
        }
      ]

      matched.forEach(item => {
        const label = item.meta && item.meta.title
          ? item.meta.title
          : item.name

        if (!label || label === 'Console') {
          return
        }

        breadcrumbs.push({
          ...item,
          label
        })
      })

      this.levelList = breadcrumbs
    },
    handleLink(item) {
      const target = item.redirect || item.path
      this.$router.push(target)
    }
  }
}
</script>

<style lang="scss" scoped>
.crumb-shell {
  padding: 16px 18px;
  border-radius: 18px;
  background: rgba(255, 255, 255, 0.92);
  border: 1px solid rgba(148, 163, 184, 0.14);
  box-shadow: 0 12px 30px rgba(15, 23, 42, 0.05);
}

.crumb-label {
  margin: 0 0 8px;
  color: #64748b;
  font-size: 12px;
  font-weight: 700;
  letter-spacing: 0.08em;
  text-transform: uppercase;
}

.app-breadcrumb {
  font-size: 14px;
  line-height: 1.4;

  ::v-deep .el-breadcrumb__separator {
    color: #94a3b8;
  }

  ::v-deep .el-breadcrumb__inner a {
    color: #475569;
    font-weight: 600;
  }
}

.current-item {
  color: #0f172a;
  font-weight: 700;
}
</style>
