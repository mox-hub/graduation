<template>
    <!-- 用户页面：用户其他页面以插件形式引入 -->
    <div class="page">
        <a-layout>
            <a-layout-header>
                <Header @change-page="changePage"></Header>
            </a-layout-header>
            <Generate v-if="showPage == 'generate'"></Generate>
            <Collect v-if="showPage == 'collect'"></Collect>
            <User v-if="showPage == 'user'"></User>
            <!-- <Menu></Menu> -->
            <a-layout-footer>
            </a-layout-footer>
        </a-layout>
        <a-back-top target-container="#basic-demo" :style="{ position: 'absolute' }" />
    </div>
</template>

<script setup lang='ts'>
import Header from '../components/Header.vue'

import Generate from './user/Generate.vue'
import Collect from './user/Collect.vue'
import User from './user/User.vue'

// 控制页面展示的组件，由header组件传值
let showPage = ref("collect")

const changePage = (name: any) => {
    showPage.value = name
    console.log(name);
}

</script>
<style scoped>
.page {
    height: 100%;
    background-color: var(--color-neutral-2);
}

.layout-tabs {
    width: 100%;
    text-align: center;
}
</style>