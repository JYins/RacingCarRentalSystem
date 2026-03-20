<template>
  <div class="main-content">
    <!-- list page -->
    <div v-if="showFlag">
      <el-form :inline="true" :model="searchForm" class="form-content">
        <el-row :gutter="20" class="slt"
          :style="{ justifyContent: contents.searchBoxPosition == '1' ? 'flex-start' : contents.searchBoxPosition == '2' ? 'center' : 'flex-end' }">
          <el-form-item :label="contents.inputTitle == 1 ? 'Car Name' : ''">
            <el-input v-if="contents.inputIcon == 1 && contents.inputIconPosition == 1"  
              v-model="searchForm.carName" placeholder="Car Name" clearable></el-input>
            <el-input v-if="contents.inputIcon == 1 && contents.inputIconPosition == 2"  
              v-model="searchForm.carName" placeholder="Car Name" clearable></el-input>
            <el-input v-if="contents.inputIcon == 0" v-model="searchForm.carName" placeholder="Car Name" clearable></el-input>
          </el-form-item>
          <el-form-item :label="contents.inputTitle == 1 ? 'Plate No.' : ''">
            <el-input v-if="contents.inputIcon == 1 && contents.inputIconPosition == 1"  
              v-model="searchForm.licensePlateNumber" placeholder="Plate No." clearable></el-input>
            <el-input v-if="contents.inputIcon == 1 && contents.inputIconPosition == 2"  
              v-model="searchForm.licensePlateNumber" placeholder="Plate No." clearable></el-input>
            <el-input v-if="contents.inputIcon == 0" v-model="searchForm.licensePlateNumber" placeholder="Plate No."
              clearable></el-input>
          </el-form-item>
          <el-form-item>
            <el-button v-if="contents.searchBtnIcon == 1 && contents.searchBtnIconPosition == 1" icon="el-icon-search"
              @click="search()">{{ contents.searchBtnFont == 1 ? 'Search' : '' }}</el-button>
            <el-button v-if="contents.searchBtnIcon == 1 && contents.searchBtnIconPosition == 2" @click="search()">{{
              contents.searchBtnFont == 1 ? 'Search' : '' }}<i class="el-icon-search el-icon--right" /></el-button>
            <el-button v-if="contents.searchBtnIcon == 0" @click="search()">{{ contents.searchBtnFont == 1 ? 'Search' : ''
            }}</el-button>
          </el-form-item>
        </el-row>
        <el-row class="ad"
          :style="{ justifyContent: contents.btnAdAllBoxPosition == '1' ? 'flex-start' : contents.btnAdAllBoxPosition == '2' ? 'center' : 'flex-end' }">
          <el-form-item>
            <el-button v-if="isAuth('carInfo', 'Add') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 1"
                @click="addOrUpdateHandler()">{{ contents.btnAdAllFont == 1 ? ' Add' : '' }}</el-button>
            <el-button v-if="isAuth('carInfo', 'Add') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 2"
              @click="addOrUpdateHandler()">{{ contents.btnAdAllFont == 1 ? ' Add' : '' }}</el-button>
            <el-button v-if="isAuth('carInfo', 'Add') && contents.btnAdAllIcon == 0" @click="addOrUpdateHandler()">{{
              contents.btnAdAllFont == 1 ? ' Add' : '' }}</el-button>
            <el-button
              v-if="isAuth('carInfo', 'Delete') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 1 && contents.tableSelection"
              :disabled="dataListSelections.length <= 0" icon="el-icon-delete" @click="deleteHandler()">{{
                contents.btnAdAllFont == 1 ? 'Delete' : '' }}</el-button>
            <el-button
              v-if="isAuth('carInfo', 'Delete') && contents.btnAdAllIcon == 1 && contents.btnAdAllIconPosition == 2 && contents.tableSelection"
              :disabled="dataListSelections.length <= 0" @click="deleteHandler()">{{ contents.btnAdAllFont == 1 ? 'Delete' : ''
              }}</el-button>
            <el-button v-if="isAuth('carInfo', 'Delete') && contents.btnAdAllIcon == 0 && contents.tableSelection"
              :disabled="dataListSelections.length <= 0" @click="deleteHandler()">{{ contents.btnAdAllFont == 1 ? 'Delete' : ''
              }}</el-button>


          </el-form-item>
        </el-row>
      </el-form>
      <div class="table-content">
        <el-table class="tables" :size="contents.tableSize" :show-header="contents.tableShowHeader"
          :header-row-style="headerRowStyle" :header-cell-style="headerCellStyle" :border="contents.tableBorder"
          :fit="contents.tableFit" :stripe="contents.tableStripe" :row-style="rowStyle" :cell-style="cellStyle"
          :style="{ width: '100%', fontSize: contents.tableContentFontSize, color: contents.tableContentFontColor }"
          v-if="isAuth('carInfo', 'View')" :data="dataList" v-loading="dataListLoading"
          @selection-change="selectionChangeHandler">
          <el-table-column v-if="contents.tableSelection" type="selection" header-align="center" align="center"
            width="50">
          </el-table-column>
          <el-table-column label="#" v-if="contents.tableIndex" type="index" width="50" />
          <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign" prop="carName"
            header-align="center" label="Car Name">
            <template slot-scope="scope">
              {{ scope.row.carName }}
            </template>
          </el-table-column>
          <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign" prop="carType"
            header-align="center" label="Car Type">
            <template slot-scope="scope">
              {{ scope.row.carType }}
            </template>
          </el-table-column>
          <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign" prop="brand"
            header-align="center" label="Brand">
            <template slot-scope="scope">
              {{ scope.row.brand }}
            </template>
          </el-table-column>
          <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign" prop="bodyColor"
            header-align="center" label="Color">
            <template slot-scope="scope">
              {{ scope.row.bodyColor }}
            </template>
          </el-table-column>
          <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign" prop="seatingCapacity"
            header-align="center" label="Seats">
            <template slot-scope="scope">
              {{ scope.row.seatingCapacity }}
            </template>
          </el-table-column>
          <el-table-column :sortable="contents.tableSortable" :align="contents.tableAlign" prop="licensePlateNumber"
            header-align="center" label="Plate No.">
            <template slot-scope="scope">
              {{ scope.row.licensePlateNumber }}
            </template>
          </el-table-column>
          <el-table-column width="300" :align="contents.tableAlign" header-align="center" label="Actions">
            <template slot-scope="scope">
              <el-button v-if="isAuth('carInfo', 'View') && contents.tableBtnIcon == 1 && contents.tableBtnIconPosition == 1"
                icon="el-icon-tickets" @click="addOrUpdateHandler(scope.row.id, 'info')">{{ contents.tableBtnFont ==
                  1 ? 'Details' : '' }}</el-button>
              <el-button v-if="isAuth('carInfo', 'View') && contents.tableBtnIcon == 1 && contents.tableBtnIconPosition == 2"
                @click="addOrUpdateHandler(scope.row.id, 'info')">{{ contents.tableBtnFont == 1 ? 'Details' : '' }}</el-button>
              <el-button v-if="isAuth('carInfo', 'View') && contents.tableBtnIcon == 0"
                @click="addOrUpdateHandler(scope.row.id, 'info')">{{ contents.tableBtnFont == 1 ? 'Details' : '' }}</el-button>
              <el-button v-if="isAuth('carInfo', 'Deploy') && contents.tableBtnIcon == 1 && contents.tableBtnIconPosition == 1"
                icon="el-icon-tickets" @click="carReturnCrossAddOrUpdateHandler(scope.row, 'cross')">{{
                  contents.tableBtnFont == 1 ? 'Deploy' : '' }}</el-button>
              <el-button v-if="isAuth('carInfo', 'Deploy') && contents.tableBtnIcon == 1 && contents.tableBtnIconPosition == 2"
                @click="carReturnCrossAddOrUpdateHandler(scope.row, 'cross')">{{ contents.tableBtnFont == 1 ? 'Deploy' : ''
                }}</el-button>
              <el-button v-if="isAuth('carInfo', 'Deploy') && contents.tableBtnIcon == 0"
                @click="carReturnCrossAddOrUpdateHandler(scope.row, 'cross')">{{ contents.tableBtnFont == 1 ? 'Deploy' : ''
                }}</el-button>
              <el-button v-if="isAuth('carInfo', 'Edit') && contents.tableBtnIcon == 1 && contents.tableBtnIconPosition == 1"
                  @click="addOrUpdateHandler(scope.row.id)">{{ contents.tableBtnFont == 1 ? 'Edit' : ''
                }}</el-button>
              <el-button v-if="isAuth('carInfo', 'Edit') && contents.tableBtnIcon == 1 && contents.tableBtnIconPosition == 2"
                @click="addOrUpdateHandler(scope.row.id)">{{ contents.tableBtnFont == 1 ? 'Edit' : '' }}<i
                  class="el-icon-edit el-icon--right" /></el-button>
              <el-button v-if="isAuth('carInfo', 'Edit') && contents.tableBtnIcon == 0"
                @click="addOrUpdateHandler(scope.row.id)">{{ contents.tableBtnFont == 1 ? 'Edit' : '' }}</el-button>

              <el-button v-if="isAuth('carInfo', 'Delete') && contents.tableBtnIcon == 1 && contents.tableBtnIconPosition == 1"
                icon="el-icon-delete" @click="deleteHandler(scope.row.id)">{{ contents.tableBtnFont == 1 ? 'Delete' : ''
                }}</el-button>
              <el-button v-if="isAuth('carInfo', 'Delete') && contents.tableBtnIcon == 1 && contents.tableBtnIconPosition == 2"
                @click="deleteHandler(scope.row.id)">{{ contents.tableBtnFont == 1 ? 'Delete' : '' }}<i
                  class="el-icon-delete el-icon--right" /></el-button>
              <el-button v-if="isAuth('carInfo', 'Delete') && contents.tableBtnIcon == 0"
                @click="deleteHandler(scope.row.id)">{{ contents.tableBtnFont == 1 ? 'Delete' : '' }}</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination clsss="pages" :layout="layouts" @size-change="sizeChangeHandle"
          @current-change="currentChangeHandle" :current-page="pageIndex" :page-sizes="[10, 20, 50, 100]"
          :page-size="Number(contents.pageEachNum)" :total="totalPage" :small="contents.pageStyle"
          class="pagination-content" :background="contents.pageBtnBG"
          :style="{ textAlign: contents.pagePosition == 1 ? 'left' : contents.pagePosition == 2 ? 'center' : 'right' }"></el-pagination>
      </div>
    </div>
    <!-- add/edit page - pass parent's search method to child -->
    <add-or-update v-if="addOrUpdateFlag" :parent="this" ref="addOrUpdate"></add-or-update>

    <carReturnCrossAddOrUpdate v-if="carReturnCrossAddOrUpdateFlag" :parent="this" ref="carReturnCrossaddOrUpdate">
    </carReturnCrossAddOrUpdate>


  </div>
