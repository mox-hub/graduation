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
    component: () =>
      import(/* webpackChunkName: "editor" */ '../views/UserHome.vue'),
  },
  {
    path: '/login',
    name: 'login',
    meta: {
      title: '登录',
    },
    component: () =>
      import(/* webpackChunkName: "editor" */ '../views/Login.vue'),
  },
  {
    path: '/collect',
    name: 'user',
    meta: {
      title: '用户收藏页面',
    },
    component: () =>
      import(/* webpackChunkName: "editor" */ '../views/user/Collect.vue'),
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes,
})

export default router
