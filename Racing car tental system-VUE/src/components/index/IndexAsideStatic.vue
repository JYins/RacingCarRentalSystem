<template>
  <el-aside class="console-aside" width="280px">
    <div class="aside-inner">
      <div class="workspace-card">
        <p class="workspace-label">{{ role === 'Admin' ? 'Admin workspace' : 'Customer workspace' }}</p>
        <strong>{{ role === 'Admin' ? 'Operations & analytics' : 'Bookings & returns' }}</strong>
      </div>

      <el-menu
        v-if="activeMenu"
        :default-active="activePath"
        class="nav-menu"
        background-color="transparent"
        text-color="#475569"
        active-text-color="#0f172a"
        :unique-opened="true"
      >
        <el-menu-item index="/" @click="menuHandler('')">
          <i class="iconfont icon-r-home"></i>
          <span>Overview</span>
        </el-menu-item>

        <el-submenu index="profile">
          <template slot="title">
            <i class="iconfont icon-r-user2"></i>
            <span>Profile</span>
          </template>
          <el-menu-item index="/updatePassword" @click="menuHandler('updatePassword')">
            <i class="iconfont icon-r-lock"></i>
            <span>Change Password</span>
          </el-menu-item>
          <el-menu-item index="/center" @click="menuHandler('center')">
            <i class="iconfont icon-r-edit"></i>
            <span>{{ role === 'User' ? 'Profile Details' : 'Account Details' }}</span>
          </el-menu-item>
        </el-submenu>

        <el-submenu
          v-for="(group, groupIndex) in activeMenu.backMenu"
          :key="group.menu"
          :index="`group-${groupIndex}`"
        >
          <template slot="title">
            <i :class="group.icon"></i>
            <span>{{ group.menu }}</span>
          </template>

          <el-menu-item
            v-for="(entry, entryIndex) in group.child"
            :key="`${group.menu}-${entryIndex}`"
            :index="`/${entry.tableName}`"
            @click="menuHandler(entry.tableName)"
          >
            <i :class="entry.icon"></i>
            <span>{{ entry.menu }}</span>
          </el-menu-item>
        </el-submenu>
      </el-menu>
    </div>
  </el-aside>
</template>

<script>
import menu from '@/utils/menu'

export default {
  data() {
    return {
      menuList: [],
      role: ''
    }
  },
  computed: {
    activeMenu() {
      return this.menuList.find(item => item.roleName === this.role) || null
    },
    activePath() {
      return this.$route.path || '/'
    }
  },
  mounted() {
    this.menuList = menu.list()
    this.role = this.$storage.get('role')
  },
  methods: {
    menuHandler(name) {
      this.$router.push(`/${name}`)
    }
  }
}
</script>

<style lang="scss" scoped>
.console-aside {
  position: fixed;
  top: 80px;
  left: 0;
  bottom: 0;
  padding: 22px 16px 24px;
  background: linear-gradient(180deg, #eef2f7 0%, #f8fafc 100%);
  border-right: 1px solid rgba(148, 163, 184, 0.18);
}

.aside-inner {
  height: 100%;
  overflow-y: auto;
  padding-right: 4px;
}

.workspace-card {
  padding: 18px 18px 16px;
  margin-bottom: 14px;
  border-radius: 20px;
  background: linear-gradient(135deg, #0f172a 0%, #1e293b 100%);
  color: #f8fafc;

  strong {
    display: block;
    font-size: 18px;
    line-height: 1.4;
  }
}

.workspace-label {
  margin: 0 0 6px;
  color: #cbd5e1;
  font-size: 12px;
  letter-spacing: 0.08em;
  text-transform: uppercase;
}

.nav-menu {
  border: 0;

  ::v-deep .el-menu-item,
  ::v-deep .el-submenu__title {
    height: 48px;
    line-height: 48px;
    margin-bottom: 6px;
    border-radius: 14px;
    font-weight: 600;
  }

  ::v-deep .el-menu-item.is-active,
  ::v-deep .el-submenu__title:hover,
  ::v-deep .el-menu-item:hover {
    background: rgba(15, 118, 110, 0.12) !important;
  }

  ::v-deep .el-submenu .el-menu-item {
    min-width: 100%;
    margin-left: 8px;
    width: calc(100% - 8px);
  }

  i {
    width: 22px;
    margin-right: 10px;
    color: #0f766e;
    text-align: center;
  }
}

@media (max-width: 980px) {
  .console-aside {
    width: 240px !important;
  }
}

@media (max-width: 760px) {
  .console-aside {
    display: none;
  }
}
</style>
