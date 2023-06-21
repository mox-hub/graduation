<template>
    <a-layout>
        <!-- 侧边栏功能：选择参数 -->
        <a-layout-sider class="layout-sider">
            <a-space direction="vertical" style="margin-top: 20px;margin-bottom: 100px;">
                <a-textarea class="textarea" placeholder="请输入正向文本" :max-length="128" allow-clear
                    v-model:model-value="imageData.prompt" show-word-limit />
                <a-textarea class="textarea" placeholder="请输入反向文本" v-model:model-value="imageData.negative_prompt"
                    style="margin-bottom: 20px;" :max-length="{ length: 128, errorOnly: true }" allow-clear
                    show-word-limit />
                <!-- 其他选项 -->
                <a-collapse :default-active-key="defaultActiveKey" :bordered="false" expand-icon-position="right">
                    <!-- step2: 选择图片规格 -->
                    <a-collapse-item header="选择生成图片规格" :style="customStyle" key="1">
                        <!-- 选择图片数量 -->
                        <a-space direction="vertical">
                            <a-radio-group>
                                <template v-for="item in 3" :key="item">
                                    <a-radio :value="item" :style="{ marginRight: '4px' }">
                                        <template #radio="{ checked }">
                                            <a-space align="start" class="custom-radio-card"
                                                :class="{ 'custom-radio-card-checked': checked }"
                                                :style="{ backgroundImage: 'url(' + 'https://p1-arco.byteimg.com/tos-cn-i-uwbnlip3yd/a20012a2d4d5b9db43dfc6a01fe508c0.png~tplv-uwbnlip3yd-webp.webp' + ')' }">
                                                <div className="custom-radio-card-mask-dot">
                                                    <icon-check />
                                                </div>
                                                <div>
                                                    <div className="custom-radio-card-title">
                                                        Card
                                                    </div>
                                                </div>
                                            </a-space>
                                        </template>
                                    </a-radio>
                                </template>
                            </a-radio-group>
                            <a-slider :min="128" :max="512" :step="32" :default-value="128"
                                v-model:model-value="imageData.width" :show-ticks="true" :style="{ width: '100%' }"
                                show-input />
                            <a-slider :min="128" :max="512" :step="32" :default-value="128"
                                v-model:model-value="imageData.height" :show-ticks="true" :style="{ width: '100%' }"
                                show-input />
                        </a-space>
                    </a-collapse-item>
                    <!-- step3: 选择生成步数 -->
                    <a-collapse-item :style="customStyle" key="2">
                        <template #header>
                            选择生成步数
                            <a-tooltip content="生成步数决定了生成图片的质量" position="bottom">
                                <icon-question-circle-fill />
                            </a-tooltip>
                        </template>
                        <template #extra>
                            <span @click.stop>
                                <a-switch type="line" v-model="stepChecked">
                                    <template #checked-icon>
                                        <icon-check />
                                    </template>
                                    <template #unchecked-icon>
                                        <icon-close />
                                    </template>
                                </a-switch>
                            </span>
                        </template>
                        <a-slider :min="10" :max="100" :default-value="10" :style="{ width: '100%' }" show-input
                            v-model:model-value="imageData.steps" :disabled="!stepChecked" />
                    </a-collapse-item>
                    <!-- step4: 选择图片风格 -->
                    <a-collapse-item :style="customStyle" :key="3">
                        <template #header>
                            选择图片风格
                            <a-tooltip content="图片风格可以规范你的生成图片的色彩类型" position="bottom">
                                <icon-question-circle-fill />
                            </a-tooltip>
                        </template>
                        <template #extra>
                            <span @click.stop>
                                <a-switch type="line" v-model="styleChecked">
                                    <template #checked-icon>
                                        <icon-check />
                                    </template>
                                    <template #unchecked-icon>
                                        <icon-close />
                                    </template>
                                </a-switch>
                            </span>
                        </template>
                        <a-radio-group v-model:model-value="imageData.style">
                            <template v-for="(item, index) in styleList" :key="index">
                                <a-radio :value="item.value" :style="{ marginRight: '4px' }"
                                    :disabled="!styleChecked">
                                    <template #radio="{ checked }">
                                        <a-space align="start" class="custom-radio-card"
                                                 :style="{backgroundImage:'url(' + item.image + ')'}"
                                                 :class="{ 'custom-radio-card-checked': checked }">
                                            <div className="custom-radio-card-mask">
                                                <div className="custom-radio-card-mask-dot" />
                                            </div>
                                            <div >
                                                <div className="custom-radio-card-title">
                                                    {{ item.name }}
                                                </div>
                                            </div>
                                        </a-space>
                                    </template>
                                </a-radio>
                            </template>
                        </a-radio-group>
                    </a-collapse-item>
                    <!-- step5: 选择相关性 -->
                    <a-collapse-item :style="customStyle" key="4">
                        <template #header>
                            选择提示词相关性
                            <a-tooltip content="图片风格可以规范你的生成图片的色彩类型" position="bottom">
                                <icon-question-circle-fill />
                            </a-tooltip>
                        </template>
                        <template #extra>
                            <span @click.stop>
                                <a-switch type="line" v-model="keywordChecked">
                                    <template #checked-icon>
                                        <icon-check />
                                    </template>
                                    <template #unchecked-icon>
                                        <icon-close />
                                    </template>
                                </a-switch>
                            </span>
                        </template>
                        <a-slider :min="2" :max="50" :default-value="10" :style="{ width: '100%' }" show-input
                            v-model:model-value="imageData.cfgScale" :disabled="!keywordChecked" />
                    </a-collapse-item>
                    <!-- step6: 选择采样方法 -->
                    <a-collapse-item :style="customStyle" key="6">
                        <template #header>
                            选择采样方法
                            <a-tooltip content="采样方法指图片生成时，进行去噪操作的采样方法" position="bottom">
                                <icon-question-circle-fill />
                            </a-tooltip>
                        </template>
                        <template #extra>
                            <span @click.stop>
                                <a-switch type="line" v-model="sampleChecked">
                                    <template #checked-icon>
                                        <icon-check />
                                    </template>
                                    <template #unchecked-icon>
                                        <icon-close />
                                    </template>
                                </a-switch>
                            </span>
                        </template>
                        <a-select :style="{ width: '100%' }" v-model="imageData.sampler_index" placeholder="请选择采样方法..."
                                  :disabled="!sampleChecked">
                            <a-option>Euler</a-option>
                            <a-option>Shanghai</a-option>
                            <a-option>Guangzhou</a-option>
                            <a-option disabled>Disabled</a-option>
                        </a-select>
                    </a-collapse-item>
                </a-collapse>
            </a-space>
            <!-- 按钮控件 -->
            <a-space direction="vertical">
                <a-divider />
                <a-space class="button-box">
                    <!-- 选择图片数量 -->
                    <a-select v-model="imageData.batch_size" default-value="1"
                        :style="{ width: '140px', borderRadius: '10px' }" size="large" placeholder="选择图片数量">
                        <template #label="{ data }">
                            <span>生成 {{ data?.label }} 张图片</span>
                        </template>
                        <a-option>1</a-option>
                        <a-option>2</a-option>
                        <a-option>3</a-option>
                        <a-option>4</a-option>
                        <a-option>5</a-option>
                        <a-option>6</a-option>
                        <a-option>7</a-option>
                        <a-option>8</a-option>
                        <a-option>9</a-option>
                    </a-select>
                    <a-divider />
                    <a-button type="primary" size="large" :style="{ width: '200px', borderRadius: '10px' }"
                        @click="handleGenerate">生成</a-button>
                </a-space>
            </a-space>
        </a-layout-sider>
        <a-layout-content class="layout-content">
            <!-- 页面标题 -->
            <div class="title-box">
                <div class="title-text">
                    Stable-diffusion：通过文本生成风格化图片
                </div>
            </div>
            <!-- card -->
            <a-card class="body-card" style="text-align: center;">
                <!-- 图片预览框 -->
                <a-carousel animation-name="card" indicator-type="slider" indicator-position="outer" :style="{
                    width: '1024px',
                    height: '512px',
                }">
                    <a-carousel-item v-for="item in imageList.image">
                        <a-image class="image" fit="cover" :src="item" v-model:width="width" v-model:height="height" />
                    </a-carousel-item>
                </a-carousel>
                <template #actions>
                </template>
                <template #cover>
                </template>
            </a-card>
        </a-layout-content>
    </a-layout>
