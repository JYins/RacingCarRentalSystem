<template>
  <div>
    <el-form
      class="detail-form-content"
      ref="ruleForm"
      :rules="rules"
      :model="ruleForm"
      label-width="120px"
    >
      <el-form-item label="Old Password" prop="password">
        <el-input v-model="ruleForm.password" show-password></el-input>
      </el-form-item>
      <el-form-item label="New Password" prop="newpassword">
        <el-input v-model="ruleForm.newpassword" show-password></el-input>
      </el-form-item>
      <el-form-item label="Confirm" prop="repassword">
        <el-input v-model="ruleForm.repassword" show-password></el-input>
      </el-form-item>
      <el-form-item>
        <el-button  @click="onUpdateHandler" type="primary"> Save</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  data() {
    return {
      dialogVisible: false,
      ruleForm: {},
      user: {},
      rules: {
        password: [
          {
            required: true,
            message: "Password is required",
            trigger: "blur"
          }
        ],
        newpassword: [
          {
            required: true,
            message: "New password is required",
            trigger: "blur"
          }
        ],
        repassword: [
          {
            required: true,
            message: "Confirm password is required",
            trigger: "blur"
          }
        ]
      }
    };
  },
  mounted() {
    this.$http({
      url: `${this.$storage.get("sessionTable")}/session`,
      method: "get"
    }).then(({ data }) => {
      if (data && data.code === 0) {
        this.user = data.data;
      } else {
        this.$message.error(data.msg);
      }
    });
  },
  methods: {
    onLogout() {
      this.$storage.remove("Token");
      this.$router.replace({ name: "login" });
    },
    // change password
    onUpdateHandler() {
      this.$refs["ruleForm"].validate(valid => {
        if (valid) {
          var password = "";
          if (this.user.password) {
            password = this.user.password;
          } else if (this.user.password) {
            password = this.user.password;
          }
          if (this.ruleForm.password != password) {
            this.$message.error("Wrong old password");
            return;
          }
          if (this.ruleForm.newpassword != this.ruleForm.repassword) {
            this.$message.error("Passwords don't match");
            return;
          }
          this.user.password = this.ruleForm.newpassword;
          this.user.password = this.ruleForm.newpassword;
          this.$http({
            url: `${this.$storage.get("sessionTable")}/update`,
            method: "post",
            data: this.user
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.ruleForm.repassword = ''
              this.ruleForm.password = ''
              this.ruleForm.newpassword = ''
              this.$message({
                message: "Password changed. Please login again.",
                type: "success",
                duration: 1000,
                onClose: () => {
                }
              });
            } else {
              this.$message.error(data.msg);
            }
          });
        }
      });
    }
  }
};
</script>
<style lang="scss" scoped>
</style>
