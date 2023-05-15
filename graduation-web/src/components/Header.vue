<!-- 首页导航栏 -->
<template>
    <div :style="{ background: 'var(--color-fill-2)' }">
        <a-page-header :style="{ background: 'var(--color-bg-2)' }" title="Stable-diffusion" :show-back=false>
            <template #subtitle>
                <a-space>
                    <span>A text to image AI generation</span>
                    <a-tag color="blue" size="small">dev</a-tag>
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
    </div>
</template>
<script setup lang="ts">

let theme = ref('light');

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
</script>

<style scoped>
.menu {
    width: 100%;
}
</style>