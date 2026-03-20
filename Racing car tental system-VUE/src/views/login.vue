<template>
  <div class="login-page">
    <div class="login-overlay"></div>

    <section class="login-shell">
      <div class="login-copy">
        <p class="eyebrow">Portfolio project</p>
        <h1>Racing car rental console</h1>
        <p class="summary">
          A full-stack management workspace for fleet operations, rental orders,
          customer records, and dashboard analytics.
        </p>

        <div class="highlight-list">
          <div class="highlight-card">
            <strong>20+</strong>
            <span>premium cars tracked in the fleet</span>
          </div>
          <div class="highlight-card">
            <strong>500+</strong>
            <span>seeded rental records for dashboard demos</span>
          </div>
          <div class="highlight-card">
            <strong>2 roles</strong>
            <span>separate admin and customer workflows</span>
          </div>
        </div>
      </div>

      <div class="login-card">
        <div class="card-header">
          <img src="@/assets/img/02.png" alt="Project mark" class="brand-mark" />
          <div>
            <p class="card-label">Sign in</p>
            <h2>Racing Car Rental</h2>
          </div>
        </div>

        <el-form @submit.native.prevent="login">
          <el-form-item label="Username">
            <el-input
              v-model="rulesForm.username"
              placeholder="Enter username"
              name="username"
              type="text"
            />
          </el-form-item>

          <el-form-item label="Password">
            <el-input
              v-model="rulesForm.password"
              placeholder="Enter password"
              name="password"
              type="password"
              show-password
            />
          </el-form-item>

          <el-form-item label="Role">
            <el-select v-model="rulesForm.role" placeholder="Select role" style="width: 100%;">
              <el-option
                v-for="item in menus"
                :key="item.roleName"
                :label="item.roleName"
                :value="item.roleName"
              />
            </el-select>
          </el-form-item>

          <div class="action-row">
            <el-button type="primary" class="action-button" @click="login">
              Sign in
            </el-button>
            <el-button class="action-button secondary" @click="register('user')">
              Create account
            </el-button>
          </div>
        </el-form>

        <p class="demo-tip">
          Demo accounts: <span>admin / 123456</span> and <span>user / 123456</span>
        </p>
      </div>
    </section>
  </div>
</template>

<script>
import menu from '@/utils/menu'

