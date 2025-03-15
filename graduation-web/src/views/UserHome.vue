<template>
    <!-- 用户页面：用户其他页面以插件形式引入 -->
    <div class="page">
        <a-layout>
            <a-layout-header class="header">
                <!-- 首页导航栏 -->
                <a-page-header class="nav-header" title="Stable-diffusion" :show-back=false>
                    <template #subtitle>
                        <a-space>
                            <div class="nav-content">
                                <div class="nav-left">
                                    <span class="subtitle">A text to image AI generation</span>
                                    <a-tag color="blue" size="small">dev</a-tag>
                                </div>

                                <div class="nav-menu">
                                    <a-space size="large">
                                        <router-link to="/home/generate">
                                            <a-button type="text">图片生成</a-button>
                                        </router-link>
                                        <router-link :to="{ name: 'collect' }">
                                            <a-button type="text">我的收藏</a-button>
                                        </router-link>
                                        <router-link :to="{ name: 'user' }">
                                            <a-button type="text">关于我</a-button>
                                        </router-link>
                                    </a-space>
                                </div>
                            </div>
                        </a-space>
                    </template>
                    <template #extra>
                        <a-space>
                            <!-- 语言模式 -->
                            <a-tooltip content="选择语言模式">
                                <a-dropdown trigger="click">
                                    <a-button>简体中文</a-button>
                                    <template #content>
                                        <a-doption>简体中文</a-doption>
                                        <a-doption>English</a-doption>
                                    </template>
                                </a-dropdown>
                            </a-tooltip>
                        </a-space>
                        <a-divider direction="vertical" />
                        <a-space>
                            <!-- 夜间模式 -->
                            <a-tooltip content="点击切换模式">
                                <a-button type="dashed" shape="circle" v-on:click="changeTheme">
                                    <icon-sun-fill v-if="theme == 'light'" />
                                    <icon-moon-fill v-if="theme == 'dark'" />
                                </a-button>
                            </a-tooltip>
                            <a-space v-if="isAuth == 'false'">
                                <!-- 登录 -->
                                <router-link to="/register">
                                    <a-button type="outline" shape="round">注 册</a-button>
                                </router-link>
                                <!-- 注册 -->
                                <router-link to="/login">
                                    <a-button type="primary" shape="round">登 录</a-button>
                                </router-link>
                                <a-divider direction="vertical" />
                                <!-- github跳转 -->
                                <a-button type="dashed" shape="circle" v-on:click="jumpToGithub">
                                    <icon-github />
                                </a-button>
                            </a-space>
                            <a-space v-if="isAuth == 'true'">
                                <a-avatar :style="{ backgroundColor: '#3370ff' }">
                                    <IconUser />
                                </a-avatar>
                                <a-button type="primary" shape="round" v-on:click="logout">退出登录</a-button>
                            </a-space>

                        </a-space>
                    </template>
                </a-page-header>
            </a-layout-header>
            <a-layout-content>
                <!-- 路由组件 -->
                <router-view />
            </a-layout-content>
            <a-layout-footer>
            </a-layout-footer>
        </a-layout>
    </div>
</template>

<script setup lang='ts'>
// =========== 定义变量 ===================
let theme = ref('light');
let pageName = ref('generate')
const isAuth = ref('true')

/**
 * 控件的绑定事件
 */
// 更改主题
const changeTheme = () => {
    if (theme.value == 'light') {
        theme.value = 'dark'
        document.body.setAttribute('arco-theme', 'dark')
    }
    else {
        theme.value = 'light'
        document.body.removeAttribute('arco-theme')
    }
}
// 跳转到仓库地址
const jumpToGithub = () => {
    window.open('https://github.com/mox-hub/graduation', "_blank")
}

const logout = () => {
    localStorage.removeItem("token")
    location.reload()
    console.log(localStorage.getItem("token"))
}

onMounted(() => {
    console.log(localStorage.getItem("token"))
    if (localStorage.getItem("token") === 'token') {
        console.log("yes")
        isAuth.value = 'true'
    } else {
        console.log("no")
        isAuth.value = 'false'
    }
})
</script>
<style scoped>
.page {
    height: 100%;
    background-color: var(--color-neutral-2);
}

.header {
    position: fixed;
    width: 100%;
    top: 0;
    z-index: 100;
    backdrop-filter: blur(10px);
    background-color: rgba(var(--gray-2), 0.6);
    height: 64px;
}

.nav-header {
    background: transparent !important;
    backdrop-filter: none;
}

.nav-content {
    display: flex;
    align-items: center;
    width: 100%;
}

.nav-left {
    display: flex;
    align-items: center;
    gap: 8px;
}

.nav-center {
    flex: 1;
    display: flex;
    justify-content: center;
}

.nav-menu {
    margin-left: 24px;
}

.subtitle {
    font-size: 14px;
    color: var(--color-text-2);
}

.layout-content {
    margin-top: 64px;
    padding: 24px;
}
</style>