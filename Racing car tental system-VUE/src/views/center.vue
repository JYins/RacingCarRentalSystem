<template>
  <div>
    <el-form class="detail-form-content" ref="ruleForm" :model="ruleForm" label-width="80px">
      <el-row>
        <el-col :span="12">
          <el-form-item v-if="flag == 'user'" label="Status" prop="username">
            <div v-if="ruleForm.isReviewed == 'No'" class="disagree"> Not Verified</div>
            <div v-else class="agree"> Verified</div>
          </el-form-item>
          <el-form-item v-if="flag == 'user'" label="Username" prop="username">
            <el-input v-model="ruleForm.username" readonly placeholder="Username" clearable></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item v-if="flag == 'user'" label="Name" prop="realName">
            <el-input v-model="ruleForm.realName" placeholder="Full Name" clearable></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item v-if="flag == 'user'" label="Gender" prop="gender">
            <el-select v-model="ruleForm.gender" placeholder="Select gender">
              <el-option v-for="(item, index) in genderOptions" v-bind:key="index" :label="item" :value="item">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item v-if="flag == 'user'" label="Birthday" prop="birthday">
            <el-date-picker format="yyyy-MM-dd" value-format="yyyy-MM-dd" v-model="ruleForm.birthday" type="date"
              placeholder="Birthday">
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item v-if="flag == 'user'" label="ID Number" prop="identificationNumber">
            <el-input v-model="ruleForm.identificationNumber" placeholder="ID Number" clearable></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item v-if="flag == 'user'" label="Phone" prop="mobilePhone">
            <el-input v-model="ruleForm.mobilePhone" placeholder="Phone" clearable></el-input>
          </el-form-item>
        </el-col>
        <el-form-item v-if="flag == 'admin'" label="Username" prop="username">
          <el-input v-model="ruleForm.username" placeholder="Username"></el-input>
        </el-form-item>
        <el-col :span="24">
          <el-row>
            <el-col :span="24">
              <el-form-item v-if="type != 'info' && flag != 'admin'" label="Photo (ID/License)" prop="returnContent">
                <editor style="min-width: 200px; max-width: 600px;" v-model="ruleForm.photo" class="editor"
                  action="file/upload">
                </editor>
              </el-form-item>
              <div v-if="type == 'info' && flag != 'admin'">
                <el-form-item label="Photo (ID/License)" prop="returnContent">
                  <span v-html="ruleForm.photo"></span>
                </el-form-item>
              </div>
            </el-col>
          </el-row>
          <el-form-item>
            <el-button @click="onUpdateHandler" type="primary"> Save</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>
<script>
// validate: number, email, phone, url, id card
import { isNumber, isIntNumer, isEmail, isMobile, isPhone, isURL, checkIdCard } from "@/utils/validate";

export default {
  data() {
    return {
      ruleForm: {},
      flag: '',
      usersFlag: false,
      genderOptions: [],
      type: ''
    };
  },
  mounted() {
    var table = this.$storage.get("sessionTable");
    this.flag = table;
    this.$http({
      url: `${this.$storage.get("sessionTable")}/session`,
      method: "get"
    }).then(({ data }) => {
      if (data && data.code === 0) {
        this.ruleForm = data.data;
      } else {
        this.$message.error(data.msg);
      }
    });
    this.genderOptions = "Male,Female".split(',')
  },
  methods: {
    onUpdateHandler() {
      if ((!this.ruleForm.username) && 'user' == this.flag) {
        this.$message.error('Username is required');
        return
      }
      if ((!this.ruleForm.password) && 'user' == this.flag) {
        this.$message.error('Password is required');
        return
      }
      // ID and phone validation relaxed - any format accepted
      if ('admin' == this.flag && this.ruleForm.username.trim().length < 1) {
        this.$message.error(`Username is required`);
        return
      }
      this.$http({
        url: `${this.$storage.get("sessionTable")}/update`,
        method: "post",
        data: this.ruleForm
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$message({
            message: "Profile updated",
            type: "success",
            onClose: () => {
            }
          });
        } else {
          this.$message.error(data.msg);
        }
      });
    }
  }
};
</script>
<style lang="scss" scoped>
.disagree {
  color: red;
  text-align: left;
  font-size: 16px;
}

.agree {
  color: green;  
  text-align: left;

  font-size: 16px;
}
</style>
