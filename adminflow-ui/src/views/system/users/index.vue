<template>
 <div class="page-container">
  <div class="heading"><div><h1 class="page-title">用户管理</h1><p class="page-subtitle">管理系统用户、账号状态及基础资料。</p></div><el-button type="primary" :icon="Plus" disabled>新增用户（开发中）</el-button></div>
  <div class="panel">
   <div class="toolbar"><el-input v-model="keyword" clearable placeholder="搜索用户名或昵称" :prefix-icon="Search" style="max-width:280px" @keyup.enter="search"/><el-button type="primary" plain @click="search">查询</el-button><el-button @click="reset">重置</el-button></div>
   <el-table :data="rows" v-loading="loading" style="width:100%" row-key="id">
    <el-table-column prop="id" label="ID" width="80"/>
    <el-table-column label="用户" min-width="180"><template #default="{row}"><div class="user-cell"><div class="user-avatar">{{(row.nickname||row.username||'U').slice(0,1)}}</div><div><b>{{row.nickname}}</b><small>@{{row.username}}</small></div></div></template></el-table-column>
    <el-table-column prop="email" label="邮箱" min-width="180"/>
    <el-table-column prop="phone" label="手机号" min-width="130"/>
    <el-table-column label="状态" width="110"><template #default="{row}"><el-tag :type="row.status===1?'success':'info'" effect="light">{{row.status===1?'启用':'禁用'}}</el-tag></template></el-table-column>
    <el-table-column prop="createTime" label="创建时间" min-width="170"/>
    <el-table-column label="操作" width="100" fixed="right"><template #default><el-button link type="primary" disabled>编辑</el-button></template></el-table-column>
   </el-table>
   <div class="pagination"><span>共 {{total}} 条记录</span><el-pagination v-model:current-page="page" v-model:page-size="size" :total="total" :page-sizes="[10,20,50]" layout="sizes, prev, pager, next" @current-change="load" @size-change="load"/></div>
  </div>
 </div>
</template>
<script setup lang="ts">
import {onMounted,ref} from 'vue'
import {Search,Plus} from '@element-plus/icons-vue'
import {getUsers} from '@/api/user'
const rows=ref<any[]>([]),keyword=ref(''),loading=ref(false),page=ref(1),size=ref(10),total=ref(0)
async function load(){loading.value=true;try{const res:any=await getUsers({page:page.value,size:size.value,keyword:keyword.value||undefined});rows.value=res.data.records;total.value=res.data.total}finally{loading.value=false}}
function search(){page.value=1;load()}function reset(){keyword.value='';search()}
onMounted(load)
</script>
<style scoped>
.heading{display:flex;justify-content:space-between;align-items:center;margin-bottom:24px}.toolbar{display:flex;gap:10px;margin-bottom:20px}.user-cell{display:flex;align-items:center;gap:10px}.user-avatar{width:32px;height:32px;border-radius:9px;background:#edf3ff;color:#5279d9;display:grid;place-items:center;font-weight:700}.user-cell b{font-size:12px;color:#344258}.user-cell small{display:block;color:#a0a9b8;font-size:10px;margin-top:4px}.pagination{display:flex;align-items:center;justify-content:space-between;margin-top:20px;color:#929bad;font-size:11px}
@media(max-width:650px){.page-container{padding:20px 14px}.heading{align-items:flex-start;gap:12px}.pagination{align-items:flex-start;flex-direction:column;gap:14px}.toolbar{flex-wrap:wrap}}
</style>
