<template>
    <a-layout class="page">
        <a-layout-content>
            <a-tabs class="collect-tabs" size="large" v-model:active-key="activeKey">
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
                <a-tab-pane :key="1">
                    <template #title> <icon-star />我的收藏 </template>
                    <a-checkbox-group v-model="checkboxData" class="tab-page">
                        <a-row :gutter="[16, 16]">
                            <a-col :span="6" v-for="(item, index) in collectList" :key="index">
                                <a-checkbox :value="index">
                                    <template #checkbox="{ checked }">
                                        <div v-if="!checkboxFlag" @click="handleShowDetail(item)" class="image-card">
                                            <a-image class="image-border" fit="cover" :src="item.imageUrl"
                                                v-model:width="imageData.width" v-model:height="imageData.height"
                                                v-model:preview="preview" />
                                            <a-rate class="rate" :model-value="item.rate" readonly />
                                            <div v-if="checkboxFlag" class="custom-checkbox-card"
                                                :class="{ 'custom-checkbox-card-checked': checked }">
                                                <div className="custom-checkbox-card-mask">
                                                    <div className="custom-checkbox-card-mask-dot" />
                                                </div>
                                                <a-image class="image-border" fit="cover" :src="item.imageUrl"
                                                    v-model:width="imageData.width" v-model:height="imageData.height"
                                                    v-model:preview="preview" />
                                            </div>
                                        </div>
                                    </template>
                                </a-checkbox>
                            </a-col>
                        </a-row>
                    </a-checkbox-group>
                </a-tab-pane>
                <!-- 生成历史Tab -->
                <a-tab-pane :key="2">
                    <template #title> <icon-save />生成历史 </template>
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
                                <a-col :span="24" style="
                    display: flex;
                    align-items: center;
                    justify-content: end;
                  ">
                                    <a-popconfirm content="确定要清空历史记录吗?清除后无法恢复" type="warning" okText="确定"
                                        cancelText="取消">
                                        <a-button status="danger" shape="round">
                                            <template #icon>
                                                <icon-delete />
                                            </template>
                                            删除全部
                                        </a-button>
                                    </a-popconfirm>
                                </a-col>
                            </a-row>
                            <a-table :data="historyTableData" :pagination="historyPageSize">
                                <template #columns>
                                    <a-table-column title="历史记录ID" data-index="historyId" />
                                    <a-table-column title="生成类型" data-index="generateType">
                                        <template #cell="{ record }">
                                            <a-tag color="arcoblue" bordered style="border-radius: 5px">
                                                <template #icon>
                                                    <icon-tag />
                                                </template>
                                                {{ record.historyType }}
                                            </a-tag>
                                        </template>
                                    </a-table-column>
                                    <a-table-column title="生成时间" data-index="generateTime" />
                                    <a-table-column title="图片数量" data-index="imageNum" />
                                    <a-table-column title="管理" fixed="right" :width="tableManageWidth">
                                        <template #cell="{ record }">
                                            <a-space>
                                                <a-button type="text" shape="round"
                                                    @click="handleShowHistory(record)">查看详情</a-button>
                                                <a-button type="text" status="danger" shape="round"
                                                    @click="handleDeleteHistory(record)">
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
                <a-tab-pane :key="3">
                    <template #title> <icon-reply />分享链接 </template>
                    <div class="tab-page">
                        <a-card title="分享链接">
                            <a-table :data="shareTableData">
                                <template #columns>
                                    <a-table-column title="分享链接ID" data-index="shareId" />
                                    <a-table-column title="分享时间" data-index="startTime" />
                                    <a-table-column title="截止时间" data-index="endTime" />
                                    <a-table-column title="是否有效" data-index="isValid">
                                        <template #cell="{ record }">
                                            <a-switch type="line" v-model="record.isValid" :checked-value="0"
                                                :unchecked-value="1">
                                                <template #checked-icon>
                                                    <icon-check />
                                                </template>
                                                <template #unchecked-icon>
                                                    <icon-close />
                                                </template>
                                            </a-switch>
                                        </template>
                                    </a-table-column>
                                    <a-table-column title="分享链接" data-index="shareUrl">
                                        <template #cell="{ record }">
                                            {{ record.shareUrl }}
                                            <a-button type="text" shape="circle" v-on:click="handleCopy(record)">
                                                <template #icon>
                                                    <icon-copy />
                                                </template>
                                            </a-button>
                                        </template>
                                    </a-table-column>
                                    <a-table-column title="管理" fixed="right" :width="tableManageWidth">
                                        <template #cell="{ record }">
                                            <a-space>
                                                <a-button status="danger" shape="round" type="text" @click="
                                                    $modal.info({
                                                        title: 'Name',
                                                        content: record.historyId,
                                                    })
                                                    ">
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
            <!-- 弹出模态框 -->
            <a-modal v-model:visible="imageDetailVisible" width="800px">
            <template #title> 收藏详情 </template>
            <!-- 单张图片的详情展示 -->
            <a-space class="image-detail" direction="vertical" size="large">
                <!-- 图片部分 -->
                <a-image fit="cover" :src="imageDetail" v-model:width="width" v-model:height="height"
                    style="border-radius: 10px" />
                <!-- 打分部分 -->
                <a-rate default-value="{{descriptions.rate}}" />
                <!-- 提示词部分 -->
                <a-descriptions title="生成提示词" layout="inline-vertical" bordered>
                    <a-descriptions-item label="正向提示词">{{
                        prompt.prompt
                        }}</a-descriptions-item>
                    <a-descriptions-item label="反向提示词">{{
                        prompt.negative_prompt
                        }}</a-descriptions-item>
                </a-descriptions>
                <!-- 其他参数信息 -->
                <a-descriptions title="其他参数" layout="inline-vertical" bordered>
                    <a-descriptions-item label="生成数量">{{
                        descriptions.batch_size
                        }}</a-descriptions-item>
                    <a-descriptions-item label="生成步数">{{
                        descriptions.steps
                        }}</a-descriptions-item>
                    <a-descriptions-item label="生成采样参数">{{
                        descriptions.cfgScale
                        }}</a-descriptions-item>
                    <a-descriptions-item label="采样方法">{{
                        descriptions.sampler_index
                        }}</a-descriptions-item>
                    <a-descriptions-item label="生成图片规格">{{
                        descriptions.size
                        }}</a-descriptions-item>
                </a-descriptions>
            </a-space>
            <template #footer>
                <!-- 按钮操作区域 -->
                <a-space style="margin-left: 300px">
                    <!-- 收藏单张图片按钮 -->
                    <a-button shape="circle" status="warning">
                        <icon-star-fill />
                    </a-button>
                    <!-- 分享单张图片按钮 -->
                    <a-popover title="分享内容" trigger="click" v-if="descriptions.isShared == 0">
                        <a-button shape="circle" v-on:click="handleShare">
                            <icon-share-alt />
                        </a-button>
                        <template #content>
                            <p>分享链接：{{ shareRequestId.shareUrl }}</p>
                        </template>
                    </a-popover>
                    <a-popover title="已分享！" trigger="click" v-if="descriptions.isShared == 1">
                        <a-button shape="circle" status="success">
                            <icon-share-alt />
                        </a-button>
                        <template #content>
                            <p>请从分享页面查看</p>
                        </template>
                    </a-popover>
                    <!-- 删除单张图片按钮 -->
                    <a-button shape="circle" v-on:click="handleDeleteCollect">
                        <icon-delete />
                    </a-button>
                    <a-space style="margin-left: 20px">
                        <!-- 下载图片 -->
                        <a-button type="primary" shape="round" size="large" v-on:click="handleDownload">
                            <template #icon>
                                <icon-download />
                            </template>
                            下载该图片
                        </a-button>
                        <!-- 生成该图片跳转 -->
                        <a-button shape="round" size="large" v-on:click="handleGenerate">
                            <template #icon>
                                <icon-sync />
                            </template>
                            前往生成页面
                        </a-button>
                    </a-space>
                </a-space>
            </template>
        </a-modal>
