<!-- 首页导航栏 -->
<template>
    <div :style="{ background: 'var(--color-fill-2)' }">
        <a-page-header :style="{ background: 'var(--color-bg-2)' }" title="Cogview2" :show-back=false>
            <template #subtitle>
                <a-space>
                    <span>A text to image AI generation</span>
                    <a-tag color="blue" size="small">dev</a-tag>
                </a-space>
            </template>
            <template #extra>
                <a-space>
                    <a-button type="dashed" shape="circle" v-on:click="changeTheme">
                        <icon-sun-fill v-if="theme == 'light'" />
                        <icon-moon-fill v-if="theme == 'dark'" />
                    </a-button>
                    <a-button type="outline" shape="round">Login</a-button>
                    <a-button type="primary" shape="round">Sign Up</a-button>
                    <a-divider direction="vertical" />
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

<style scoped></style>