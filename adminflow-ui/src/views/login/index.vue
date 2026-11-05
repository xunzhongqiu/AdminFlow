<template>
  <div class="login-page">
    <section class="login-visual"><div class="visual-brand"><div class="brand-icon">A</div> AdminFlow</div><div class="visual-content"><div class="eyebrow">CONTROL YOUR WORKSPACE</div><h1>Everything you need.<br/><span>All in one place.</span></h1><p>一个现代化、可扩展的企业级管理平台，让复杂的业务管理变得简单。</p><div class="visual-bottom">SECURE · EFFICIENT · SCALABLE</div></div><div class="visual-orb orb-one"></div><div class="visual-orb orb-two"></div></section>
    <section class="login-form-wrap"><div class="login-form"><div class="mobile-brand">AdminFlow</div><div class="welcome">欢迎回来 👋</div><h2>登录你的工作空间</h2><p class="desc">请输入账号和密码以继续</p>
      <el-form ref="formRef" :model="form" :rules="rules" size="large" @keyup.enter="submit">
        <el-form-item prop="username"><label>用户名</label><el-input v-model="form.username" placeholder="请输入用户名" :prefix-icon="User" /></el-form-item>
        <el-form-item prop="password"><label>密码</label><el-input v-model="form.password" type="password" show-password placeholder="请输入密码" :prefix-icon="Lock" /></el-form-item>
        <div class="form-extra"><el-checkbox v-model="remember">记住我</el-checkbox><span>忘记密码？</span></div>
        <el-button class="submit-btn" type="primary" :loading="loading" @click="submit">登 录 <el-icon><Right /></el-icon></el-button>
      </el-form><div class="demo-tip"><b>本地演示账号</b><span>admin / Admin@123456</span></div><div class="copyright">© 2026 AdminFlow. Crafted for modern teams.</div></div></section>
  </div>
</template>
<script setup lang="ts">
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage, type FormInstance, type FormRules } from 'element-plus'
import { User, Lock, Right } from '@element-plus/icons-vue'
import { login } from '@/api/auth'
const router=useRouter(),formRef=ref<FormInstance>(),loading=ref(false),remember=ref(true)
const form=reactive({username:'admin',password:'Admin@123456'})
const rules:FormRules={username:[{required:true,message:'请输入用户名',trigger:'blur'}],password:[{required:true,message:'请输入密码',trigger:'blur'}]}
async function submit(){if(!await formRef.value?.validate().catch(()=>false))return;loading.value=true;try{const res:any=await login(form);localStorage.setItem('adminflow_token',res.data.accessToken);ElMessage.success('登录成功');router.push('/dashboard')}finally{loading.value=false}}
</script>
<style scoped>
.login-page{min-height:100vh;display:grid;grid-template-columns:1fr 1fr;background:white}.login-visual{position:relative;overflow:hidden;background:linear-gradient(145deg,#101d34,#172e50 60%,#244c78);padding:42px 8%;color:#fff;display:flex;flex-direction:column}.visual-brand{display:flex;align-items:center;gap:12px;font-size:20px;font-weight:750;z-index:1}.brand-icon{width:34px;height:34px;border-radius:10px;background:linear-gradient(135deg,#7da7ff,#4269e9);display:grid;place-items:center}.visual-content{margin:auto 0;max-width:530px;z-index:1}.eyebrow{font-size:10px;letter-spacing:2.8px;color:#91b6f7;font-weight:700}.visual-content h1{font-size:clamp(38px,4.4vw,62px);line-height:1.14;letter-spacing:-2px;margin:22px 0}.visual-content h1 span{color:#82aaff}.visual-content p{color:#b4c4d9;line-height:1.9;font-size:15px;max-width:400px}.visual-bottom{font-size:10px;letter-spacing:2px;color:#7f94b2;margin-top:90px}.visual-orb{position:absolute;border:1px solid #ffffff13;border-radius:50%;width:460px;height:460px;right:-170px;top:16%;box-shadow:0 0 0 45px #ffffff06,0 0 0 90px #ffffff04}.orb-two{width:180px;height:180px;top:auto;right:18%;bottom:-90px;background:#6d9aff12}.login-form-wrap{display:grid;place-items:center;padding:40px}.login-form{width:min(390px,100%)}.welcome{font-size:13px;color:#6c83a7;margin-bottom:12px}.login-form h2{font-size:26px;margin:0;color:#17243a}.desc{font-size:13px;color:#929bad;margin:10px 0 34px}.login-form :deep(.el-form-item){display:block;margin-bottom:23px}.login-form label{display:block;font-size:12px;color:#39465c;font-weight:650;margin-bottom:9px}.login-form :deep(.el-input__wrapper){height:46px;box-shadow:0 0 0 1px #e8edf4 inset;border-radius:9px}.login-form :deep(.el-input__wrapper.is-focus){box-shadow:0 0 0 1px #6489e8 inset}.form-extra{display:flex;justify-content:space-between;align-items:center;font-size:12px;color:#6485d5;margin:-4px 0 24px}.submit-btn{width:100%;height:47px;border-radius:9px;font-weight:650;letter-spacing:2px;background:#426de0;border:0}.submit-btn .el-icon{margin-left:8px}.demo-tip{margin-top:24px;padding:13px 15px;background:#f5f8ff;border:1px solid #e7edfb;border-radius:9px;font-size:11px;color:#7d8ba3;display:flex;justify-content:space-between}.demo-tip span{color:#496bc0;font-family:monospace}.copyright{text-align:center;color:#b0b8c6;font-size:10px;margin-top:58px}.mobile-brand{display:none}
@media(max-width:850px){.login-page{grid-template-columns:1fr}.login-visual{display:none}.login-form-wrap{min-height:100vh}.mobile-brand{display:block;color:#315ec6;font-weight:800;font-size:20px;margin-bottom:48px}}
</style>