</template>
<script>
import AddOrUpdate from "./add-or-update";
import carReturnCrossAddOrUpdate from "../carDispense/add-or-update";
export default {
  data() {
    return {
      searchForm: {
        key: ""
      },
      form: {},
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      showFlag: true,
      sfshVisiable: false,
      shForm: {},
      chartVisiable: false,
      addOrUpdateFlag: false,
      carReturnCrossAddOrUpdateFlag: false,
      contents: { "searchBtnFontColor": "rgba(19, 20, 20, 1)", "pagePosition": "1", "inputFontSize": "14px", "inputBorderRadius": "4px", "tableBtnDelFontColor": "rgba(255, 73, 73, 1)", "tableBtnIconPosition": "2", "searchBtnHeight": "40px", "inputIconColor": "#C0C4CC", "searchBtnBorderRadius": "4px", "tableStripe": true, "btnAdAllWarnFontColor": "rgba(30, 136, 229, 1)", "tableBtnDelBgColor": "rgba(255, 255, 255, 1)", "searchBtnIcon": "1", "tableSize": "medium", "searchBtnBorderStyle": "solid", "tableSelection": true, "searchBtnBorderWidth": "1px", "tableContentFontSize": "14px", "searchBtnBgColor": "rgba(38, 198, 218, 1)", "inputTitleSize": "14px", "btnAdAllBorderColor": "#DCDFE6", "pageJumper": true, "btnAdAllIconPosition": "1", "searchBoxPosition": "1", "tableBtnDetailFontColor": "rgba(38, 198, 218, 1)", "tableBtnHeight": "40px", "pagePager": true, "searchBtnBorderColor": "rgba(255, 255, 255, 1)", "tableHeaderFontColor": "#909399", "inputTitle": "1", "tableBtnBorderRadius": "10px", "btnAdAllFont": "1", "btnAdAllDelFontColor": "rgba(255, 73, 73, 1)", "tableBtnIcon": "1", "btnAdAllHeight": "40px", "btnAdAllWarnBgColor": "rgba(255, 255, 255, 1)", "btnAdAllBorderWidth": "1px", "tableStripeFontColor": "#606266", "tableBtnBorderStyle": "solid", "inputHeight": "40px", "btnAdAllBorderRadius": "4px", "btnAdAllDelBgColor": "rgba(255, 255, 255, 1)", "pagePrevNext": true, "btnAdAllAddBgColor": "rgba(255, 255, 255, 1)", "searchBtnFont": "1", "tableIndex": true, "btnAdAllIcon": "1", "tableSortable": false, "pageSizes": true, "tableFit": true, "pageBtnBG": true, "searchBtnFontSize": "14px", "tableBtnEditBgColor": "rgba(255, 255, 255, 1)", "inputBorderWidth": "1px", "inputFontPosition": "1", "inputFontColor": "#333", "pageEachNum": 10, "tableHeaderBgColor": "#fff", "inputTitleColor": "#333", "btnAdAllBoxPosition": "1", "tableBtnDetailBgColor": "rgba(255, 255, 255, 1)", "inputIcon": "1", "searchBtnIconPosition": "1", "btnAdAllFontSize": "14px", "inputBorderStyle": "solid", "inputBgColor": "#fff", "pageStyle": false, "pageTotal": true, "btnAdAllAddFontColor": "rgba(38, 198, 218, 1)", "tableBtnFont": "1", "tableContentFontColor": "#606266", "inputBorderColor": "#DCDFE6", "tableShowHeader": true, "tableBtnFontSize": "10px", "tableBtnBorderColor": "#DCDFE6", "inputIconPosition": "1", "tableBorder": true, "btnAdAllBorderStyle": "solid", "tableBtnBorderWidth": "1px", "tableStripeBgColor": "#F5F7FA", "tableBtnEditFontColor": "rgba(30, 136, 229, 1)", "tableAlign": "center" },
      layouts: '',


    };
  },
  created() {
    this.init();
    this.getDataList();
    this.contentStyleChange()
  },
  mounted() {

  },
  filters: {
    htmlfilter: function (val) {
      return val.replace(/<[^>]*>/g).replace(/undefined/g, '');
    }
  },
  components: {
    AddOrUpdate,
    carReturnCrossAddOrUpdate,
  },
  methods: {
    contentStyleChange() {
      this.contentSearchStyleChange()
      this.contentBtnAdAllStyleChange()
      this.contentSearchBtnStyleChange()
      this.contentTableBtnStyleChange()
      this.contentPageStyleChange()
    },
    contentSearchStyleChange() {
      this.$nextTick(() => {
        document.querySelectorAll('.form-content .slt .el-input__inner').forEach(el => {
          let textAlign = 'left'
          if (this.contents.inputFontPosition == 2) textAlign = 'center'
          if (this.contents.inputFontPosition == 3) textAlign = 'right'
          el.style.textAlign = textAlign
          el.style.height = this.contents.inputHeight
          el.style.lineHeight = this.contents.inputHeight
          el.style.color = this.contents.inputFontColor
          el.style.fontSize = this.contents.inputFontSize
          el.style.borderWidth = this.contents.inputBorderWidth
          el.style.borderStyle = this.contents.inputBorderStyle
          el.style.borderColor = this.contents.inputBorderColor
          el.style.borderRadius = this.contents.inputBorderRadius
          el.style.backgroundColor = this.contents.inputBgColor
        })
        if (this.contents.inputTitle) {
          document.querySelectorAll('.form-content .slt .el-form-item__label').forEach(el => {
            el.style.color = this.contents.inputTitleColor
            el.style.fontSize = this.contents.inputTitleSize
            el.style.lineHeight = this.contents.inputHeight
          })
        }
        setTimeout(() => {
          document.querySelectorAll('.form-content .slt .el-input__prefix').forEach(el => {
            el.style.color = this.contents.inputIconColor
            el.style.lineHeight = this.contents.inputHeight
          })
          document.querySelectorAll('.form-content .slt .el-input__suffix').forEach(el => {
            el.style.color = this.contents.inputIconColor
            el.style.lineHeight = this.contents.inputHeight
          })
          document.querySelectorAll('.form-content .slt .el-input__icon').forEach(el => {
            el.style.lineHeight = this.contents.inputHeight
          })
        }, 10)

      })
    },
    // 搜索按钮
    contentSearchBtnStyleChange() {
      this.$nextTick(() => {
        document.querySelectorAll('.form-content .slt .el-button--success').forEach(el => {
          el.style.height = this.contents.searchBtnHeight
          el.style.color = this.contents.searchBtnFontColor
          el.style.fontSize = this.contents.searchBtnFontSize
          el.style.borderWidth = this.contents.searchBtnBorderWidth
          el.style.borderStyle = this.contents.searchBtnBorderStyle
          el.style.borderColor = this.contents.searchBtnBorderColor
          el.style.borderRadius = this.contents.searchBtnBorderRadius
          el.style.backgroundColor = this.contents.searchBtnBgColor
        })
      })
    },
    // 新增、批量删除
    contentBtnAdAllStyleChange() {
      this.$nextTick(() => {
        document.querySelectorAll('.form-content .ad .el-button--success').forEach(el => {
          el.style.height = this.contents.btnAdAllHeight
          el.style.color = this.contents.btnAdAllAddFontColor
          el.style.fontSize = this.contents.btnAdAllFontSize
          el.style.borderWidth = this.contents.btnAdAllBorderWidth
          el.style.borderStyle = this.contents.btnAdAllBorderStyle
          el.style.borderColor = this.contents.btnAdAllBorderColor
          el.style.borderRadius = this.contents.btnAdAllBorderRadius
          el.style.backgroundColor = this.contents.btnAdAllAddBgColor
        })
        document.querySelectorAll('.form-content .ad .el-button--danger').forEach(el => {
          el.style.height = this.contents.btnAdAllHeight
          el.style.color = this.contents.btnAdAllDelFontColor
          el.style.fontSize = this.contents.btnAdAllFontSize
          el.style.borderWidth = this.contents.btnAdAllBorderWidth
          el.style.borderStyle = this.contents.btnAdAllBorderStyle
          el.style.borderColor = this.contents.btnAdAllBorderColor
          el.style.borderRadius = this.contents.btnAdAllBorderRadius
          el.style.backgroundColor = this.contents.btnAdAllDelBgColor
        })
        document.querySelectorAll('.form-content .ad .el-button--warning').forEach(el => {
          el.style.height = this.contents.btnAdAllHeight
          el.style.color = this.contents.btnAdAllWarnFontColor
          el.style.fontSize = this.contents.btnAdAllFontSize
          el.style.borderWidth = this.contents.btnAdAllBorderWidth
          el.style.borderStyle = this.contents.btnAdAllBorderStyle
          el.style.borderColor = this.contents.btnAdAllBorderColor
          el.style.borderRadius = this.contents.btnAdAllBorderRadius
          el.style.backgroundColor = this.contents.btnAdAllWarnBgColor
        })
      })
    },
    // 表格
    rowStyle({ row, rowIndex }) {
      if (rowIndex % 2 == 1) {
        if (this.contents.tableStripe) {
          return { color: this.contents.tableStripeFontColor }
        }
      } else {
        return ''
      }
    },
    cellStyle({ row, rowIndex }) {
      if (rowIndex % 2 == 1) {
        if (this.contents.tableStripe) {
          return { backgroundColor: this.contents.tableStripeBgColor }
        }
      } else {
        return ''
      }
    },
    headerRowStyle({ row, rowIndex }) {
      return { color: this.contents.tableHeaderFontColor }
    },
    headerCellStyle({ row, rowIndex }) {
      return { backgroundColor: this.contents.tableHeaderBgColor }
    },
    // 表格按钮
    contentTableBtnStyleChange() {
    },
    // 分页
    contentPageStyleChange() {
      let arr = []

      if (this.contents.pageTotal) arr.push('total')
      if (this.contents.pageSizes) arr.push('sizes')
      if (this.contents.pagePrevNext) {
        arr.push('prev')
        if (this.contents.pagePager) arr.push('pager')
        arr.push('next')
      }
      if (this.contents.pageJumper) arr.push('jumper')
      this.layouts = arr.join()
      this.contents.pageEachNum = 10
    },

    carReturnCrossAddOrUpdateHandler(row, type) {
      this.showFlag = false;
      this.addOrUpdateFlag = false;
      this.carReturnCrossAddOrUpdateFlag = true;
      this.$storage.set('crossObj', row);
      this.$storage.set('crossTable', 'carInfo');
      this.$nextTick(() => {
        this.$refs.carReturnCrossaddOrUpdate.init(row.id, type);
      });
    },
    init() {
    },
    search() {
      this.pageIndex = 1;
      this.getDataList();
    },
    // get data list
    getDataList() {
      this.dataListLoading = true;
      let params = {
        page: this.pageIndex,
        limit: this.pageSize,
        sort: 'id',
      }
      if (this.searchForm.carName != '' && this.searchForm.carName != undefined) {
        params['carName'] = '%' + this.searchForm.carName + '%'
      }
      if (this.searchForm.licensePlateNumber != '' && this.searchForm.licensePlateNumber != undefined) {
        params['licensePlateNumber'] = '%' + this.searchForm.licensePlateNumber + '%'
      }
      this.$http({
        url: "carInfo/page",
        method: "get",
        params: params
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.dataList = data.data.list;
          this.totalPage = data.data.total;
          console.log(this.dataList)
        } else {
          this.dataList = [];
          this.totalPage = 0;
        }
        this.dataListLoading = false;
      });
    },
    // page size change
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getDataList();
    },
    // current page change
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getDataList();
    },
    // multi-select
    selectionChangeHandler(val) {
      this.dataListSelections = val;
    },
    // add or edit
    addOrUpdateHandler(id, type) {
      this.showFlag = false;
      this.addOrUpdateFlag = true;
      this.crossAddOrUpdateFlag = false;
      if (type != 'info') {
        type = 'else';
      }
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id, type);
      });
    },
    // download
    download(file) {
      window.open(`${file}`)
    },
    // delete
    deleteHandler(id) {
      var ids = id
        ? [Number(id)]
        : this.dataListSelections.map(item => {
          return Number(item.id);
        });
      this.$confirm(`Confirm ${id ? "delete" : "batch delete"}?`, "Notice", {
        confirmButtonText: "OK",
        cancelButtonText: "Cancel",
        type: "warning"
      }).then(() => {
        this.$http({
          url: "carInfo/delete",
          method: "post",
          data: ids
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.$message({
              message: "Success",
              type: "success",
              duration: 1000,
              onClose: () => {
                this.search();
              }
            });
          } else {
            this.$message.error(data.msg);
          }
        });
      });
    },
  }

};
</script>
<style lang="scss" scoped>
.slt {
  margin: 0 !important;
  display: flex;
}

