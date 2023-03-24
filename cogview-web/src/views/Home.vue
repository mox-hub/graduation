<template>
    <Header></Header>
    <Menu class="menu"></Menu>
    <div class="page">
        <a-typography class="title-box">
            <a-typography-title bold>
                Text-to-image CogView2 project
            </a-typography-title>
        </a-typography>
        <!-- card -->
        <a-card class="card">
            <template #actions>

            </template>
            <template #cover>
            </template>
        </a-card>
        <a-back-top target-container="#basic-demo" :style="{ position: 'absolute' }" />
    </div>
</template>

<script setup lang='ts'>
import Header from '../components/Header.vue'
import Menu from '../components/Menu.vue'
</script>
<style scoped>
.page {
    height: 2000px;
    background-color: var(--color-neutral-2);
}

.menu {
    top: 100rpx;
    left: 100rpx;
}

.title-box {
    display: table;
    margin: auto;
    /* padding-top: 20px; */
}

.card {
    display: table;
    margin: auto;
    width: 1200px;
    height: 800px;
    margin-top: 100rpx;
    border: 10px;
}
</style>