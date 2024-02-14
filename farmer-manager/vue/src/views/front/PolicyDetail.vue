<template>
  <div class="main-content">
    <div style="width: 65%; margin: 20px auto">
      <div style="text-align: center">
        <div style="font-size: 22px">{{ policyData.name }}</div>
        <div style="margin-top: 20px; color: #666666">
          发布时间：{{ policyData.time }}
          <span style="margin: 0 30px">点赞： {{ policyData.num }}</span>
          <img v-if="isPraise" src="@/assets/imgs/praise_yes.png" alt="" style="width: 25px" @click="praise">
          <img v-else src="@/assets/imgs/praise_no.png" alt="" style="width: 25px" @click="praise">

        </div>
      </div>
      <div v-html="policyData.content" class="w-e-text w-e-text-container" style="margin-top: 30px"></div>
      <!--   评论区   -->
      <div style="margin-top: 50px; font-size: 18px">欢迎发表您宝贵的意见</div>
      <div style="margin-top: 20px">
        <el-input type="textarea" :rows="5" v-model="content" autocomplete="off"></el-input>
      </div>
      <div style="margin-top: 10px; text-align: right">
        <el-button type="primary" @click="submit(content, 0)">提 交</el-button>
      </div>
      <div style="margin-top: 20px">
        <el-row :gutter="10" style="margin-bottom: 30px" v-for="item in commentData">
          <el-col :span="4">
            <div style="display: flex; align-items: center">
              <img :src="item.avatar" alt="" style="width: 40px; height: 40px; border-radius: 50%">
              <div style="margin-left: 5px">{{ item.userName }}</div>
            </div>
          </el-col>
          <el-col :span="20">
            <el-row style="height: 40px; line-height: 40px">
              <el-col :span="19" class="overflowShow">{{ item.content }}</el-col>
              <el-col :span="5" style="text-align: right">{{ item.time }}</el-col>
            </el-row>
            <el-row style="height: 40px; line-height: 40px; margin-bottom: 10px" v-for="child in item.children">
              <el-col :span="4">
                <div style="display: flex; align-items: center">
                  <img :src="child.avatar" alt="" style="width: 40px; height: 40px; border-radius: 50%">
                  <div style="margin-left: 5px">{{ child.userName }}</div>
                </div>
              </el-col>
              <el-col :span="15" class="overflowShow">{{ child.content }}</el-col>
              <el-col :span="5" style="text-align: right">{{ child.time }}</el-col>
            </el-row>
            <div style="margin-top: 20px">
              <el-input v-model="item.tmp" autocomplete="off" style="width: 60%; margin-right: 20px"></el-input>
              <el-button type="primary" @click="submit(item.tmp, item.id)">回 复</el-button>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script>
import E from 'wangeditor'
export default {

  data() {
    let policyId = this.$route.query.id
    return {
      policyId: policyId,
      policyData: {},
      isPraise: false,
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      content: null,
      temp:null,
      commentData:null
   }
  },
  mounted() {
    this.loadInformation()
    this.checkPraise()
    this.loadComment()
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    loadComment() {
      this.$request.get('/comment/selectByPolicyId?id=' + this.policyId).then(res => {
        if (res.code === '200') {
          this.commentData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    submit(content, parentId) {
      if (!content) {
        this.$message.warning('请输入内容')
        return
      }
      let data = {
        userId: this.user.id,
        policyId: this.policyId,
        parentId: parentId,
        content: content
      }
      this.$request.post('/comment/add', data).then(res => {
        if (res.code === '200') {
          this.$message.success('评论成功')
          this.content = null
          this.loadComment()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    praise() {
      let data = {
        userId: this.user.id,
        policyId: this.policyId
      }
      this.$request.post('/praise/add', data).then(res => {
        if (res.code === '200') {
          this.$message.success('操作成功')
          this.loadInformation()
          this.checkPraise()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    checkPraise() {
      this.$request.get('/praise/selectAll', {
        params: {
          userId: this.user.id,
          policyId: this.policyId
        }
      }).then(res => {
        if (res.code === '200') {
          if (res.data.length > 0) {
            this.isPraise = true
          } else {
            this.isPraise = false
          }
        } else {
          this.$message.error(res.msg)
        }
      })
    },

    loadInformation() {
      this.$request.get('/policy/selectById/' + this.policyId).then(res => {
        if (res.code === '200') {
          this.policyData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },

  }
}
</script>

<style>
p {
  color: #3a3c40 !important;
  line-height: 30px !important;
}
ol {
  color: #3a3c40 !important;
  line-height: 30px !important;
  font-size: 15px !important;
}
</style>
