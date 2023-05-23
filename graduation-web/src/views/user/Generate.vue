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
                <a-collapse :default-active-key="['1', 2]" :bordered="false" expand-icon-position="right">
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
                                <a-switch type="line" @change="disabledImgStyle" v-model="stepChecked" checked-value="on"
                                    unchecked-value="off">
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
                            v-model:model-value="imageData.steps" :disabled="stepChecked == 'off'" />
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
                                <a-switch type="line" @change="disabledImgStyle" v-model="styleChecked" checked-value="on"
                                    unchecked-value="off">
                                    <template #checked-icon>
                                        <icon-check />
                                    </template>
                                    <template #unchecked-icon>
                                        <icon-close />
                                    </template>
                                </a-switch>
                            </span>
                        </template>
                        <a-radio-group>
                            <template v-for="item in 9" :key="item">
                                <a-radio :value="item" :style="{ marginRight: '4px' }" :disabled="styleChecked == 'off'">
                                    <template #radio="{ checked }">
                                        <a-space align="start" class="custom-radio-card"
                                            :class="{ 'custom-radio-card-checked': checked }">
                                            <div className="custom-radio-card-mask">
                                                <div className="custom-radio-card-mask-dot" />
                                            </div>
                                            <div>
                                                <div className="custom-radio-card-title">
                                                    Card {{ item }}
                                                </div>
                                                <img :style="{ width: '100%', height: '100%' }" alt="dessert"
                                                    src="https://p1-arco.byteimg.com/tos-cn-i-uwbnlip3yd/a20012a2d4d5b9db43dfc6a01fe508c0.png~tplv-uwbnlip3yd-webp.webp" />
                                            </div>
                                        </a-space>
                                    </template>
                                </a-radio>
                            </template>
                        </a-radio-group>
                    </a-collapse-item>
                    <!-- step5: 选择关键词 -->
                    <a-collapse-item :style="customStyle" key="4">
                        <template #header>
                            选择关键词
                            <a-tooltip content="图片风格可以规范你的生成图片的色彩类型" position="bottom">
                                <icon-question-circle-fill />
                            </a-tooltip>
                        </template>
                        <template #extra>
                            <span @click.stop>
                                <a-switch type="line" @change="disabledImgStyle" v-model="keywordChecked" checked-value="on"
                                    unchecked-value="off">
                                    <template #checked-icon>
                                        <icon-check />
                                    </template>
                                    <template #unchecked-icon>
                                        <icon-close />
                                    </template>
                                </a-switch>
                            </span>
                        </template>
                        <div :style="{ marginTop: '20px' }">
                            <a-checkbox-group :default-value="[1]" :disabled="keywordChecked == 'off'">
                                <template v-for="item in 2" :key="item">
                                    <a-checkbox :value="item">
                                        <template #checkbox="{ checked }">
                                            <a-space align="start" class="custom-checkbox-card"
                                                :class="{ 'custom-checkbox-card-checked': checked }">
                                                <div className="custom-checkbox-card-title">
                                                    Checkbox Card {{ item }}
                                                </div>
                                            </a-space>
                                        </template>
                                    </a-checkbox>
                                </template>
                            </a-checkbox-group>
                        </div>
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
                                <a-switch type="line" @change="disabledImgStyle" v-model="sampleChecked" checked-value="on"
                                    unchecked-value="off">
                                    <template #checked-icon>
                                        <icon-check />
                                    </template>
                                    <template #unchecked-icon>
                                        <icon-close />
                                    </template>
                                </a-switch>
                            </span>
                        </template>
                        <a-select :style="{ width: '100%' }" v-model="sampleValue" placeholder="Please select ..."
                            allow-clear :disabled="sampleChecked == 'off'">
                            <a-option>Beijing</a-option>
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
                        @click="inputTime">生成</a-button>
                </a-space>
            </a-space>
        </a-layout-sider>
        <a-layout-content class="layout-content">
            <!-- 页面标题 -->
            <div class="title-box">
                <div class="title-text">
                    Stable-diffusion：通过文本生成风格化图片
                </div>
                {{ imageData }}
            </div>
            <!-- card -->
            <a-card class="body-card" style="text-align: center;">
                <!-- 图片预览框 -->
                <a-image-preview-group>
                    <a-space>
                        <div v-for="item in imageList" :key="item">
                            <a-image fit="cover" :src="item" v-model:width="width" v-model:height="height" />
                        </div>
                    </a-space>
                </a-image-preview-group>
                <template #actions>
                </template>
                <template #cover>
                </template>
            </a-card>
        </a-layout-content>
    </a-layout>
</template>

<script setup lang='ts'>
import { ref, reactive } from 'vue'
import { text2img } from "../../api/index.js"

const imageData = ref({
    seed: -1,
    batch_size: 1,
    steps: 20,
    width: 256,
    height: 256,
    prompt: "",
    negative_prompt: "",
    sampler_index: "Euler",
})

const imageList = ref(["https://p1-arco.byteimg.com/tos-cn-i-uwbnlip3yd/cd7a1aaea8e1c5e3d26fe2591e561798.png~tplv-uwbnlip3yd-webp.webp"])

const customStyle = {
    width: '384px',
    borderRadius: '6px',
    marginLeft: '6px',
    marginRight: '6px',
    marginBottom: '18px',
    border: 'none',
    overflow: 'hidden',
}

// 选择是否开启部分功能
const stepChecked = ref("off");
const styleChecked = ref("off");
const keywordChecked = ref("off");
const sampleChecked = ref("off");

const sampleValue = ref("");

const marks = {
    1: '1',
    4: '4',
    6: '6',
    9: '9'
};
var img = "";
const disabledImgStyle = (event: any) => {
    console.log("yes")
}
const text2img1 = (data: any) => {
    text2img("/sd/text2img", data).then((res) => {
        imageList.value = res.data
        console.log(imageList.value)
    })
}

const inputTime = (e: Event) => {
    text2img1(imageData.value);
}

const width = computed(() => {
    return imageData.value.width * 2
})
const height = computed(() => {
    return imageData.value.height * 2
})
</script>
<style scoped>
.layout-sider {
    text-align: center;
    background-color: var(--color-neutral-1);
    min-width: 420px;
    height: 1200px;
}

.layout-content {
    /* background-color: var(--color-bg-3); */
    min-width: 420px;
    height: 1200px;
}

.menu {
    top: 100rpx;
    left: 100rpx;
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
    width: 60%;
    height: 60%;
    margin-top: 100rpx;
    padding: 60px;
    border: 10px;
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
    margin-top: 100rpx;
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
    width: 110px;
    height: 110px;
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