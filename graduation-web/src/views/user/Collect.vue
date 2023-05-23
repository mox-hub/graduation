<template>
    <a-layout>
        <a-layout-content>
            <a-tabs class="collect-tabs" size="large">
                <template #extra>
                    <!-- 标签页的tab操作 -->
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
                <!-- 我的收藏Tab -->
                <a-tab-pane key="1">
                    <template #title>
                        <icon-star />我的收藏
                    </template>
                    <a-checkbox-group v-model="checkboxData" class="tab-page">
                        <template v-for="(item, index) in imageList" :key="index">
                            <a-checkbox :value="index">
                                <template #checkbox="{ checked }">
                                    <div class="custom-checkbox-card" :class="{ 'custom-checkbox-card-checked': checked }">
                                        <div v-if="checkboxFlag" className="custom-checkbox-card-mask">
                                            <div className="custom-checkbox-card-mask-dot" />
                                        </div>
                                        <a-image class="image-border" fit="cover" :src="item"
                                            v-model:width="imageData.width" v-model:height="imageData.height"
                                            v-model:preview="preview" />
                                        <a-rate class="rate" :default-value="4" readonly />
                                    </div>
                                </template>
                            </a-checkbox>
                        </template>
                    </a-checkbox-group>
                </a-tab-pane>
                <!-- 生成历史Tab -->
                <a-tab-pane key="2">
                    <template #title>
                        <icon-save />生成历史
                    </template>
                    <div class="tab-page">
                        <a-card title="生成历史">
                            <a-form :model="HistorySearchForm">
                                <a-row :gutter="16">
                                    <a-col :span="6">
                                        <a-form-item field="value1" label="历史记录ID" label-col-flex="100px">
                                            <a-input v-model="HistorySearchForm.value1" placeholder="please enter..." />
                                        </a-form-item>
                                    </a-col>
                                    <a-col :span="6">
                                        <a-form-item field="value1" label="生成类型" label-col-flex="100px">
                                            <a-input v-model="HistorySearchForm.value1" placeholder="please enter..." />
                                        </a-form-item>
                                    </a-col>
                                    <a-col :span="8">
                                        <a-form-item field="value1" label="生成时间范围" label-col-flex="100px">
                                            <a-input v-model="HistorySearchForm.value1" placeholder="please enter..." />
                                        </a-form-item>
                                    </a-col>
                                    <a-divider style="height: 34px" direction="vertical" />
                                    <a-space>
                                        <a-button shape="round">
                                            <template #icon>
                                                <icon-search />
                                            </template>
                                            查询历史
                                        </a-button>
                                        <a-button shape="round">
                                            <template #icon>
                                                <icon-refresh />
                                            </template>
                                            清空参数
                                        </a-button>
                                    </a-space>
                                </a-row>
                            </a-form>
                            <a-divider style="margin-top: 0" />
                            <!-- 表格全局操作栏 -->
                            <a-row style="margin-bottom: 16px">
                                <a-col :span="24" style="display: flex; align-items: center; justify-content: end">
                                    <a-tooltip content="清空全部历史记录">
                                        <a-button status="danger" shape="round">
                                            <template #icon>
                                                <icon-delete />
                                            </template>
                                            删除全部
                                        </a-button>
                                    </a-tooltip>
                                </a-col>
                            </a-row>
                            <a-table :data="historyTableData">
                                <template #columns>
                                    <a-table-column title="历史记录ID" data-index="historyId" />
                                    <a-table-column title="生成类型" data-index="generateType" />
                                    <a-table-column title="生成时间" data-index="generateTime" />
                                    <a-table-column title="图片数量" data-index="imageNum" />
                                    <a-table-column title="管理" fixed="right" :width="tableManageWidth">
                                        <template #cell="{ record }">
                                            <a-space>
                                                <a-button type="primary" shape="round"
                                                    @click="$modal.info({ title: 'Name', content: record.historyId })">查看详情</a-button>
                                                <a-button status="danger" shape="round"
                                                    @click="$modal.info({ title: 'Name', content: record.historyId })">
                                                    <template #icon>
                                                        <icon-delete />
                                                    </template>
                                                    删除
                                                </a-button>
                                            </a-space>
                                        </template>
                                    </a-table-column>
                                </template>
                            </a-table>
                        </a-card>
                    </div>
                </a-tab-pane>
                <!-- 分享链接Tab -->
                <a-tab-pane key="3">
                    <template #title>
                        <icon-reply />分享链接
                    </template>
                    <div class="tab-page">
                        <a-card title="分享链接">
                            <a-table :data="shareTableData">
                                <template #columns>
                                    <a-table-column title="分享链接ID" data-index="shareId" />
                                    <a-table-column title="分享时间" data-index="createTime" />
                                    <a-table-column title="截止时间" data-index="deleteTime" />
                                    <a-table-column title="是否有效" data-index="isRight" />
                                    <a-table-column title="分享链接" data-index="link">
                                        <template #cell="{ record }">
                                            {{ record.link }}
                                            <a-button type="text" shape="circle"
                                                @click="$modal.info({ title: 'Name', content: record.historyId })">
                                                <template #icon>
                                                    <icon-copy />
                                                </template>
                                            </a-button>
                                        </template>
                                    </a-table-column>
                                    <a-table-column title="管理" fixed="right" :width="tableManageWidth">
                                        <template #cell="{ record }">
                                            <a-space>
                                                <a-button status="danger" shape="round"
                                                    @click="$modal.info({ title: 'Name', content: record.historyId })">
                                                    <template #icon>
                                                        <icon-delete />
                                                    </template>
                                                    删除
                                                </a-button>
                                            </a-space>
                                        </template>
                                    </a-table-column>
                                </template>
                            </a-table>
                        </a-card>
                    </div>
                </a-tab-pane>
            </a-tabs>
        </a-layout-content>
        <!-- 收藏页面:弹出模态框 -->
        <a-modal v-model:visible="visible" width="800px">
            <template #title>
                收藏详情
            </template>
            <template #footer>
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
            </template>
            <!-- 单张图片的详情展示 -->
            <a-space class="image-detail" direction="vertical" size="large">
                <!-- 图片部分 -->
                <a-image fit="cover" :src="imageDetail" v-model:width="width" v-model:height="height" />
                <!-- 打分部分 -->
                <a-rate :default-value="2.5" allow-half />
                <!-- 提示词部分 -->
                <a-descriptions :data="prompt" title="生成提示词" layout="inline-vertical" bordered />
                <!-- 其他参数信息 -->
                <a-descriptions :data="descriptions" title="其他参数" layout="inline-vertical" bordered />
            </a-space>
        </a-modal>
    </a-layout>
