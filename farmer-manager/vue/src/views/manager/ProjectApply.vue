<template>
  <div>
    <div class="search">
      <el-select v-model="status" placeholder="请选择状态" style="width: 200px">
        <el-option label="待审核" value="待审核"></el-option>
        <el-option label="审核通过" value="审核通过"></el-option>
        <el-option label="审核不通过" value="审核不通过"></el-option>
      </el-select>
      <el-button type="info" plain style="margin-left: 10px" @click="load(1)">查询</el-button>
      <el-button type="warning" plain style="margin-left: 10px" @click="reset">重置</el-button>
    </div>

    <div class="table">
      <el-table :data="tableData" stripe>
        <el-table-column prop="id" label="序号" width="80" align="center" sortable></el-table-column>
        <el-table-column prop="userName" label="申请用户"></el-table-column>
        <el-table-column prop="content" label="申请说明" show-overflow-tooltip></el-table-column>
        <el-table-column prop="time" label="申请时间"></el-table-column>
        <el-table-column prop="file" label="申请资料">
          <template v-slot="scope">
            <el-button type="warning" size="mini" @click="downPoor(scope.row.file)">下载资料</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="status" label="审核状态"></el-table-column>
        <el-table-column prop="descr" label="审核说明"></el-table-column>

        <el-table-column label="操作" width="180" align="center">
          <template v-slot="scope">
            <el-button plain type="primary" size="mini" :disabled="scope.row.status !== '待审核'" @click="check(scope.row)">审核</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="pagination">
        <el-pagination
            background
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[5, 10, 20]"
            :page-size="pageSize"
            layout="total, prev, pager, next"
            :total="total">
        </el-pagination>
      </div>
    </div>
    <el-dialog title="贫困户审核" :visible.sync="fromVisible" width="40%" :close-on-click-modal="false" destroy-on-close>
      <el-form label-width="100px" style="padding-right: 50px" :model="form">
        <el-form-item prop="status" label="审核状态">
          <el-select v-model="form.status" placeholder="请选择状态" style="width: 100%">
            <el-option label="待审核" value="待审核"></el-option>
            <el-option label="审核通过" value="审核通过"></el-option>
            <el-option label="审核不通过" value="审核不通过"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="descr" label="审核说明">
          <el-input type="textarea" :rows="5" v-model="form.descr" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="fromVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "ProjectApply",
  data() {
    return {
      tableData: [],  // 所有的数据
      pageNum: 1,   // 当前的页码
      pageSize: 10,  // 每页显示的个数
      total: 0,
      status: null,
      form: {},
      fromVisible: false,
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
    }
  },
  created() {
    this.load(1)
  },
  methods: {
    check(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.fromVisible = true
    },
    load(pageNum) {  // 分页查询
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/projectapply/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          status: this.status,
        }
      }).then(res => {
        this.tableData = res.data?.list
        this.total = res.data?.total
      })
    },
    save() {
      this.$request.put('/projectapply/update', this.form).then(res => {
        if (res.code === '200') {
          this.$message.success('操作成功')
          this.fromVisible = false
          this.load(1)
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    reset() {
      this.status = null
      this.load(1)
    },
    handleCurrentChange(pageNum) {
      this.load(pageNum)
    },
    downPoor(url) {
      location.href = url
    }
  }
}
</script>

<style scoped>

</style>
