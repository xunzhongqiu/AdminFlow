<template>
  <div class="admin-layout">
    <aside class="sidebar">
      <div class="brand"><div class="brand-mark">A</div><span>AdminFlow</span></div>
      <div class="workspace"><div class="workspace-icon">AF</div><div><b>Admin Workspace</b><small>Enterprise Edition</small></div><el-icon><ArrowDown /></el-icon></div>
      <div class="nav-label">工作空间</div>
      <router-link class="nav-item" to="/dashboard"><el-icon><DataBoard /></el-icon><span>工作台</span></router-link>
      <div class="nav-label">系统管理</div>
      <router-link class="nav-item" to="/system/users"><el-icon><User /></el-icon><span>用户管理</span></router-link>
      <a class="nav-item disabled"><el-icon><Avatar /></el-icon><span>角色管理</span><em>规划中</em></a>
      <a class="nav-item disabled"><el-icon><Menu /></el-icon><span>菜单管理</span></a>
      <a class="nav-item disabled"><el-icon><OfficeBuilding /></el-icon><span>组织架构</span></a>
      <div class="sidebar-bottom"><div class="help-card"><b>需要帮助？</b><p>查看文档，快速了解 AdminFlow。</p><span>阅读开发文档 →</span></div><div class="profile"><div class="avatar">A</div><div><b>系统管理员</b><small>admin</small></div><el-icon><MoreFilled /></el-icon></div></div>
    </aside>
    <main class="main-area">
      <header class="topbar"><div class="breadcrumb">工作空间 <span>/</span> <b>{{ route.meta.title || '工作台' }}</b></div><div class="top-actions"><el-tooltip content="通知"><el-button circle text><el-icon><Bell /></el-icon></el-button></el-tooltip><el-button circle text><el-icon><Setting /></el-icon></el-button><el-dropdown @command="onCommand"><div class="top-avatar">A</div><template #dropdown><el-dropdown-menu><el-dropdown-item command="logout">退出登录</el-dropdown-item></el-dropdown-menu></template></el-dropdown></div></header>
      <router-view />
    </main>
  </div>
</template>
<script setup lang="ts">
import { useRoute, useRouter } from 'vue-router'
import { ArrowDown, DataBoard, User, Avatar, Menu, OfficeBuilding, MoreFilled, Bell, Setting } from '@element-plus/icons-vue'
const route = useRoute(), router = useRouter()
function onCommand(command:string){ if(command==='logout'){localStorage.removeItem('adminflow_token');router.push('/login')} }
</script>
<style scoped>
.admin-layout{min-height:100vh;display:flex;background:#f6f8fc}.sidebar{width:254px;background:#111c2e;color:#a8b4c7;padding:22px 15px;display:flex;flex-direction:column;flex-shrink:0}.brand{display:flex;align-items:center;gap:11px;color:#fff;font-weight:750;font-size:20px;padding:0 10px 26px}.brand-mark{width:32px;height:32px;border-radius:10px;background:linear-gradient(135deg,#5d8dff,#4166e8);display:grid;place-items:center;color:white}.workspace{display:flex;align-items:center;gap:10px;padding:11px;background:#1b2a42;border:1px solid #2a3b55;border-radius:10px;color:#fff;font-size:12px}.workspace small,.profile small{display:block;color:#8594aa;font-size:10px;margin-top:4px}.workspace .el-icon:last-child{margin-left:auto}.workspace-icon{width:30px;height:30px;border-radius:8px;background:#314d79;display:grid;place-items:center;color:#c9dcff;font-weight:bold}.nav-label{font-size:10px;text-transform:uppercase;letter-spacing:1.1px;color:#71819a;margin:27px 11px 10px}.nav-item{height:42px;padding:0 12px;display:flex;align-items:center;gap:12px;color:#a8b4c7;text-decoration:none;border-radius:8px;font-size:13px;margin:2px 0}.nav-item .el-icon{font-size:17px}.nav-item.router-link-active{background:#263d60;color:#fff}.nav-item.disabled{opacity:.58;cursor:default}.nav-item em{font-size:9px;font-style:normal;margin-left:auto}.sidebar-bottom{margin-top:auto}.help-card{background:#1b2a42;border:1px solid #2a3b55;border-radius:10px;padding:14px;font-size:12px;color:#e1e8f2}.help-card p{color:#91a0b5;line-height:1.6}.help-card span{color:#83aaff}.profile{display:flex;align-items:center;gap:10px;padding:18px 5px 0;color:#fff;font-size:12px}.avatar,.top-avatar{width:32px;height:32px;border-radius:50%;background:#dbe7ff;color:#4166c5;display:grid;place-items:center;font-weight:700}.profile .el-icon{margin-left:auto}.main-area{flex:1;min-width:0}.topbar{height:66px;background:#fff;border-bottom:1px solid #edf0f5;display:flex;align-items:center;justify-content:space-between;padding:0 30px}.breadcrumb{font-size:12px;color:#929bad}.breadcrumb span{padding:0 10px;color:#c0c6d1}.breadcrumb b{color:#29364c}.top-actions{display:flex;align-items:center;gap:12px}.top-avatar{cursor:pointer}.main-area :deep(.el-button.is-text){color:#6d7890}
@media(max-width:760px){.sidebar{width:68px;padding:18px 8px}.brand span,.workspace,.nav-label,.nav-item span,.nav-item em,.help-card,.profile>div:not(.avatar),.profile>.el-icon{display:none}.brand{padding:0 9px 25px}.nav-item{justify-content:center;padding:0}.profile{justify-content:center}.topbar{padding:0 16px}}
</style>
