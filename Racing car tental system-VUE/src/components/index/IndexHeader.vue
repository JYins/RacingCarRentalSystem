<template>
  <header class="app-header">
    <div class="brand-block">
      <img src="@/assets/img/04.png" alt="Project logo" class="brand-logo" />
      <div>
        <p class="brand-label">Management Console</p>
        <div class="brand-name">Racing Car Rental</div>
      </div>
    </div>

    <div class="header-actions">
      <div class="account-chip">
        <img
          :src="role === 'Admin' ? '/avatar1.jpg' : '/avatar2.jpg'"
          alt="Account avatar"
          class="account-avatar"
        />
        <div>
          <div class="account-role">{{ role }}</div>
          <div class="account-name">{{ displayName }}</div>
        </div>
      </div>

      <button class="logout-button" type="button" @click="onLogout">
        Sign out
      </button>
    </div>
  </header>
</template>

<script>
export default {
  data() {
    return {
      user: {}
    }
  },
  computed: {
    role() {
      return this.$storage.get('role') || 'User'
    },
    displayName() {
      return this.user.username || this.$storage.get('adminName') || 'Guest'
    }
  },
  mounted() {
    const sessionTable = this.$storage.get('sessionTable')
    if (!sessionTable) {
      return
    }

    this.$http({
      url: `${sessionTable}/session`,
      method: 'get'
    }).then(({ data }) => {
      if (data && data.code === 0) {
        this.user = data.data || {}
        return
      }

      this.$message.error(data.msg)
    }).catch(error => {
      if (
        error.response &&
        error.response.data &&
        `${error.response.data}`.includes('Could not proxy request')
      ) {
        this.$message({
          showClose: true,
          message: error.response.data,
          type: 'error',
          duration: 12000
        })
      }
    })
  },
  methods: {
    onLogout() {
      this.$storage.clear()
      this.$router.replace({ name: 'login' })
    }
  }
}
</script>

<style lang="scss" scoped>
.app-header {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 200;
  height: 80px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 28px 0 24px;
  background: rgba(15, 23, 42, 0.95);
  box-shadow: 0 14px 30px rgba(15, 23, 42, 0.16);
  backdrop-filter: blur(12px);
}

.brand-block {
  display: flex;
  align-items: center;
  gap: 14px;
}

.brand-logo {
  width: 44px;
  height: 44px;
  object-fit: contain;
}

.brand-label {
  margin: 0 0 2px;
  color: #94a3b8;
  font-size: 12px;
  font-weight: 700;
  letter-spacing: 0.12em;
  text-transform: uppercase;
}

.brand-name {
  color: #f8fafc;
  font-size: 28px;
  font-weight: 700;
  line-height: 1;
}

.header-actions {
  display: flex;
  align-items: center;
  gap: 16px;
}

.account-chip {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 8px 12px 8px 8px;
  border-radius: 999px;
  background: rgba(255, 255, 255, 0.08);
}

.account-avatar {
  width: 48px;
  height: 48px;
  border-radius: 50%;
  object-fit: cover;
}

.account-role {
  color: #cbd5e1;
  font-size: 12px;
  text-transform: uppercase;
  letter-spacing: 0.08em;
}

.account-name {
  color: #f8fafc;
  font-size: 15px;
  font-weight: 600;
}

.logout-button {
  height: 42px;
  padding: 0 18px;
  border: 0;
  border-radius: 999px;
  background: linear-gradient(135deg, #ea580c 0%, #c2410c 100%);
  color: #fff;
  font-weight: 700;
  cursor: pointer;
}

.logout-button:hover {
  filter: brightness(1.05);
}

@media (max-width: 760px) {
  .app-header {
    padding: 0 16px;
  }

  .brand-name {
    font-size: 22px;
  }

  .account-chip {
    display: none;
  }
}
</style>
