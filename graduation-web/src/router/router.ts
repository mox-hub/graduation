import { createRouter, createWebHashHistory } from 'vue-router'
import { Message } from '@arco-design/web-vue';
const routes = [
  {
    path: '/',
    redirect: '/home/home',
  },
  {
    path: '/home',
    name: 'Home',
    component: () => import('../views/UserHome.vue'),
    children: [
      {
        path: 'home',
        name: 'homePage',
        meta: {
          title: '首页',
        },
        component: () => import('../views/welcome/Home.vue'),
      },
      {
        path: 'generate/:prompt?/:image?',
        name: 'generate',
        meta: {
          title: '生成页面',
        },
        component: () => import('../views/user/Generate.vue'),
      },
      {
        path: 'collect',
        name: 'collect',
        meta: {
          title: '用户收藏页面',
          isAuth: true
        },
        component: () => import('../views/user/Collect.vue'),
      },
      {
        path: 'user',
        name: 'user',
        meta: {
          title: '用户页面',
          isAuth: true
        },
        component: () => import('../views/user/User.vue'),
      },
    ],
  },
  {
    path: '/login',
    name: 'login',
    meta: {
      title: '登录',
    },
    component: () => import('../views/welcome/Login.vue'),
  },
  {
    path: '/register',
    name: 'register',
    meta: {
      title: '注册',
    },
    component: () => import('../views/welcome/Register.vue'),
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes,
})
//导航守卫、路由守卫、路由拦截
router.beforeEach((to,from,next) =>{
  //验证token,只有存在token的时候，才能跳转到内容页

  let token = localStorage.getItem("token");
  //console.log(token);
  if(to.meta.isAuth){
    if (localStorage.getItem('token') === 'token') {
      next();
      console.log(token);
    } else {
      Message.error({
        content:'请先登录！',
        closable: true
      })
      next("/login");
    }
  }else{
    next();
  }
})

export default router