</template>

<script setup lang='ts'>
import {reactive, ref} from 'vue'
import {getData, text2img, insertData} from '../../api'
import {useRoute} from 'vue-router'
import {Notification, Button, Message} from '@arco-design/web-vue';
import router from "../../router/router";
// 使用路由功能获取参数
const route = useRoute()
// 展示图片的列表
const imageList = reactive({
  image:["http://fpoimg.com/512?text=Stable%20Diffusion"]
})

let tmpImage = "http://fpoimg.com/512?text=Stable%20Diffusion"

// 折叠菜单的自定义样式
const customStyle = {
    width: '384px',
    borderRadius: '6px',
    marginLeft: '6px',
    marginRight: '6px',
    marginBottom: '18px',
    border: 'none',
    overflow: 'hidden',
}
// 折叠菜单默认展开栏目
const defaultActiveKey = ref(['1'])
// 选择是否开启部分功能
const stepChecked = ref(false);
const styleChecked = ref(false);
const keywordChecked = ref(false);
const sampleChecked = ref(false);

// 展示风格列表
const styleList = reactive([
    {
        value: "Chesley Bonestell,",
        name: '科幻画',
        image: 'https://img.moxhub.cn/blog/bnac6y7stkc7.png',
    },
    {
        value: "Tim Burton,",
        name: '恐怖画',
        image: 'https://img.moxhub.cn/blog/cjzvvcny65jq.jpg',
    },
    {
        value: "Katsuhiro Otomo,",
        name: '动漫画',
        image: 'https://img.moxhub.cn/blog/06z591khfqc6.png',
    },
])