export default {
  data() {
    return {
      rulesForm: {
        username: '',
        password: '',
        role: 'User'
      },
      menus: [],
      tableName: ''
    }
  },
  mounted() {
    this.menus = menu.list()
  },
  methods: {
    register(tableName) {
      this.$storage.set('loginTable', tableName)
      this.$router.push({ path: '/register' })
    },

    login() {
      if (!this.rulesForm.username) {
        this.$message.error('Please enter username')
        return
      }

      if (!this.rulesForm.password) {
        this.$message.error('Please enter password')
        return
      }

      if (!this.rulesForm.role) {
        this.$message.error('Please select a role')
        return
      }

      const matchedRole = this.menus.find(
        item => item.roleName === this.rulesForm.role
      )

      if (!matchedRole) {
        this.$message.error('Unable to find the selected role')
        return
      }

      this.tableName = matchedRole.tableName

      this.$http({
        url: `${this.tableName}/login?username=${this.rulesForm.username}&password=${this.rulesForm.password}`,
        method: 'post'
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$storage.set('Token', data.token)
          this.$storage.set('role', this.rulesForm.role)
          this.$storage.set('sessionTable', this.tableName)
          this.$storage.set('adminName', this.rulesForm.username)
          this.$router.replace({ path: '/index/' })
          return
        }

        this.$message.error(data.msg)
      }).catch(error => {
        if (!error.response || !error.response.data) {
          this.$message({
            showClose: true,
            message: error,
            type: 'error',
            duration: 20000
          })
          return
        }

        this.$message({
          showClose: true,
          message: error.response.data,
          type: 'error',
          duration: 20000
        })
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.login-page {
  position: relative;
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 32px;
  background:
    linear-gradient(120deg, rgba(15, 23, 42, 0.7), rgba(12, 74, 110, 0.45)),
    url('../assets/img/bg.jpg') center center / cover no-repeat;
}

.login-overlay {
  position: absolute;
  inset: 0;
  background:
    radial-gradient(circle at top right, rgba(251, 191, 36, 0.2), transparent 22%),
    radial-gradient(circle at bottom left, rgba(96, 165, 250, 0.18), transparent 26%);
}

.login-shell {
  position: relative;
  z-index: 1;
  width: min(1120px, 100%);
  display: grid;
  grid-template-columns: 1.05fr 460px;
  gap: 28px;
  align-items: stretch;
}

.login-copy,
.login-card {
  border: 1px solid rgba(255, 255, 255, 0.14);
  background: rgba(255, 255, 255, 0.9);
  box-shadow: 0 25px 60px rgba(15, 23, 42, 0.28);
  backdrop-filter: blur(14px);
}

.login-copy {
  padding: 42px;
  border-radius: 28px;
  color: #0f172a;

  h1 {
    margin: 10px 0 14px;
    font-size: 48px;
    line-height: 1;
  }
}

.eyebrow {
  margin: 0;
  color: #c2410c;
  font-size: 12px;
  font-weight: 700;
  letter-spacing: 0.16em;
  text-transform: uppercase;
}

.summary {
  max-width: 520px;
  margin: 0;
  color: #475569;
  font-size: 16px;
  line-height: 1.7;
}

.highlight-list {
  display: grid;
  gap: 16px;
  margin-top: 36px;
}

.highlight-card {
  display: flex;
  flex-direction: column;
  gap: 4px;
  padding: 18px 20px;
  border-radius: 18px;
  background: rgba(248, 250, 252, 0.92);
  border: 1px solid rgba(148, 163, 184, 0.16);

  strong {
    color: #0f172a;
    font-size: 24px;
  }

  span {
    color: #64748b;
    line-height: 1.5;
  }
}

.login-card {
  padding: 30px 28px 24px;
  border-radius: 24px;
}

.card-header {
  display: flex;
  align-items: center;
  gap: 14px;
  margin-bottom: 26px;

  h2 {
    margin: 4px 0 0;
    font-size: 28px;
    color: #0f172a;
  }
}

.card-label {
  margin: 0;
  color: #64748b;
  font-size: 13px;
  text-transform: uppercase;
  letter-spacing: 0.08em;
}

.brand-mark {
  width: 52px;
  height: 52px;
  object-fit: contain;
  user-select: none;
}

.action-row {
  display: grid;
  gap: 12px;
  margin-top: 8px;
}

.action-button {
  width: 100%;
  height: 46px;
  border-radius: 12px;
  font-weight: 600;
}

.action-button.secondary {
  border-color: rgba(15, 23, 42, 0.14);
  color: #0f172a;
  background: #ffffff;
}

.demo-tip {
  margin: 18px 0 0;
  color: #64748b;
  font-size: 13px;
  line-height: 1.7;

  span {
    color: #0f172a;
    font-weight: 600;
  }
}

::v-deep .el-form-item {
  margin-bottom: 18px;
}

::v-deep .el-form-item__label {
  color: #334155;
  font-weight: 600;
}

::v-deep .el-input__inner,
::v-deep .el-select .el-input__inner {
  height: 46px;
  border-radius: 12px;
  border-color: rgba(148, 163, 184, 0.28);
  background: rgba(248, 250, 252, 0.96);
}

::v-deep .el-input__inner:focus,
::v-deep .el-select .el-input.is-focus .el-input__inner {
  border-color: #0f766e;
}

@media (max-width: 980px) {
  .login-shell {
    grid-template-columns: 1fr;
  }

  .login-copy {
    padding: 30px;

    h1 {
      font-size: 38px;
    }
  }
}

@media (max-width: 640px) {
  .login-page {
    padding: 18px;
  }

  .login-copy,
  .login-card {
    padding: 24px 20px;
    border-radius: 20px;
  }

  .login-copy h1 {
    font-size: 32px;
  }
}
</style>
