<template>
  <div class="addEdit-block">
    <el-form class="detail-form-content" ref="ruleForm" :model="ruleForm" :rules="rules" label-width="80px"
      :style="{ backgroundColor: addEditForm.addEditBoxColor }">
      <el-row>
        <el-col :span="12">
          <el-form-item class="input" v-if="type != 'info'" label="Car Name" prop="carName">
            <el-input v-model="ruleForm.carName" placeholder="Car Name" clearable :readonly="ro.carName"></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item class="input" label="Car Name" prop="carName">
              <el-input v-model="ruleForm.carName" placeholder="Car Name" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="12">
          <el-form-item class="input" v-if="type != 'info'" label="Car Type" prop="carType">
            <el-input v-model="ruleForm.carType" placeholder="Car Type" clearable :readonly="ro.carType"></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item class="input" label="Car Type" prop="carType">
              <el-input v-model="ruleForm.carType" placeholder="Car Type" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="12">
          <el-form-item class="input" v-if="type != 'info'" label="Brand" prop="brand">
            <el-input v-model="ruleForm.brand" placeholder="Brand" clearable :readonly="ro.brand"></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item class="input" label="Brand" prop="brand">
              <el-input v-model="ruleForm.brand" placeholder="Brand" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="12">
          <el-form-item class="input" v-if="type != 'info'" label="Color" prop="bodyColor">
            <el-input v-model="ruleForm.bodyColor" placeholder="Color" clearable :readonly="ro.bodyColor"></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item class="input" label="Color" prop="bodyColor">
              <el-input v-model="ruleForm.bodyColor" placeholder="Color" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="12">
          <el-form-item class="input" v-if="type != 'info'" label="Seats" prop="seatingCapacity">
            <el-input v-model="ruleForm.seatingCapacity" placeholder="Seats" clearable
              :readonly="ro.seatingCapacity"></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item class="input" label="Seats" prop="seatingCapacity">
              <el-input v-model="ruleForm.seatingCapacity" placeholder="Seats" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="12">
          <el-form-item class="input" v-if="type != 'info'" label="Plate No." prop="licensePlateNumber">
            <el-input v-model="ruleForm.licensePlateNumber" placeholder="Plate No." clearable
              :readonly="ro.licensePlateNumber"></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item class="input" label="Plate No." prop="licensePlateNumber">
              <el-input v-model="ruleForm.licensePlateNumber" placeholder="Plate No." readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item v-if="type != 'info'" label="Extra Info" prop="returnContent">
            <editor style="min-width: 200px; max-width: 600px;" v-model="ruleForm.content" class="editor"
              action="file/upload">
            </editor>
          </el-form-item>
          <div v-else>
            <el-form-item label="Extra Info" prop="returnContent">
              <span v-html="ruleForm.content"></span>
            </el-form-item>
          </div>
        </el-col>
      </el-row>
      <el-form-item>
        <el-button v-if="type != 'info'" @click="onSubmit"> Save</el-button>
        <el-button v-if="type != 'info'" @click="back()"> Cancel</el-button>
        <el-button v-if="type == 'info'" @click="back()"> Back</el-button>
      </el-form-item>
    </el-form>


  </div>
