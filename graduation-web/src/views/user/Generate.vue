<template>
    <a-layout>
        <a-layout-content>
            <!-- 页面标题 -->
            <a-typography class="title-box">
                <a-typography-title bold>
                    Stable-diffusion：通过文本生成风格化图片
                </a-typography-title>
            </a-typography>
            <!-- card -->
            <a-card class="body-card">
                <!-- 图片预览框 -->
                <a-image-preview-group>
                    <a-space>
                        <div v-for="item in imageList" :key="item">
                            <a-image fit="cover" :src="item" v-model:width="imageData.width"
                                v-model:height="imageData.height" />
                        </div>
                    </a-space>
                </a-image-preview-group>
                <a-card-meta>
                    <template #description>
                        <!-- step 1 输入正向文本 -->
                        <a-space direction="vertical" :style="{ width: '100%' }">
                            <a-typography-text type="secondary" :style="{ marginLeft: '10px', fontSize: '24px' }" bold>
                                请输入要生成图片的文本{{ imageData }}
                            </a-typography-text>

                            <a-textarea class="textarea" placeholder="请输入正向文本" :max-length="128" allow-clear
                                v-model:model-value="imageData.prompt" show-word-limit />
                            <a-textarea class="textarea" placeholder="请输入反向文本"
                                v-model:model-value="imageData.negative_prompt"
                                :max-length="{ length: 128, errorOnly: true }" allow-clear show-word-limit />

                        </a-space>

                        <a-button type="primary" shape="round" size="large" @click="inputTime">生成</a-button>
                    </template>
                </a-card-meta>
                <template #actions>
                </template>
                <template #cover>
                </template>
            </a-card>
        </a-layout-content>
        <!-- 侧边栏功能：选择参数 -->
        <a-layout-sider :resize-directions="['left']">
            <!-- step1: 选择图片数量 -->
            <div class="choose-num">
                <a-typography-title :heading="6" :style="{ marginLeft: '10px' }">
                    选择生成图片数量
                </a-typography-title>
                <a-slider :default-value="1" :style="{
                    width: '340px', paddingBottom: '0px'
                }" :min="1" :max="9" :marks="marks" v-model:model-value="imageData.batch_size" />
            </div>
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
                <a-collapse-item header="选择生成步数" :style="customStyle" key="2">
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
                <a-collapse-item header="选择图片风格" :style="customStyle" :key="3">
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
                        <template v-for="item in 15" :key="item">
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
                <a-collapse-item header="选择关键词" :style="customStyle" key="4">
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
                <a-collapse-item header="选择采样方法" :style="customStyle" key="6">
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
                    <a-select :style="{ width: '100%' }" v-model="sampleValue" placeholder="Please select ..." allow-clear
                        :disabled="sampleChecked == 'off'">
                        <a-option>Beijing</a-option>
                        <a-option>Shanghai</a-option>
                        <a-option>Guangzhou</a-option>
                        <a-option disabled>Disabled</a-option>
                    </a-select>
                </a-collapse-item>
            </a-collapse>
        </a-layout-sider>
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
</script>
<style scoped>
.arco-layout-sider {
    width: 420px;
    background-color: var(--color-neutral-1);
    min-width: 420px;
    max-width: 1200px;
    height: 1200px;
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

.body-card {
    display: table;
    margin: auto;
    width: 80%;
    height: 60%;
    margin-top: 100rpx;
    padding: 60px;
    border: 10px;
}

.textarea {
    width: 80%;
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