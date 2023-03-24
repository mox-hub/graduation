import { createRouter, createWebHashHistory } from 'vue-router'
import Home from '../views/Home.vue'
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
      title: '车辆管理',
    },
    component: () =>
      import(/* webpackChunkName: "editor" */ '../views/Home.vue'),
  },
]

const router = createRouter({
    history: createWebHashHistory(),
    routes,
})

export default router
