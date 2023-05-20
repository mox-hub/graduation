<template>
    <a-layout>
        <a-layout>
            <a-layout-content>
                <a-tabs class="collect-tabs" size="large">
                    <template #extra>
                        <a-space v-if="buttonFlag == 'manage'">
                            <a-button @click="handleCheckAll">
                                <template #icon>
                                    <icon-select-all />
                                </template>
                                全部选择
                            </a-button>
                            <a-button disabled>
                                <template #icon>
                                    <icon-delete />
                                </template>
                                删除选中
                            </a-button>
                            <a-button disabled>
                                <template #icon>
                                    <icon-download />
                                </template>
                                下载选中
                            </a-button>
                            <a-button shape="round" @click="changeButtonFlag('usual', false)">
                                <template #icon>
                                    <icon-export />
                                </template>
                                退出
                            </a-button>
                        </a-space>
                        <a-space v-if="buttonFlag == 'usual'">
                            <a-select defaultValue="所有类型" placeholder="选择图片来源">
                                <a-option>所有类型</a-option>
                                <a-option>文本生成图片</a-option>
                                <a-option>图片生成图片</a-option>
                            </a-select>
                            <a-button shape="round" type="primary" @click="changeButtonFlag('manage', true)">
                                <template #icon>
                                    <icon-command />
                                </template>
                                管理
                            </a-button>
                        </a-space>
                    </template>
                    <a-tab-pane key="1">
                        <template #title>
                            <icon-star />我的收藏
                        </template>
                        <a-checkbox-group v-model="checkboxData" class="image">
                            <template v-for="item in imageList" :key="item.index">
                                <a-checkbox :value="index">
                                    <template #checkbox="{ checked }">
                                        <div class="custom-checkbox-card"
                                            :class="{ 'custom-checkbox-card-checked': checked }">
                                            <div v-if="checkboxFlag" className="custom-checkbox-card-mask">
                                                <div className="custom-checkbox-card-mask-dot" />
                                            </div>
                                            <!-- <div>
                                                <div className="custom-checkbox-card-title">
                                                    Checkbox Card {{ item }}
                                                </div>
                                                <a-typography-text type="secondary">
                                                    this is a text
                                                </a-typography-text>
                                            </div> -->
                                            <a-image class="image-border" fit="cover" :src="item"
                                                v-model:width="imageData.width" v-model:height="imageData.height" />
                                        </div>
                                    </template>
                                </a-checkbox>
                            </template>
                        </a-checkbox-group>
                    </a-tab-pane>
                    <a-tab-pane key="2">
                        <template #title>
                            <icon-save />生成历史
                        </template>
                        <icon-save />
                        Content of Tab Panel 2
                        <a-table :columns="columns" :data="data" />
                    </a-tab-pane>
                    <a-tab-pane key="3">
                        <template #title>
                            <icon-reply />分享链接
                        </template>
                        Content of Tab Panel 3
                        <a-table :columns="columns" :data="data" />
                    </a-tab-pane>
                </a-tabs>
            </a-layout-content>
            <a-modal v-model:visible="visible" @ok="handleOk" @cancel="handleCancel" width="auto">
                <template #title>
                    收藏详情
                </template>
                <!-- 单张图片的详情展示 -->
                <a-space class="image-detail" direction="vertical" size="large">
                    <!-- 图片部分 -->
                    <a-image fit="cover" :src="imageDetail" v-model:width="width" v-model:height="height" />
                    <!-- 按钮操作区域 -->
                    <a-space style="margin-left: 300px;">
                        <!-- 收藏单张图片按钮 -->
                        <a-button shape="circle" status="warning">
                            <icon-star-fill />
                        </a-button>
                        <!-- 分享单张图片按钮 -->
                        <a-button shape="circle">
                            <icon-share-alt />
                        </a-button>
                        <!-- 删除单张图片按钮 -->
                        <a-button shape="circle">
                            <icon-delete />
                        </a-button>
                        <a-space style="margin-left: 20px;">
                            <!-- 下载图片 -->
                            <a-button type="primary" shape="round" size="large">
                                <template #icon>
                                    <icon-download />
                                </template>
                                下载该图片
                            </a-button>
                            <!-- 生成该图片跳转 -->
                            <a-button shape="round" size="large">
                                <template #icon>
                                    <icon-sync />
                                </template>
                                前往生成页面
                            </a-button>
                        </a-space>
                    </a-space>
                    <a-descriptions :data="prompt" title="生成提示词" layout="inline-vertical" bordered />
                    <a-descriptions :data="descriptions" title="其他参数" layout="inline-vertical" bordered />
                </a-space>
            </a-modal>
        </a-layout>
        <a-layout-footer>

        </a-layout-footer>
    </a-layout>