<a-modal v-model:visible="historyDetailVisible" width="800px">
            <template #title> 历史记录详情 </template>
            <!-- 单张图片的详情展示 -->
            <a-space class="image-detail" direction="vertical" size="large">
                <!-- 图片部分 -->
                <a-image fit="cover" :src="imageDetail" v-model:width="width" v-model:height="height"
                    style="border-radius: 10px" />
                <!-- 提示词部分 -->
                <a-descriptions title="生成提示词" layout="inline-vertical" bordered>
                    <a-descriptions-item label="正向提示词">{{
                        prompt.prompt
                        }}</a-descriptions-item>
                    <a-descriptions-item label="反向提示词">{{
                        prompt.negative_prompt
                        }}</a-descriptions-item>
                </a-descriptions>
                <!-- 其他参数信息 -->
                <a-descriptions title="其他参数" layout="inline-vertical" bordered>
                    <a-descriptions-item label="生成数量">{{
                        descriptions.batch_size
                        }}</a-descriptions-item>
                    <a-descriptions-item label="生成步数">{{
                        descriptions.steps
                        }}</a-descriptions-item>
                    <a-descriptions-item label="生成采样参数">{{
                        descriptions.cfgScale
                        }}</a-descriptions-item>
                    <a-descriptions-item label="采样方法">{{
                        descriptions.sampler_index
                        }}</a-descriptions-item>
                    <a-descriptions-item label="生成图片规格">{{
                        descriptions.size
                        }}</a-descriptions-item>
                </a-descriptions>
            </a-space>
            <template #footer>
                <!-- 按钮操作区域 -->
                <a-space style="margin-left: 300px">
                    <!-- 收藏单张图片按钮 -->
                    <a-button shape="circle" status="warning" v-on:click="handleCollect">
                        <icon-star-fill />
                    </a-button>
                    <a-space style="margin-left: 20px">
                        <!-- 下载图片 -->
                        <a-button type="primary" shape="round" size="large" v-on:click="handleDownload">
                            <template #icon>
                                <icon-download />
                            </template>
                            下载该图片
                        </a-button>
                        <!-- 生成该图片跳转 -->
                        <a-button shape="round" size="large" v-on:click="handleGenerate">
                            <template #icon>
                                <icon-sync />
                            </template>
                            前往生成页面
                        </a-button>
                    </a-space>
                </a-space>
            </template>
        </a-modal>
