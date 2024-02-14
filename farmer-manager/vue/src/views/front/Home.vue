<template>
  <div class="main-content">
    <div>
      <el-carousel :interval="4000" type="card" height="300px">
        <el-carousel-item v-for="item in carouselData" :key="item">
          <img :src="item" alt="" style="width: 100%; height: 300px; border-radius: 5px">
        </el-carousel-item>
      </el-carousel>
    </div>
    <div style="width: 80%; margin: 20px auto;">
      <div style="display: flex">
        <div style="width: 50%; height: 330px; margin-right: 17px; margin-top: 10px">
          <el-carousel height="330px">
            <el-carousel-item v-for="item in left" :key="item">
              <img :src="item" alt="" style="width: 100%; height: 330px">
            </el-carousel-item>
          </el-carousel>
        </div>
        <div style="width: 50%; height: 330px; margin-left: 17px">
          <el-tabs v-model="activeName">
            <el-tab-pane label="扶贫资讯" name="first">
              <div style="display: flex; line-height: 48px; height: 48px" v-for="item in informationData">
                <div style="flex: 1; font-size: 17px; width: 0; cursor: pointer;" class="overflowShow" @click="navTo('/front/informationDetail?id=' + item.id)">{{ item.name }}</div>
                <div style="width: 150px; text-align: right; color: #a9a9b8; font-size: 15px">{{ item.time }}</div>
              </div>
            </el-tab-pane>
            <el-tab-pane label="扶贫政策" name="second">
              <div style="display: flex; line-height: 48px; height: 48px" v-for="item in policyData">
                <div style="flex: 1; font-size: 17px; width: 0; cursor: pointer;" class="overflowShow" @click="navTo('/front/policyDetail?id=' + item.id)">{{ item.name }}</div>
                <div style="width: 150px; text-align: right; color: #a9a9b8; font-size: 15px">{{ item.time }}</div>
              </div>
            </el-tab-pane>
          </el-tabs>
        </div>
      </div>

      <div style="text-align: center; color: #19a14b; font-size: 25px; font-weight: 550; margin: 40px 0">魅力农家乐</div>
      <div>
        <el-row :gutter="20">
          <el-col :span="8" style="margin-bottom: 50px" v-for="item in agritainmentData">
            <img :src="item.img" alt="" style="width: 100%; height: 100%; border-radius: 10px; cursor: pointer;" @click="navTo('/front/agritainmentDetail?id=' + item.id)">
            <div style="text-align: center; font-size: 16px; font-weight: 530; margin-top: 10px">{{ item.name }}</div>
          </el-col>
        </el-row>
      </div>
      <div style="text-align: center; color: #19a14b; font-size: 25px; font-weight: 550">优质农产品</div>
      <div style="height: 1000px">
        <el-col :span="4" style="margin-top: 30px" v-for="item in goodsData">
          <img :src="item.img" alt="" style="width: 100%; height: 180px; border-radius: 10px; border: 1px solid #cccccc">
          <div style="text-align: left; font-weight: 530; font-size: 16px; margin-top: 10px" class="overflowShow">{{ item.name }}
           </div>
          <div style="margin-top: 10px;display: flex;justify-content: space-between;" >
            <span style="color: red;">￥ {{ item.price }}</span>
            <span style="color: red;">库存 {{ item.num }}</span>

          </div>
          <div style="margin-top: 20px"><el-button type="warning" size="mini" @click="buy(item)" :disabled="item.num === 0">购买</el-button></div>

        </el-col>
      </div>

    </div>
  </div>
</template>

<script>

export default {

  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      carouselData: [
        require('@/assets/imgs/lun-1.png'),
        require('@/assets/imgs/lun-2.png'),
        require('@/assets/imgs/lun-3.png'),
      ],
      left: [
        require('@/assets/imgs/lun-1.png'),
        require('@/assets/imgs/lun-2.png'),
        require('@/assets/imgs/lun-3.png'),
      ],
      informationData: [],
      policyData: [],
      agritainmentData:[],
      goodsData: [],
      activeName: 'first'
    }
  },

  // methods：本页面所有的点击事件或者其他函数定义区
  mounted() {
    this.loadInformation()
    this.loadPolicy()
    this.loadAgritainment()
    this.loadGoods()
  },
// methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    buy(goods) {
      if (this.user.role === 'ADMIN') {
        this.$message.warning('您是管理员，不支持该操作')
        return
      }
      let data = {
        goodsId: goods.id,
        userId: this.user.id,
        price: goods.price,
        status: '待发货'
      }
      this.$request.post('/orders/add', data).then(res => {
        if (res.code === '200') {
          this.$message.success('购买成功')
          this.loadGoods()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    loadPolicy() {
      this.$request.get('/policy/top6').then(res => {
        if (res.code === '200') {
          this.policyData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    loadInformation() {
      this.$request.get('/information/top6').then(res => {
        if (res.code === '200') {
          this.informationData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },

    navTo(url) {
      location.href = url
    },
    loadAgritainment() {
      this.$request.get('/agritainment/top6').then(res => {
        if (res.code === '200') {
          this.agritainmentData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    loadGoods() {
      this.$request.get('/goods/selectAll').then(res => {
        if (res.code === '200') {
          this.goodsData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
  },

}
</script>

<style scoped>
.overflowShow {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>