/** =========================================================================
 *                            网络请求部分
 * ========================================================================== */

// 生成图片绑定的参数
const imageData = reactive({
    seed: -1,
    batch_size: 1,
    steps: 20,
    width: 512,
    height: 512,
    prompt: "",
    cfgScale: 7,
    style: "Katsuhiro Otomo",
    negative_prompt: "",
    sampler_index: "Euler",
})
// 图片请求参数
const imageRequest = reactive({
    imageId: 1,
    imageUrl: '',
    pageIndex: 1,
    pageSize: 10,
})
// 提示词请求参数
const promptRequest = reactive({
    promptId: 1,
    batch_size: 1,
    steps: 20,
    width: 512,
    height: 512,
    prompt: "",
    cfgScale: 7,
    style: "Katsuhiro Otomo",
    negative_prompt: "",
    sampler_index: "Euler",
    pageIndex:1,
    pageSize:10,
})

const historyRequest = reactive({
    historyId: 0,
    userId: 1,
    imageId: 0,
    promptId: 0,
    imageNum: 1,
    historyType: '文本生成图片',
    generateTime: Date.parse(new Date().toString()),
    pageIndex: 1,
    pageSize: 100,
})
let img = ''
// 请求后台
const generateImage = (data: any) => {
    // 添加提示词
    promptRequest.prompt = imageData.prompt
    promptRequest.negative_prompt = imageData.negative_prompt
    promptRequest.height = imageData.height
    promptRequest.width = imageData.width
    promptRequest.sampler_index = imageData.sampler_index
    promptRequest.steps = imageData.steps
    promptRequest.cfgScale = imageData.cfgScale
    promptRequest.style = imageData.style
    createPrompt();

    text2img("/sd/text2img", data).then(async (res) => {
      // 添加历史记录
      historyRequest.imageId = Number(res.data.images[0])
      createHistory()
      // 清空展示列表
      imageList.image.length = 0;
      let list = res.data.images
      for (let i = 0; i < list.length; i++) {
        let imageId = Number(res.data.images[i])
        // console.log(imageId)
        imageRequest.imageId = imageId
        await getImageById(imageRequest)
      }
      console.log("111")
      // imageList.image.values = res.data.images
      console.log(imageList.image)
    })
}

