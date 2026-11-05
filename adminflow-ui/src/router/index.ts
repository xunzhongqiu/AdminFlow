import { createRouter, createWebHistory } from 'vue-router'
const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/login', component: () => import('@/views/login/index.vue'), meta: { public: true } },
    { path: '/', component: () => import('@/layout/index.vue'), redirect: '/dashboard',
      children: [
        { path: 'dashboard', component: () => import('@/views/dashboard/index.vue'), meta: { title: '工作台' } },
        { path: 'system/users', component: () => import('@/views/system/users/index.vue'), meta: { title: '用户管理' } }
      ] },
    { path: '/:pathMatch(.*)*', redirect: '/dashboard' }
  ]
})
router.beforeEach(to => {
  if (to.meta.public) return true
  if (!localStorage.getItem('adminflow_token')) return '/login'
  return true
})
export default router