</template>
<script>
// validate: number, email, phone, url, id card
import { isNumber, isIntNumer, isEmail, isPhone, isMobile, isURL, checkIdCard } from "@/utils/validate";
export default {
  data() {
    let self = this
    var validateIdCard = (rule, value, callback) => {
      if (!value) {
        callback();
      } else if (!checkIdCard(value)) {
        callback(new Error("Please enter valid ID number"));
      } else {
        callback();
      }
    };
    var validateUrl = (rule, value, callback) => {
      if (!value) {
        callback();
      } else if (!isURL(value)) {
        callback(new Error("Please enter valid URL"));
      } else {
        callback();
      }
    };
    var validateMobile = (rule, value, callback) => {
      if (!value) {
        callback();
      } else if (!isMobile(value)) {
        callback(new Error("Please enter valid phone number"));
      } else {
        callback();
      }
    };
    var validatePhone = (rule, value, callback) => {
      if (!value) {
        callback();
      } else if (!isPhone(value)) {
        callback(new Error("Please enter valid phone number"));
      } else {
        callback();
      }
    };
    var validateEmail = (rule, value, callback) => {
      if (!value) {
        callback();
      } else if (!isEmail(value)) {
        callback(new Error("Please enter valid email"));
      } else {
        callback();
      }
    };
    var validateNumber = (rule, value, callback) => {
      if (!value) {
        callback();
      } else if (!isNumber(value)) {
        callback(new Error("Please enter a number"));
      } else {
        callback();
      }
    };
    var validateIntNumber = (rule, value, callback) => {
      if (!value) {
        callback();
      } else if (!isIntNumer(value)) {
        callback(new Error("Please enter an integer"));
      } else {
        callback();
      }
    };
    return {
      addEditForm: { "btnSaveFontColor": "#fff", "selectFontSize": "14px", "btnCancelBorderColor": "#DCDFE6", "inputBorderRadius": "4px", "inputFontSize": "14px", "textareaBgColor": "#fff", "btnSaveFontSize": "14px", "textareaBorderRadius": "4px", "uploadBgColor": "#fff", "textareaBorderStyle": "solid", "btnCancelWidth": "88px", "textareaHeight": "120px", "dateBgColor": "#fff", "btnSaveBorderRadius": "4px", "uploadLableFontSize": "14px", "textareaBorderWidth": "1px", "inputLableColor": "#606266", "addEditBoxColor": "rgba(245, 247, 250, 1)", "dateIconFontSize": "14px", "btnSaveBgColor": "#409EFF", "uploadIconFontColor": "#8c939d", "textareaBorderColor": "#DCDFE6", "btnCancelBgColor": "#ecf5ff", "selectLableColor": "#606266", "btnSaveBorderStyle": "solid", "dateBorderWidth": "1px", "dateLableFontSize": "14px", "dateBorderRadius": "4px", "btnCancelBorderStyle": "solid", "selectLableFontSize": "14px", "selectBorderStyle": "solid", "selectIconFontColor": "#C0C4CC", "btnCancelHeight": "44px", "inputHeight": "40px", "btnCancelFontColor": "#606266", "dateBorderColor": "#DCDFE6", "dateIconFontColor": "#C0C4CC", "uploadBorderStyle": "solid", "dateBorderStyle": "solid", "dateLableColor": "#606266", "dateFontSize": "14px", "inputBorderWidth": "1px", "uploadIconFontSize": "28px", "selectHeight": "40px", "inputFontColor": "#606266", "uploadHeight": "148px", "textareaLableColor": "#606266", "textareaLableFontSize": "14px", "btnCancelFontSize": "14px", "inputBorderStyle": "solid", "btnCancelBorderRadius": "4px", "inputBgColor": "#fff", "inputLableFontSize": "14px", "uploadLableColor": "#606266", "uploadBorderRadius": "4px", "btnSaveHeight": "44px", "selectBgColor": "#fff", "btnSaveWidth": "88px", "selectIconFontSize": "14px", "dateHeight": "40px", "selectBorderColor": "#DCDFE6", "inputBorderColor": "#DCDFE6", "uploadBorderColor": "#DCDFE6", "textareaFontColor": "#606266", "selectBorderWidth": "1px", "dateFontColor": "#606266", "btnCancelBorderWidth": "1px", "uploadBorderWidth": "1px", "textareaFontSize": "14px", "selectBorderRadius": "4px", "selectFontColor": "#606266", "btnSaveBorderColor": "#409EFF", "btnSaveBorderWidth": "1px" },
      id: '',
      type: '',
      ro: {
        carName: false,
        carType: false,
        brand: false,
        bodyColor: false,
        seatingCapacity: false,
        licensePlateNumber: false,
      },
      ruleForm: {
        carName: '',
        carType: '',
        brand: '',
        bodyColor: '',
        seatingCapacity: '',
        licensePlateNumber: '',
      },
      rules: {
        carName: [
        ],
        carType: [
        ],
        brand: [
        ],
        bodyColor: [
        ],
        seatingCapacity: [
        ],
        licensePlateNumber: [
        ],
      }
    };
  },
  props: ["parent"],
  computed: {
  },
  created() {
    this.addEditStyleChange()
    this.addEditUploadStyleChange()
  },
  methods: {
    // download
    download(file) {
      window.open(`${file}`)
    },
    // init
    init(id, type) {
      if (id) {
        this.id = id;
        this.type = type;
      }
      if (this.type == 'info' || this.type == 'else') {
        this.info(id);
      } else if (this.type == 'cross') {
        var obj = this.$storage.getObj('crossObj');
        for (var o in obj) {
          if (o == 'carName') {
            this.ruleForm.carName = obj[o];
            this.ro.carName = true;
            continue;
          }
          if (o == 'carType') {
            this.ruleForm.carType = obj[o];
            this.ro.carType = true;
            continue;
          }
          if (o == 'brand') {
            this.ruleForm.brand = obj[o];
            this.ro.brand = true;
            continue;
          }
          if (o == 'bodyColor') {
            this.ruleForm.bodyColor = obj[o];
            this.ro.bodyColor = true;
            continue;
          }
          if (o == 'seatingCapacity') {
            this.ruleForm.seatingCapacity = obj[o];
            this.ro.seatingCapacity = true;
            continue;
          }
          if (o == 'licensePlateNumber') {
            this.ruleForm.licensePlateNumber = obj[o];
            this.ro.licensePlateNumber = true;
            continue;
          }
        }
      }
      // get user info
      this.$http({
        url: `${this.$storage.get('sessionTable')}/session`,
        method: "get"
      }).then(({ data }) => {
        if (data && data.code === 0) {
          var json = data.data;
        } else {
          this.$message.error(data.msg);
        }
      });
    },
    // load info
    info(id) {
      this.$http({
        url: `carInfo/info/${id}`,
        method: "get"
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.ruleForm = data.data;
          // fix image path issue
          let reg = new RegExp('../../../upload', 'g')
        } else {
          this.$message.error(data.msg);
        }
      });
    },
    // submit
    onSubmit() {
      // ${column.compare}
      // ${column.compare}
      // ${column.compare}
      // ${column.compare}
      // ${column.compare}
      // ${column.compare}






      this.$refs["ruleForm"].validate(valid => {
        if (valid) {
          this.$http({
            url: `carInfo/${!this.ruleForm.id ? "save" : "update"}`,
            method: "post",
            data: this.ruleForm
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: "Success",
                type: "success",
                duration: 1000,
                onClose: () => {
                  this.parent.showFlag = true;
                  this.parent.addOrUpdateFlag = false;
                  this.parent.qichexinxiCrossAddOrUpdateFlag = false;
                  this.parent.search();
                  this.parent.contentStyleChange();
                }
              });
            } else {
              this.$message.error(data.msg);
            }
          });
        }
      });
    },
    // get uuid
    getUUID() {
      return new Date().getTime();
    },
    // go back
    back() {
      this.parent.showFlag = true;
      this.parent.addOrUpdateFlag = false;
      this.parent.qichexinxiCrossAddOrUpdateFlag = false;
      this.parent.contentStyleChange();
    },
    addEditStyleChange() {
      this.$nextTick(() => {
        // input
        document.querySelectorAll('.addEdit-block .input .el-input__inner').forEach(el => {
          el.style.height = this.addEditForm.inputHeight
          el.style.color = this.addEditForm.inputFontColor
          el.style.fontSize = this.addEditForm.inputFontSize
          el.style.borderWidth = this.addEditForm.inputBorderWidth
          el.style.borderStyle = this.addEditForm.inputBorderStyle
          el.style.borderColor = this.addEditForm.inputBorderColor
          el.style.borderRadius = this.addEditForm.inputBorderRadius
          el.style.backgroundColor = this.addEditForm.inputBgColor
        })
        document.querySelectorAll('.addEdit-block .input .el-form-item__label').forEach(el => {
          el.style.lineHeight = this.addEditForm.inputHeight
          el.style.color = this.addEditForm.inputLableColor
          el.style.fontSize = this.addEditForm.inputLableFontSize
        })
        // select
        document.querySelectorAll('.addEdit-block .select .el-input__inner').forEach(el => {
          el.style.height = this.addEditForm.selectHeight
          el.style.color = this.addEditForm.selectFontColor
          el.style.fontSize = this.addEditForm.selectFontSize
          el.style.borderWidth = this.addEditForm.selectBorderWidth
          el.style.borderStyle = this.addEditForm.selectBorderStyle
          el.style.borderColor = this.addEditForm.selectBorderColor
          el.style.borderRadius = this.addEditForm.selectBorderRadius
          el.style.backgroundColor = this.addEditForm.selectBgColor
        })
        document.querySelectorAll('.addEdit-block .select .el-form-item__label').forEach(el => {
          el.style.lineHeight = this.addEditForm.selectHeight
          el.style.color = this.addEditForm.selectLableColor
          el.style.fontSize = this.addEditForm.selectLableFontSize
        })
        document.querySelectorAll('.addEdit-block .select .el-select__caret').forEach(el => {
          el.style.color = this.addEditForm.selectIconFontColor
          el.style.fontSize = this.addEditForm.selectIconFontSize
        })
        // date
        document.querySelectorAll('.addEdit-block .date .el-input__inner').forEach(el => {
          el.style.height = this.addEditForm.dateHeight
          el.style.color = this.addEditForm.dateFontColor
          el.style.fontSize = this.addEditForm.dateFontSize
          el.style.borderWidth = this.addEditForm.dateBorderWidth
          el.style.borderStyle = this.addEditForm.dateBorderStyle
          el.style.borderColor = this.addEditForm.dateBorderColor
          el.style.borderRadius = this.addEditForm.dateBorderRadius
          el.style.backgroundColor = this.addEditForm.dateBgColor
        })
        document.querySelectorAll('.addEdit-block .date .el-form-item__label').forEach(el => {
          el.style.lineHeight = this.addEditForm.dateHeight
          el.style.color = this.addEditForm.dateLableColor
          el.style.fontSize = this.addEditForm.dateLableFontSize
        })
        document.querySelectorAll('.addEdit-block .date .el-input__icon').forEach(el => {
          el.style.color = this.addEditForm.dateIconFontColor
          el.style.fontSize = this.addEditForm.dateIconFontSize
          el.style.lineHeight = this.addEditForm.dateHeight
        })
        // upload
        let iconLineHeight = parseInt(this.addEditForm.uploadHeight) - parseInt(this.addEditForm.uploadBorderWidth) * 2 + 'px'
        document.querySelectorAll('.addEdit-block .upload .el-upload--picture-card').forEach(el => {
          el.style.width = this.addEditForm.uploadHeight
          el.style.height = this.addEditForm.uploadHeight
          el.style.borderWidth = this.addEditForm.uploadBorderWidth
          el.style.borderStyle = this.addEditForm.uploadBorderStyle
          el.style.borderColor = this.addEditForm.uploadBorderColor
          el.style.borderRadius = this.addEditForm.uploadBorderRadius
          el.style.backgroundColor = this.addEditForm.uploadBgColor
        })
        document.querySelectorAll('.addEdit-block .upload .el-form-item__label').forEach(el => {
          el.style.lineHeight = this.addEditForm.uploadHeight
          el.style.color = this.addEditForm.uploadLableColor
          el.style.fontSize = this.addEditForm.uploadLableFontSize
        })
        document.querySelectorAll('.addEdit-block .upload .el-icon-plus').forEach(el => {
          el.style.color = this.addEditForm.uploadIconFontColor
          el.style.fontSize = this.addEditForm.uploadIconFontSize
          el.style.lineHeight = iconLineHeight
          el.style.display = 'block'
        })
        // 多文本输入框
        document.querySelectorAll('.addEdit-block .textarea .el-textarea__inner').forEach(el => {
          el.style.height = this.addEditForm.textareaHeight
          el.style.color = this.addEditForm.textareaFontColor
          el.style.fontSize = this.addEditForm.textareaFontSize
          el.style.borderWidth = this.addEditForm.textareaBorderWidth
          el.style.borderStyle = this.addEditForm.textareaBorderStyle
          el.style.borderColor = this.addEditForm.textareaBorderColor
          el.style.borderRadius = this.addEditForm.textareaBorderRadius
          el.style.backgroundColor = this.addEditForm.textareaBgColor
        })
        document.querySelectorAll('.addEdit-block .textarea .el-form-item__label').forEach(el => {
          // el.style.lineHeight = this.addEditForm.textareaHeight
          el.style.color = this.addEditForm.textareaLableColor
          el.style.fontSize = this.addEditForm.textareaLableFontSize
        })
        // 保存
        document.querySelectorAll('.addEdit-block .btn .btn-success').forEach(el => {
          el.style.width = this.addEditForm.btnSaveWidth
          el.style.height = this.addEditForm.btnSaveHeight
          el.style.color = this.addEditForm.btnSaveFontColor
          el.style.fontSize = this.addEditForm.btnSaveFontSize
          el.style.borderWidth = this.addEditForm.btnSaveBorderWidth
          el.style.borderStyle = this.addEditForm.btnSaveBorderStyle
          el.style.borderColor = this.addEditForm.btnSaveBorderColor
          el.style.borderRadius = this.addEditForm.btnSaveBorderRadius
          el.style.backgroundColor = this.addEditForm.btnSaveBgColor
        })
        // 返回
        document.querySelectorAll('.addEdit-block .btn .btn-close').forEach(el => {
          el.style.width = this.addEditForm.btnCancelWidth
          el.style.height = this.addEditForm.btnCancelHeight
          el.style.color = this.addEditForm.btnCancelFontColor
          el.style.fontSize = this.addEditForm.btnCancelFontSize
          el.style.borderWidth = this.addEditForm.btnCancelBorderWidth
          el.style.borderStyle = this.addEditForm.btnCancelBorderStyle
          el.style.borderColor = this.addEditForm.btnCancelBorderColor
          el.style.borderRadius = this.addEditForm.btnCancelBorderRadius
          el.style.backgroundColor = this.addEditForm.btnCancelBgColor
        })
      })
    },
    addEditUploadStyleChange() {
      this.$nextTick(() => {
        document.querySelectorAll('.addEdit-block .upload .el-upload-list--picture-card .el-upload-list__item').forEach(el => {
          el.style.width = this.addEditForm.uploadHeight
          el.style.height = this.addEditForm.uploadHeight
          el.style.borderWidth = this.addEditForm.uploadBorderWidth
          el.style.borderStyle = this.addEditForm.uploadBorderStyle
          el.style.borderColor = this.addEditForm.uploadBorderColor
          el.style.borderRadius = this.addEditForm.uploadBorderRadius
          el.style.backgroundColor = this.addEditForm.uploadBgColor
        })
      })
    },
  }
};
</script>
<style lang="scss">
.editor {
  height: 500px;

  & ::v-deep .ql-container {
    height: 310px;
  }
}

.amap-wrapper {
  width: 100%;
  height: 500px;
}

.search-box {
  position: absolute;
}

.addEdit-block {
  margin: -10px;
}

.detail-form-content {
  padding: 12px;
}

.btn .el-button {
  padding: 0;
}
</style>
