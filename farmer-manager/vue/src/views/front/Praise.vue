<template>
  <div class="main-content">
    <div class="table" style="width: 80%; margin: 0 auto">
      <div style="font-size: 17px; margin: 20px 0">我的历史点赞（{{ total }}）</div>
      <el-table :data="tableData" strip>
        <el-table-column prop="id" label="序号" width="70" align="center" sortable></el-table-column>
        <el-table-column prop="userName" label="点赞用户" width="150"></el-table-column>
        <el-table-column prop="policyName" label="点赞政策" show-overflow-tooltip>
          <template v-slot="scope">
            <a :href="'/front/policyDetail?id=' + scope.row.policyId">{{scope.row.policyName}}</a>
          </template>
        </el-table-column>
        <el-table-column prop="time" label="点赞时间" width="200"></el-table-column>
      </el-table>

      <div class="pagination" style="margin-top: 20px">
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
  </div>
</template>

<script>
export default {

  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      tableData: [],
      pageNum: 1,
      pageSize: 5,
      total: 0,
    }
  },
  mounted() {
    this.load(1)
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    load(pageNum) {  // 分页查询
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/praise/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          userId: this.user.id,
        }
      }).then(res => {
        if (res.code === '200') {
          this.tableData = res.data?.list
          this.total = res.data?.total
        } else {
          this.$message.error(res.msg)
        }
      })
    },

    handleCurrentChange(pageNum) {
      this.load(pageNum)
    },
  }
}
</script>

