<template>
    <!-- 用户页面：用户其他页面以插件形式引入 -->
    <div class="page">
        <a-layout>
            <a-layout-header>
                <Header></Header>
            </a-layout-header>
            <Generate></Generate>
            <!-- <Menu></Menu> -->
            <a-layout-footer>
            </a-layout-footer>
        </a-layout>

        <a-back-top target-container="#basic-demo" :style="{ position: 'absolute' }" />
    </div>
</template>

<script setup lang='ts'>
import Header from '../components/Header.vue'
import Menu from '../components/Menu.vue'
import Generate from './user/Generate.vue'


</script>
<style scoped>
.page {
    height: 100%;
    background-color: var(--color-neutral-2);
}
</style>