</template>

<script setup lang='ts'>
import { ref, reactive } from 'vue'


const checkAll = ref(false)
const checkboxData = ref([])

const visible = ref(true)

const handleCheckAll = (value: any) => {
    if (value) {
        checkAll.value = true;
        console.log(checkboxData.value);
    }
}

const columns = [
    {
        title: '收藏ID',
        dataIndex: 'collectId',
    },
    {
        title: '用户ID',
        dataIndex: 'userId',
    },
    {
        title: '图片ID',
        dataIndex: 'imageId',
    },
]

const data = reactive([
    {
        collectId: '1',
        userId: '2',
        imageId: '1',
    },
    {
        collectId: '2',
        userId: '2',
        imageId: '1',
    },
    {
        collectId: '3',
        userId: '2',
        imageId: '1',
    },

])

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
const width = computed(() => {
    return imageData.value.width * 2
})

const height = computed(() => {
    return imageData.value.height * 2
})
const fullName = computed(() => {
    return firstName.value + '-·-' + lastName.value
})

const imageDetail = ref("https://p1-arco.byteimg.com/tos-cn-i-uwbnlip3yd/cd7a1aaea8e1c5e3d26fe2591e561798.png~tplv-uwbnlip3yd-webp.webp")

const imageList = ref(["https://p1-arco.byteimg.com/tos-cn-i-uwbnlip3yd/cd7a1aaea8e1c5e3d26fe2591e561798.png~tplv-uwbnlip3yd-webp.webp"
    , "https://p1-arco.byteimg.com/tos-cn-i-uwbnlip3yd/cd7a1aaea8e1c5e3d26fe2591e561798.png~tplv-uwbnlip3yd-webp.webp"
    , "https://p1-arco.byteimg.com/tos-cn-i-uwbnlip3yd/cd7a1aaea8e1c5e3d26fe2591e561798.png~tplv-uwbnlip3yd-webp.webp"])


const descriptions = reactive([{
    label: 'Name',
    value: 'Socrates',
}, {
    label: 'Mobile',
    value: '123-1234-1234',
}, {
    label: 'Residence',
    value: 'Beijing'
}, {
    label: 'Hometown',
    value: 'Beijing',
}, {
    label: 'Address',
    value: 'Yingdu Building, Zhichun Road, Beijing'
}])

const prompt = reactive([{
    label: '正向提示词',
    value: 'a girl',
}, {
    label: '反向提示词',
    value: 'bad hand',
}])

// 设置控件的显示情况，ususal 显示普通按钮，manage 显示管理按钮
const buttonFlag = ref("usual")
const checkboxFlag = ref(false)

/**
 * 点击管理按钮时，对菜单的显示进行更改
 * 同时对checkbox的显示进行更改
 */
const changeButtonFlag = (flag: any, checkbox: any) => {
    buttonFlag.value = flag
    checkboxFlag.value = checkbox
    console.log(buttonFlag.value)
}

</script>
<style scoped>
.collect-tabs {
    margin-top: 10px;
    margin-left: 10px;
    margin-right: 20px;
}

.image {
    height: 90vh;
}

.image-border {
    border-radius: 10px;
}

.image-detail {
    text-align: center;
}

/* checkbox */
.custom-checkbox-card {
    border: 1px solid var(--color-border-2);
    border-radius: 10px;
    width: 258px;
    height: 258px;
    box-sizing: border-box;
}

.custom-checkbox-card-mask {
    z-index: 100;
    height: 18px;
    width: 18px;
    top: 10px;
    margin-left: 10px;
    position: absolute;
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
    margin-bottom: 8px;
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