<template>
    <!-- 登录页面 -->
    <div>
        <a-form direction="vertical" class="login-container" :model="form" :style="{ width: '500px' }">
            <h2 class="login_title">用户登录</h2>
            <a-form-item field="userName" label="用户名" validate-trigger="input" required>
                <a-input v-model="form.userName" placeholder="请输入你的用户名..." />
                <template #extra>
                    <div>Used to login</div>
                </template>
            </a-form-item>
            <a-form-item field="password" label="密码" validate-trigger="input" required>
                <a-input v-model="form.password" placeholder="请输入你的密码..." />
                <template #extra>
                    <div>Used to login</div>
                </template>
                <template #help>
                    <div>Custom valitae message</div>
                </template>
            </a-form-item>
            <a-form-item field="isRead">
                <a-checkbox v-model="form.isRead">
                    I have read the manual
                </a-checkbox>
            </a-form-item>
            <a-form-item>
                <a-button class="button" type="primary" size="large" shape="round" long>登录</a-button>
                <a-button class="button" shape="round" size="large" @click="changeFlag('register')" long>注册</a-button>
            </a-form-item>
        </a-form>
    </div>
</template>

<script setup lang="ts">

const form = reactive({
    userName: '',
    password: '',
    isRead: false,
})

const regForm = reactive({
    userName: '',
    password: '',
    isRead: false,
})

const changeFlag = (e: String) => {
    console.log('112')
}

</script>

<style scoped>
.login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 90px auto;
    width: 350px;
    padding: 30px 30px 10px 20px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
}

.login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
}

.button {
    margin-left: 10px;
}
</style>