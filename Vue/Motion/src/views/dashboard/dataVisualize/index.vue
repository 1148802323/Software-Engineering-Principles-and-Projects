<script setup lang="ts" name="dataVisualize">
import Pie from "./components/pie.vue";
import { ref, onMounted } from "vue";
import { getUserTotal } from "@/api/modules/user";

const totalUsers = ref(0);
const vipUsers = ref(0);
const normalUsers = ref(0);

onMounted(() => {
  fetchData();
});

const fetchData = async () => {
  try {
    const response = await getUserTotal();
    const data = response.data[0];
    totalUsers.value = data.totalUsers;
    vipUsers.value = data.vipUsers;
    normalUsers.value = data.normalUsers;
  } catch (error) {
    console.error("Error fetching data:", error);
  }
};
</script>

<template>
  <div class="dataVisualize-box">
    <div class="card top-box">
      <div class="top-title">用户统计</div>
      <div class="top-content">
        <el-row :gutter="40">
          <el-col class="mb40" :xs="24" :sm="12" :md="12" :lg="6" :xl="6">
            <div class="item-left sle">
              <span class="left-title">用户总数</span>
              <div class="img-box">
                <img src="./images/book-sum.png" alt="" />
              </div>
              <span class="left-number">{{ totalUsers }}</span>
            </div>
          </el-col>
          <el-col class="mb40" :xs="24" :sm="12" :md="12" :lg="8" :xl="8">
            <div class="item-center">
              <div class="gitee-traffic traffic-box">
                <div class="traffic-img">
                  <img src="./images/add_person.png" alt="" />
                </div>
                <span class="item-value">{{ vipUsers }}</span>
                <span class="traffic-name sle">VIP用户数量</span>
              </div>
              <div class="gitHub-traffic traffic-box">
                <div class="traffic-img">
                  <img src="./images/add_team.png" alt="" />
                </div>
                <span class="item-value">{{ normalUsers }}</span>
                <span class="traffic-name sle">普通用户数量</span>
              </div>
            </div>
          </el-col>
          <el-col class="mb40" :xs="24" :sm="24" :md="24" :lg="10" :xl="10">
            <div class="item-right">
              <div class="echarts-title">VIP / 普通 用户占比</div>
              <div class="book-echarts">
                <Pie :vip-users="vipUsers" :normal-users="normalUsers" />
              </div>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
@import "./index.scss";
</style>
