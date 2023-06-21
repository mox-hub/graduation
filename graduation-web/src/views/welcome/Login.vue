<template>
    <!-- 登录页面 -->
    <div>
        <a-form direction="vertical" class="login-container" :model="loginForm.value" :style="{ width: '500px' }">
            <h2 class="login_title">用户登录</h2>
            <a-form-item field="userName" label="用户名" validate-trigger="input">
                <a-input v-model="loginForm.userName" placeholder="请输入你的用户名..." />
                <template #extra>
                    <div>填写你的用户名</div>
                </template>
            </a-form-item>
            <a-form-item field="password" label="密码" validate-trigger="input" >
                <a-input v-model="loginForm.password" placeholder="请输入你的密码..." />
                <template #extra>
                    <div>填写你的密码</div>
                </template>
            </a-form-item>
            <a-form-item field="isRead">
                <a-checkbox v-model="loginForm.isRead">
                    我同意《隐私协议》
                </a-checkbox>
            </a-form-item>
            <a-form-item>
                <a-button class="button" type="primary" size="large" shape="round" long v-on:click="login">登录</a-button>
                <router-link to="/register" style="width: 100%;">
                    <a-button class="button" shape="round" size="large" long>注册</a-button>
                </router-link>
            </a-form-item>
        </a-form>
    </div>
</template>

<script setup lang="ts">
import { Message } from '@arco-design/web-vue';
import router from "../../router/router";
const loginForm = ref({
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

const login = () => {
  if (loginForm.value.userName==='' || loginForm.value.password === '') {
    Message.warning({
      content:'用户名和密码不可以为空！',
      closable: true
    })
  } else if (loginForm.value.isRead == false) {
    Message.warning({
      content:'请确认协议！',
      closable: true
    })
  } else {
      if (loginForm.value.userName==='mox' || loginForm.value.password === '123456') {
        Message.success({
          content:'用户登录成功！',
          closable: true
        })
        localStorage.setItem('token','token');
        router.push("/home/home")
      } else {
        Message.error({
          content:'用户名或密码错误！',
          closable: true
        })
      }
  }
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