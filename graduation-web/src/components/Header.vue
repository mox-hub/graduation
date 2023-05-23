<template>
    <!-- 首页导航栏 -->
    <a-page-header :style="{ background: 'var(--color-bg-1)' }" title="Stable-diffusion" :show-back=false>
        <template #subtitle>
            <a-space>
                <span>A text to image AI generation</span>
                <a-tag color="blue" size="small">dev</a-tag>

                <!-- 页面切换功能 -->
                <a-space class="button-box">
                    <a-button shape="round" @click="changePage('generate')">图片生成</a-button>
                    <a-divider direction="vertical" />
                    <a-button shape="round" name="Collect" @click="changePage('collect')">我的收藏</a-button>
                    <a-divider direction="vertical" />
                    <a-button shape="round" status="success" @click="changePage('user')">关于我</a-button>
                </a-space>
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
                <!-- 登录 -->
                <a-button type="outline" shape="round">登 录</a-button>
                <!-- 注册 -->
                <a-button type="primary" shape="round">注 册</a-button>
                <a-divider direction="vertical" />
                <!-- github跳转 -->
                <a-button type="dashed" shape="circle">
                    <icon-github />
                </a-button>
            </a-space>
        </template>
    </a-page-header>
</template>

<script setup lang="ts">
// =========== 定义变量 ===================
let theme = ref('light');
let pageName = ref('generate')

// 使用emit向父组件传参
const emit = defineEmits(['changePage'])

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
// 更改展示页面
const changePage = (name: any) => {
    pageName.value = name;
    emit('changePage', pageName.value)
}
</script>

<style scoped>
.button-box {
    margin-left: 300px;
}

.menu {
    /* height: 34px; */
    width: 400px;
}
</style>