</a-layout>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import { getData, deleteData, updateData, insertData } from '../../api'
import useClipboard from 'vue-clipboard3'
import { Modal } from '@arco-design/web-vue'
import router from '../../router/router'
const { toClipboard } = useClipboard()
const checkAll = ref(false)
const checkboxData = ref([])
const tableManageWidth = ref(200)
const activeKey = ref(1)

const imageDetailVisible = ref(false)
const historyDetailVisible = ref(false)
const preview = ref(false)
const historyPageSize = ref({
    total: 0,
    current: 1,
    pageSize: 10,
    onChange: (page: number) => {
        historyPageSize.value.current = page
        historyRequest.value.pageIndex = page
        getHistoryList()
    }
})

const imageData = ref({
    seed: -1,
    batch_size: 1,
    steps: 20,
    width: 256,
    height: 256,
    prompt: '',
    negative_prompt: '',
    sampler_index: 'Euler',
})

/** =========================================================================
 *                            收藏信息页面
 * ========================================================================== */

// 收藏页面图片展示宽度
const width = computed(() => {
    return imageData.value.width * 2
})
// 收藏页面图片展示高度
const height = computed(() => {
    return imageData.value.height * 2
})

const collectList = ref([])

// 设置控件的显示情况，ususal 显示普通按钮，manage 显示管理按钮
const buttonFlag = ref('usual')
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

const handleCheckAll = (value: any) => {
    if (value) {
        checkAll.value = true
        console.log(checkboxData.value)
    }
}
interface collect {
    collectId: number
    userId: number
    imageId: number
    promptId: number
    rate: number
    isShared: number
    pageIndex: number
    pageSize: number
}
const collectRequest = ref<collect>({
    collectId: 21,
    userId: 1,
    imageId: 1,
    promptId: 1,
    rate: 0,
    isShared: 0,
    pageIndex: 1,
    pageSize: 10,
})
const getCollectList = () => {
    getData(collectRequest.value, '/collect/getCollectByUserId').then((res) => {
        collectList.value = res.data
        console.log(res.data)
    })
}

const updateCollect = () => { }

/** =========================================================================
 *                            历史记录页面
 * ========================================================================== */