.ad {
  margin: 0 !important;
  display: flex;
}

.pages {
  & ::v-deep el-pagination__sizes {
    & ::v-deep el-input__inner {
      height: 22px;
      line-height: 22px;
    }
  }
}


.el-button+.el-button {
  margin: 0;
}

.tables {
  & ::v-deep .el-button--success {
    height: 40px;
    color: rgba(38, 198, 218, 1);
    font-size: 10px;
    border-width: 1px;
    border-style: solid;
    border-color: #DCDFE6;
    border-radius: 10px;
    background-color: rgba(255, 255, 255, 1);
  }

  & ::v-deep .el-button--primary {
    height: 40px;
    color: rgba(30, 136, 229, 1);
    font-size: 10px;
    border-width: 1px;
    border-style: solid;
    border-color: #DCDFE6;
    border-radius: 10px;
    background-color: rgba(255, 255, 255, 1);
  }

  & ::v-deep .el-button--danger {
    height: 40px;
    color: rgba(255, 73, 73, 1);
    font-size: 10px;
    border-width: 1px;
    border-style: solid;
    border-color: #DCDFE6;
    border-radius: 10px;
    background-color: rgba(255, 255, 255, 1);
  }

  & ::v-deep .el-button {
    margin: 4px;
  }
}
</style>