const getImageById = (data: any) => {
  getData(data, '/image/getImageByImageId').then((res) => {
      console.log(res.data)
      imageList.image.push(res.data.imageUrl)

  })
}
const getImageData = (data: any) => {
    getData(data, '/image/getImageByImageId').then((res) => {
        console.log(res.data)
        imageList.image.length = 0
        imageList.image = res.data.imageUrl.split(",")
        console.log(imageList)
    })
}

const createHistory = () => {
    insertData(historyRequest,'/history/createHistory').then((res) => {
        console.log(res)
    })
}

const createPrompt = () => {
    insertData(promptRequest,'/prompt/createPrompt').then((res) => {
        historyRequest.promptId = res.data
        console.log("prompt res:")
        console.log(res)
    })
}

const getPromptData = (data: any) => {
    getData(data, '/prompt/getPromptByPromptId').then((res) => {
        console.log(res.data)
        imageData.prompt = res.data.prompt
        imageData.negative_prompt = res.data.negative_prompt
        imageData.batch_size = res.data.batch_size
        imageData.height = res.data.height
        imageData.width = res.data.width
        imageData.steps = res.data.steps
        imageData.cfgScale = res.data.cfgScale
        imageData.sampler_index = res.data.sampler_index
        stepChecked.value = true
        sampleChecked.value = true
        keywordChecked.value =true
        styleChecked.value = true
        defaultActiveKey.value = ['1','2','3','4']
    })
}

// 生成图片按钮绑定事件
const handleGenerate = (e: Event) => {
    if (localStorage.getItem("token") != 'token') {
      Message.error({
        content:'请先登录！',
        closable: true
      })
      router.push("/login")
    }
    generateImage(imageData);
}
// 计算展示图片宽度
const width = computed(() => {
    return imageData.width * 1
})
// 计算展示图片长度
const height = computed(() => {
    return imageData.height * 1
})
watch(()=> [imageData.width,imageData.height],([newWidth,newHeight],[oldWidth,oldHeight])=>{
    imageList.image = ['http://fpoimg.com/'+newWidth+'x'+newHeight+'?text=Stable%20Diffusion']
    tmpImage = 'http://fpoimg.com/'+newWidth+'x'+newHeight+'?text=Stable%20Diffusion'
})

// 监控生成多张图片事件
watch(() => imageData.batch_size, (newVal, oldVal) => {
    console.log(newVal, oldVal);
    if (newVal > imageList.image.length) {
        for (let i = imageList.image.length; i < newVal; i++) {
            imageList.image.push(tmpImage)
        }
    } else if (newVal < imageList.image.length) {
        imageList.image.slice(0, newVal)
    }
})

// watch(imageData, (newVal, oldVal) => {
//     console.log(newVal, oldVal);
// })

/** =========================================================================
 *                            分享链接部分处理
 * ========================================================================== */

// 处理分享链接进入逻辑
onMounted(async () => {
  console.log(route.params);
  if (route.params.image != '' && route.params.prompt != '') {
    promptRequest.promptId = Number(route.params.prompt)
    await getPromptData(promptRequest)
    imageRequest.imageId = Number(route.params.image)
    await getImageData(imageRequest)
    Notification.success({
      id: 'link',
      title: '分享链接解析成功',
      content: '欢迎使用Stable Diffusion图片生成系统，请查看您的获取的分享内容！',
      closable: true,
      duration: 8000,
    })
  }
})
</script>
<style scoped>
.layout-sider {
    text-align: center;
    background-color: var(--color-neutral-1);
    min-width: 420px;
    height: 1200px;

}

.layout-sider ::-webkit-scrollbar {
    width: 0 !important;
    height: 0;
}

