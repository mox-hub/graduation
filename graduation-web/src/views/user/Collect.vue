<template>
    <div class="page">
        <a-layout>
            <a-layout-header>
                <Header></Header>
            </a-layout-header>
            <a-layout>
                <!-- 侧边栏：页面菜单 -->
                <a-layout-sider>
                    <Menu></Menu>
                </a-layout-sider>
                <a-layout-content>
                    <!-- 页面标题 -->
                    <a-typography class="title-box">
                        <a-typography-title bold>
                            用户收藏页面
                        </a-typography-title>
                    </a-typography>

                </a-layout-content>

            </a-layout>
            <a-layout-footer>

            </a-layout-footer>
        </a-layout>
        <a-back-top target-container="#basic-demo" :style="{ position: 'absolute' }" />
    </div>
</template>

<script setup lang='ts'>
import Header from '../../components/Header.vue'
import Menu from '../../components/Menu.vue'
import { ref, reactive } from 'vue'

</script>
<style scoped>
.page {
    height: 100%;
    background-color: var(--color-neutral-2);
}
</style>