</template>

<script setup lang='ts'>
import { ref, reactive } from 'vue'

const checkAll = ref(false)
const checkboxData = ref([])
const tableManageWidth = ref(200)


const visible = ref(true)
const preview = ref(false)
const handleCheckAll = (value: any) => {
    if (value) {
        checkAll.value = true;
        console.log(checkboxData.value);
    }
}

const collectCheck = ref([])

const shareTableData = reactive([
    {
        shareId: '1',
        createTime: '2023-4-1',
        deleteTime: '2023-6-1',
        isRight: true,
        link: 'http://localhost:5173/',
    },
    {
        shareId: '2',
        createTime: '2023-4-1',
        deleteTime: '2023-6-1',
        isRight: true,
        link: 'http://localhost:5173/',
    },
    {
        shareId: '3',
        createTime: '2023-4-1',
        deleteTime: '2023-6-1',
        isRight: true,
        link: 'http://localhost:5173/',
    },
])

const historyTableData = reactive([
    {
        historyId: '1',
        generateTime: '2022-01-04',
        generateType: 'text-to-image',
        imageNum: '2',
    },
    {
        historyId: '2',
        generateTime: '2022-01-04',
        generateType: 'text-to-image',
        imageNum: '9',
    },
    {
        historyId: '3',
        generateTime: '2022-01-23',
        generateType: 'text-to-image',
        imageNum: '1',
    },
    {
        historyId: '4',
        generateTime: '2022-01-14',
        generateType: 'text-to-image',
        imageNum: '2',
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
const HistorySearchForm = ref({
    value1: '',
})
const width = computed(() => {
    return imageData.value.width * 2
})
const height = computed(() => {
    return imageData.value.height * 2
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

.tab-page {
    height: 90vh;
}

.image-border {
    border-radius: 10px;
}

.image-detail {
    width: 760px;
    text-align: center;
}

.rate {
    position: absolute;
    left: 10px;
    bottom: 10px;
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