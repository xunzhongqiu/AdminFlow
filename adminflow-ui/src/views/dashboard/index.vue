<template>
 <div class="page-container">
  <div class="heading"><div><h1 class="page-title">工作台</h1><p class="page-subtitle">欢迎回来，今天也要高效完成工作。</p></div><el-button :icon="Refresh" @click="load">刷新数据</el-button></div>
  <el-row :gutter="18" class="stats">
   <el-col :xs="24" :sm="12" :lg="6"><div class="stat-card"><div class="stat-top"><span>用户总数</span><div class="stat-icon blue"><el-icon><User /></el-icon></div></div><div class="stat-value">{{overview.userTotal ?? '--'}}</div><div class="stat-foot"><span class="up">↑ 12.8%</span><span>较上月</span></div></div></el-col>
   <el-col :xs="24" :sm="12" :lg="6"><div class="stat-card"><div class="stat-top"><span>启用用户</span><div class="stat-icon green"><el-icon><CircleCheck /></el-icon></div></div><div class="stat-value">{{overview.userEnabled ?? '--'}}</div><div class="stat-foot"><span class="up">正常</span><span>当前状态</span></div></div></el-col>
   <el-col :xs="24" :sm="12" :lg="6"><div class="stat-card"><div class="stat-top"><span>今日访问</span><div class="stat-icon purple"><el-icon><View /></el-icon></div></div><div class="stat-value">{{overview.todayVisits ?? '--'}}</div><div class="stat-foot"><span class="up">↑ 8.2%</span><span>较昨日</span></div></div></el-col>
   <el-col :xs="24" :sm="12" :lg="6"><div class="stat-card"><div class="stat-top"><span>待处理事项</span><div class="stat-icon orange"><el-icon><Clock /></el-icon></div></div><div class="stat-value">{{overview.pendingTasks ?? '--'}}</div><div class="stat-foot"><span>需要关注</span><span>当前任务</span></div></div></el-col>
  </el-row>
  <el-row :gutter="18" class="content-row">
   <el-col :xs="24" :lg="16"><div class="panel chart-panel"><div class="panel-heading"><div><b>访问趋势</b><p>最近 7 天的访问数据概览</p></div><el-tag effect="plain">最近 7 天</el-tag></div><div ref="chartRef" class="chart"></div></div></el-col>
   <el-col :xs="24" :lg="8"><div class="panel"><div class="panel-heading"><div><b>系统概览</b><p>平台运行状态</p></div><el-tag type="success" effect="light">运行正常</el-tag></div><div class="system-list"><div><span>API 服务</span><b><i class="dot"></i>正常</b></div><div><span>数据库</span><b><i class="dot"></i>已连接</b></div><div><span>缓存服务</span><b><i class="dot"></i>待接入监控</b></div><div><span>系统版本</span><b>v1.0.0</b></div></div></div></el-col>
  </el-row>
  <div class="panel quick-panel"><div class="panel-heading"><div><b>快捷入口</b><p>快速访问常用功能</p></div></div><div class="quick-links"><router-link to="/system/users"><div class="quick-icon"><el-icon><User /></el-icon></div><span>用户管理</span><el-icon class="arrow"><ArrowRight /></el-icon></router-link><a><div class="quick-icon violet"><el-icon><Lock /></el-icon></div><span>权限配置</span><el-tag size="small">规划中</el-tag></a><a><div class="quick-icon orange-bg"><el-icon><Document /></el-icon></div><span>操作日志</span><el-tag size="small">规划中</el-tag></a></div></div>
 </div>
</template>
<script setup lang="ts">
import { onMounted,onBeforeUnmount,ref,nextTick } from 'vue'
import * as echarts from 'echarts'
import { User,CircleCheck,View,Clock,Refresh,ArrowRight,Lock,Document } from '@element-plus/icons-vue'
import { getOverview } from '@/api/dashboard'
const overview=ref<any>({}),chartRef=ref<HTMLElement>(),chart=ref<echarts.ECharts>()
async function load(){try{const res:any=await getOverview();overview.value=res.data}catch{}}
function renderChart(){if(!chartRef.value)return;chart.value=echarts.init(chartRef.value);chart.value.setOption({tooltip:{trigger:'axis'},grid:{left:12,right:18,top:25,bottom:8,containLabel:true},xAxis:{type:'category',boundaryGap:false,data:['周一','周二','周三','周四','周五','周六','周日'],axisLine:{lineStyle:{color:'#edf0f5'}},axisLabel:{color:'#929bad'}},yAxis:{type:'value',splitLine:{lineStyle:{color:'#f0f2f6'}},axisLabel:{color:'#929bad'}},series:[{data:[320,420,380,560,490,680,740],type:'line',smooth:true,symbol:'none',lineStyle:{width:3,color:'#5b83ed'},areaStyle:{color:new echarts.graphic.LinearGradient(0,0,0,1,[{offset:0,color:'rgba(91,131,237,.22)'},{offset:1,color:'rgba(91,131,237,0)'}])}}]})}
function resize(){chart.value?.resize()}
onMounted(async()=>{await load();await nextTick();renderChart();window.addEventListener('resize',resize)})
onBeforeUnmount(()=>{window.removeEventListener('resize',resize);chart.value?.dispose()})
</script>
<style scoped>
.heading{display:flex;justify-content:space-between;align-items:center;margin-bottom:24px}.stats{row-gap:18px}.stat-top{display:flex;justify-content:space-between;align-items:center;color:#7d899c;font-size:12px}.stat-icon{width:36px;height:36px;border-radius:10px;display:grid;place-items:center;font-size:18px}.blue{background:#edf3ff;color:#5b83ed}.green{background:#eaf8f1;color:#36a879}.purple{background:#f2efff;color:#8971db}.orange{background:#fff4e9;color:#e89a4b}.stat-value{font-size:29px;font-weight:750;letter-spacing:-1px;margin:9px 0;color:#1b2940}.stat-foot{font-size:10px;color:#a0a9b8;display:flex;gap:9px}.up{color:#2fa879}.content-row{margin-top:18px}.panel{height:100%}.panel-heading{display:flex;align-items:flex-start;justify-content:space-between}.panel-heading b{font-size:14px;color:#26344b}.panel-heading p{font-size:11px;color:#9aa4b3;margin:7px 0 0}.chart{height:265px;margin-top:10px}.system-list{margin-top:22px}.system-list>div{height:48px;border-bottom:1px solid #f0f2f6;display:flex;justify-content:space-between;align-items:center;font-size:12px;color:#8994a5}.system-list>div:last-child{border:0}.system-list b{font-weight:550;color:#45536a}.dot{display:inline-block;width:6px;height:6px;border-radius:50%;background:#36b37e;margin-right:7px}.quick-panel{margin-top:18px}.quick-links{display:grid;grid-template-columns:repeat(3,1fr);gap:12px;margin-top:18px}.quick-links>a{display:flex;align-items:center;gap:12px;padding:13px;border:1px solid #edf0f5;border-radius:10px;text-decoration:none;color:#4b5870;font-size:12px}.quick-icon{width:34px;height:34px;border-radius:9px;background:#edf3ff;color:#5b83ed;display:grid;place-items:center;font-size:17px}.quick-icon.violet{background:#f2efff;color:#8971db}.quick-icon.orange-bg{background:#fff4e9;color:#e89a4b}.quick-links .arrow{margin-left:auto;color:#aab3c1}.quick-links :deep(.el-tag){margin-left:auto}
@media(max-width:700px){.page-container{padding:20px 15px}.quick-links{grid-template-columns:1fr}.heading{align-items:flex-start}.chart{height:220px}}
</style>
