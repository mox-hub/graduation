import { createApp } from 'vue'
import ArcoVue from '@arco-design/web-vue';
import ArcoVueIcon from '@arco-design/web-vue/es/icon';
import App from './App.vue';
import router from './router/router'
import '@arco-design/web-vue/dist/arco.css';
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

const app = createApp(App);
app.use(ArcoVue);
app.use(ArcoVueIcon);
app.use(ElementPlus);
app.use(router);
app.mount('#app');