.layout-content {
    /* background-color: var(--color-bg-3); */
    min-width: 420px;
    height: 1200px;
}

.title-box {
    display: table;
    margin: auto;
    padding: 40px;
}

.title-text {
    background-image: linear-gradient(90deg, #dec36d, #62ffb8 50%, #62f2ff 98%);
    -webkit-background-clip: text;
    color: transparent;
    background-clip: text;
    background-size: 300% 100%;
    font-size: 50px;
    font-weight: 600;
}

.body-card {
    display: table;
    margin: auto;
    width: 800px;
    height: auto;
    padding: 60px;
    border: 10px;
    border-radius: 10px;
}

.image {
    display: inline;
    border-radius: 10px;
}

.textarea {
    border-radius: 6px;
    width: 90%;
}

.card-demo {
    padding: 0;
}

.input {
    margin: auto;
    width: 800px;
    height: 40px;
    border-radius: 20px;
    margin-top: 100px;
}

.button-box {
    margin: 10px;
    text-align: center;
    position: absolute;
    bottom: 10px;
    left: 10px;
}

.choose-num {
    margin: 10px 10px;
    border-radius: 20px;
}

/* 自定义图片选择框 */
.custom-radio-card {
    padding: 2px 2px;
    border: 2px solid var(--color-border-2);
    border-radius: 8px;
    background-size: 100% 100%;
    width: 100px;
    height: 100px;
    box-sizing: border-box;
}

.custom-radio-card-mask {
    position: absolute;
    top: 0;
    right: 0;
}

.custom-radio-card-mask-dot {
    position: absolute;
    top: 0;
    right: 0;
    /* background-color: rgb(var(--primary-6)); */
    clip-path: polygon(0 0, 100% 100%, 100% 0);
    height: 22px;
    width: 22px;
    display: inline-flex;
    align-items: center;
    justify-content: center;
    box-sizing: border-box;
}

.custom-radio-card-title {
    color: var(--color-primary-light-1);
    font-size: 14px;
    font-weight: bold;
    margin-bottom: 8px;
}

.custom-radio-card:hover,
.custom-radio-card-checked,
.custom-radio-card:hover .custom-radio-card-mask,
.custom-radio-card-checked .custom-radio-card-mask {
    border-color: rgb(var(--primary-6));
}

.custom-radio-card-checked {
    background-color: var(--color-primary-light-1);
}

.custom-radio-card:hover .custom-radio-card-title,
.custom-radio-card-checked .custom-radio-card-title {
    color: rgb(var(--primary-6));
}

.custom-radio-card-checked .custom-radio-card-mask-dot {
    background-color: rgb(var(--primary-6));
}

/* 关键词多选 */
.custom-checkbox-card {
    padding: 10px 16px;
    border: 2px solid var(--color-border-2);
    border-radius: 10px;
    /* width: 250px; */
    box-sizing: border-box;
}

.custom-checkbox-card-mask {
    height: 14px;
    width: 14px;
    display: inline-flex;
    align-items: center;
    justify-content: center;
    border-radius: 2px;
    border: 1px solid var(--color-border-2);
    box-sizing: border-box;
}

.custom-checkbox-card-mask-dot {
    width: 8px;
    height: 8px;
    border-radius: 2px;
}

.custom-checkbox-card-title {
    color: var(--color-text-1);
    font-size: 14px;
    font-weight: bold;
}

.custom-checkbox-card:hover,
.custom-checkbox-card-checked,
.custom-checkbox-card:hover .custom-checkbox-card-mask,
.custom-checkbox-card-checked .custom-checkbox-card-mask {
    border-color: rgb(var(--primary-6));
}

.custom-checkbox-card-checked {
    background-color: var(--color-primary-light-1);
}

.custom-checkbox-card:hover .custom-checkbox-card-title,
.custom-checkbox-card-checked .custom-checkbox-card-title {
    color: rgb(var(--primary-6));
}

.custom-checkbox-card-checked .custom-checkbox-card-mask-dot {
    background-color: rgb(var(--primary-6));
}
</style>