// 历史记录搜索框数据
const HistorySearchForm = ref({
    value1: '',
})
// 历史记录信息表单数据
const historyTableData = ref([])
// 历史记录类型
interface history {
    historyId: number
    userId: number
    imageId: number
    promptId: number
    imageNum: number
    historyType: string
    generateTime: Date | any
    pageIndex: number
    pageSize: number
}
// 历史记录请求参数
const historyRequest = ref<history>({
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

// 处理删除一条历史记录
const handleDeleteHistory = (record: any) => {
    historyRequest.value = record
    historyRequest.value.pageIndex = 1
    historyRequest.value.pageSize = 100
    deleteHistoryByHistoryId()
    getHistoryList()
}

// =====================网络请求===================================
// 历史记录查询
const getHistoryList = () => {
    getData(historyRequest.value, '/history/getHistoryByUserId').then((res) => {
        historyTableData.value = res.data.records
        historyPageSize.value.total = res.data.total
    })
}
// 删除一条历史记录
const deleteHistoryByHistoryId = () => {
    deleteData(historyRequest.value, '/history/deleteHistoryByHistoryId').then(
        (res) => {
            Modal.success({
                title: '删除提示',
                content: '本条历史记录已删除',
            })
        }
    )
}

/** =========================================================================
 *                            分享链接页面
 * ========================================================================== */

// 分享信息表单数据
const shareTableData = ref([])
interface share {
    shareId: string
    userId: number
    imageId: number
    promptId: number
    startTime: Date | any
    endTime: Date | any
    isValid: number
    shareUrl: string
    pageIndex: number
    pageSize: number
}
// 分享记录请求参数
const shareRequest = ref<share>({
    shareId: '',
    userId: 1,
    imageId: 1,
    promptId: 1,
    startTime: new Date().toISOString(),
    endTime: new Date().toISOString(),
    isValid: 0,
    shareUrl: '',
    pageIndex: 1,
    pageSize: 100,
})

// =====================网络请求===================================
// 分享记录查询
const getShareList = () => {
    getData(shareRequest.value, '/share/getShareListByUserId').then((res) => {
        shareTableData.value = res.data
        console.log(res.data)
    })
}
// 删除一条分享记录
const deleteShareByShareId = () => {
    deleteData(shareRequest.value, '/share/deleteShareByShareId').then((res) => {
        Modal.success({
            title: '删除提示',
            content: '本条分享记录已删除',
        })
        location.reload()
    })
}

/** =========================================================================
 *                            图片详情页面
 * ========================================================================== */

const shareImageId = ref(0)
const sharePromptId = ref(0)

// 图片详情页展示图片
const imageDetail = ref(
    'https://p1-arco.byteimg.com/tos-cn-i-uwbnlip3yd/cd7a1aaea8e1c5e3d26fe2591e561798.png~tplv-uwbnlip3yd-webp.webp'
)
// 提示词
const prompt = reactive({
    prompt: 'test',
    negative_prompt: 'negative test',
})
// 其他描述
const descriptions = reactive({
    batch_size: 1,
    steps: 20,
    sampler_index: 'Euler',
    cfgScale: 7,
    size: '512像素 X 512像素',
    width: 512,
    height: 512,
    rate: 0,
    isShared: 0,
})

const imageRequest = ref({
    imageId: 0,
    imageUrl: '',
    pageIndex: 1,
    pageSize: 10,
})

const promptRequest = reactive({
    promptId: 1,
    pageIndex: 1,
    pageSize: 10,
})
let date = new Date()
date.setMonth(date.getMonth() + 3)
const shareRequestId = reactive({
    shareId: '',
    userId: 1,
    imageId: 1,
    promptId: 1,
    startTime: new Date().toISOString(),
    endTime: date.toISOString(),
    isValid: 0,
    shareUrl: '',
    pageIndex: 1,
    pageSize: 10,
})
// 获得详细信息的图片
const getImageDetail = () => {
    getData(imageRequest.value, '/image/getImageByImageId').then((res) => {
        console.log(res)
        imageDetail.value = res.data.imageUrl
    })
}

const getPromptData = (data: any) => {
    getData(data, '/prompt/getPromptByPromptId').then((res) => {
        console.log(res.data)
        prompt.prompt = res.data.prompt
        prompt.negative_prompt = res.data.negative_prompt
        descriptions.batch_size = res.data.batch_size
        descriptions.size =
            res.data.height.toString() +
            '像素 X ' +
            res.data.width.toString() +
            '像素'
        descriptions.width = res.data.width
        descriptions.height = res.data.height
        descriptions.steps = res.data.steps
        descriptions.cfgScale = res.data.cfgScale
        descriptions.sampler_index = res.data.sampler_index
    })
}

const createShareData = () => {
    insertData(shareRequestId, '/share/createShare').then((res) => {
        console.log(res)
    })
}

const createCollectData = () => {
    insertData(collectRequest.value, '/collect/createCollect').then((res) => {
        console.log(res)
        if (res.code == 0) {
            Modal.success({
                title: '收藏提示',
                content: '本图片已成功收藏',
            })
        }
        getCollectList()
    })
}

const downloadImage = (imgSrc: string, name: string) => {
    return new Promise((resolve, reject) => {
        let image = new Image()
        image.setAttribute('crossOrigin', 'anonymous')
        image.src = imgSrc
        image.onerror = () => {
            Modal.error({
                title: '下载失败',
                content: '图片加载失败，请稍后重试'
            })
            reject(new Error('Image load failed'))
        }
        image.onload = function () {
            try {
                let canvas = document.createElement('canvas')
                canvas.width = descriptions.width
                canvas.height = descriptions.height
                let context = canvas.getContext('2d')
                if (!context) {
                    throw new Error('Canvas context not supported')
                }
                context.drawImage(image, 0, 0, descriptions.width, descriptions.height)
                let url = canvas.toDataURL('image/png')
                let a = document.createElement('a')
                let event = new MouseEvent('click')
                a.download = name || 'photo'
                a.href = url
                a.dispatchEvent(event)
                Modal.success({
                    title: '下载成功',
                    content: '图片已成功下载到本地'
                })
                resolve(true)
            } catch (error) {
                Modal.error({
                    title: '下载失败',
                    content: '图片处理失败，请稍后重试'
                })
                reject(error)
            }
        }
    })
}

// 处理查看一条记录
const handleShowDetail = async (record: any) => {
    descriptions.rate = record.rate
    descriptions.isShared = record.isShared
    console.log(record)
    imageRequest.value.imageId = record.imageId
    await getImageDetail()
    promptRequest.promptId = record.promptId
    collectRequest.value.collectId = record.collectId
    console.log(collectRequest.value.collectId)
    console.log(promptRequest)
    await getPromptData(promptRequest)
    imageDetailVisible.value = true
    // 分享操作
    shareRequestId.imageId = record.imageId
    shareRequestId.promptId = record.promptId
    shareRequestId.shareUrl =
        'http://localhost:5173/#/home/generate/' +
        record.promptId +
        '/' +
        record.imageId
}

const handleShowHistory = async (record: any) => {
    descriptions.rate = record.rate
    descriptions.isShared = record.isShared
    // console.log(record);
    imageRequest.value.imageId = record.imageId
    collectRequest.value.imageId = record.imageId
    await getImageDetail()
    promptRequest.promptId = record.promptId
    collectRequest.value.promptId = record.promptId
    // console.log(promptRequest)
    await getPromptData(promptRequest)
    historyDetailVisible.value = true
    // 分享操作
    shareRequestId.imageId = record.imageId
    shareRequestId.promptId = record.promptId
    shareRequestId.shareUrl =
        'http://localhost:5173/#/home/generate/' +
        record.promptId +
        '/' +
        record.imageId
}

const handleDeleteCollect = () => {
    deleteData(collectRequest.value, '/collect/deleteCollectByCollectId').then(
        (res) => {
            console.log(res)
            Modal.success({
                title: '删除提示',
                content: '收藏记录已成功删除',
            })
        }
    )
    location.reload()
}

const handleDownload = () => {
    let imgUrl = imageDetail.value
    downloadImage(imgUrl, 'sample_pictures')
}

const handleGenerate = () => {
    router.push(
        '/home/generate/' +
        imageRequest.value.imageId +
        '/' +
        promptRequest.promptId
    )
}
const handleShare = async () => {
    await createShareData()
    descriptions.isShared = 1
    Modal.success({
        title: '分享成功',
        content: '图片已成功分享，可以在分享链接页面查看'
    })
    getShareList()
}

const handleCollect = () => {
    console.log(collectRequest.value)
    createCollectData()
    getShareList()
}

const handleCopy = (record: any) => {
    toClipboard(record.shareUrl) //实现复制
    Modal.success({
        title: '复制成功',
        content: '链接已复制到剪切板',
    })
}
watch(
    () => activeKey,
    (newVal, oldVal) => {
        console.log(newVal, oldVal)
    }
)

getCollectList()
getHistoryList()
getShareList()
</script>
<style scoped>
.page {
    margin-top: 50px;
}
.collect-tabs {
    padding: 20px;
}

.tab-page {
    padding: 16px;
}

.image-card {
    position: relative;
    border-radius: 8px;
    overflow: hidden;
    transition: all 0.3s;
    cursor: pointer;
}

.image-card:hover {
    transform: translateY(-4px);
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.image-border {
    width: 100%;
    border-radius: 8px;
    overflow: hidden;
}

.rate {
    position: absolute;
    bottom: 8px;
    left: 8px;
    background: rgba(255, 255, 255, 0.8);
    padding: 4px;
    border-radius: 4px;
}

.custom-checkbox-card {
    position: relative;
    border: 2px solid transparent;
    border-radius: 8px;
    transition: all 0.3s;
}

.custom-checkbox-card-checked {
    border-color: rgb(var(--primary-6));
}

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