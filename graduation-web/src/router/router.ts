import { createRouter, createWebHashHistory } from 'vue-router'
import Home from '../views/UserHome.vue'
const routes = [
  {
    path: '/',
    redirect: '/home',
  },
  {
    path: '/',
    name: 'Home',
    component: Home,
    children: [],
  },
  {
    path: '/home',
    name: 'home',
    meta: {
      title: '首页',
    },
    component: () => import('../views/UserHome.vue'),
  },
  {
    path: '/login',
    name: 'login',
    meta: {
      title: '登录',
    },
    component: () => import('../views/Login.vue'),
  },
  {
    path: '/register',
    name: 'register',
    meta: {
      title: '登录',
    },
    component: () => import('../views/Register.vue'),
  },
  {
    path: '/collect',
    name: 'collect',
    meta: {
      title: '用户收藏页面',
    },
    component: () => import('../views/user/Collect.vue'),
  },
  {
    path: '/user',
    name: 'user',
    meta: {
      title: '用户页面',
    },
    component: () => import('../views/user/User.vue'),
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes,
})

export default router
