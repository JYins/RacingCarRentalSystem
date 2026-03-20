<template>
  <div class="container">
    <el-alert title="Please verify order info before payment"  :closable="false"></el-alert>
    <!-- <div class="top-content">
      <span>Payee</span>
      <el-input style="width:300px" v-model="name" placeholder="Payee"></el-input>
      <span style="margin-left:20px">Account</span>
      <el-input style="width:300px" v-model="account" placeholder="Account"></el-input>
    </div> -->
    <!-- <div class="price-content">
      <span>Amount</span>
      <span>$99.0</span>
    </div> -->
    <div class="pay-type-content">
      <div class="pay-type-item">
        <el-radio v-model="type" label="Credit Card (Visa/MC)"></el-radio>
        <span style="font-size:24px;">💳</span>
      </div>
      <div class="pay-type-item">
        <el-radio v-model="type" label="PayPal"></el-radio>
        <span style="font-size:24px;">🅿️</span>
      </div>
      <div class="pay-type-item">
        <el-radio v-model="type" label="Apple Pay"></el-radio>
        <span style="font-size:24px;">🍎</span>
      </div>
      <div class="pay-type-item">
        <el-radio v-model="type" label="Google Pay"></el-radio>
        <span style="font-size:24px;">📱</span>
      </div>
      <div class="pay-type-item">
        <el-radio v-model="type" label="Interac e-Transfer"></el-radio>
        <span style="font-size:24px;">🏦</span>
      </div>
      <div class="pay-type-item">
        <el-radio v-model="type" label="Debit Card"></el-radio>
        <span style="font-size:24px;">💵</span>
      </div>
    </div>
    <div class="buton-content">
      <el-button @click="submitTap" > Confirm Pay</el-button>
      <el-button @click="back()"> Back</el-button>
    </div>
  </div>
</template>
<script>
// import { Message } from "element-ui";
export default {
  data() {
    return {
      name: "",
      account: "",
      type: "",
      table: "",
      obj: ""
    };
  },
  mounted() {
    let table = this.$storage.get("paytable");
    let obj = this.$storage.getObj("payObject");
    this.table = table;
    this.obj = obj;
  },
  methods: {
    submitTap() {
      // if (!this.name) {
      //   this.$message.error("Please enter payee name");
      //   return;
      // }
      // if (!this.account) {
      //   this.$message.error("Please enter account");
      //   return;
      // }
      if (!this.type) {
        this.$message.error("Please select payment method");
        return;
      }
      this.$confirm(`Confirm payment?`, "Notice", {
        confirmButtonText: "OK",
        cancelButtonText: "Cancel",
        type: "warning"
      }).then(() => {
        this.obj.isPaid = "Paid";
        this.$http({
          url: `${this.table}/update`,
          method: "post",
          data: this.obj
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.$message({
              message: "Payment success",
              type: "success",
              duration: 1000,
              onClose: () => {
                this.$router.go(-1);
              }
            });
          } else {
            this.$message.error(data.msg);
          }
        });
      });
    },
    back(){
      this.$router.go(-1);
    }
  }
};
</script>
<style lang="scss" scoped>
.container {
  margin: 10px;
  font-size: 14px;
  span {
    width: 60px;
  }
  .top-content {
    display: flex;
    align-items: center;
    padding: 20px;
  }
  .price-content {
    display: flex;
    align-items: center;
    margin-top: 20px;
    padding-bottom: 20px;
    padding: 20px;
    border-bottom: 1px solid #eeeeee;
    font-size: 20px;
    font-weight: bold;
    color: red;
  }
  .pay-type-content {
    display: flex;
    align-items: center;
    margin-top: 20px;
    flex-wrap: wrap;
    span {
      width: 100px;
    }
    .pay-type-item {
      display: flex;
      align-items: center;
      justify-content: space-between;
      width: 300px;
      margin: 20px;
      border: 1px solid #eeeeee;
      padding: 20px;
    }
  }
  .buton-content {
    margin: 20px;
  }
}
</style>
