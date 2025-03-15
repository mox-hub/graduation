<template>
    <!-- 用户界面 -->
    <a-layout>
        <a-layout-content style="text-align: center;">
            <a-space class="page" direction="vertical" style="text-align: center;">
                <!-- 用户信息面板 -->
                <a-card class="card" style="margin-top:40px">
                    <a-space :size="54" style="text-align: center;">
                        <a-avatar :trigger-icon-style="{ color: '#3491FA' }" :auto-fix-font-size="false"
                            :style="{ backgroundColor: '#168CFF' }" :size="80">
                            <IconUser />
                            <template #trigger-icon>
                                <IconCamera />
                            </template>
                        </a-avatar>
                        <a-descriptions :data="renderData" :column="2" align="right" layout="inline-horizontal"
                            :label-style="{
                        width: '140px',
                        fontWeight: 'normal',
                        color: 'rgb(var(--gray-8))',
                    }" :value-style="{
                        width: '200px',
                        paddingLeft: '8px',
                        textAlign: 'left',
                    }">
                            <template #label="{ label }">测试 :</template>
                            <template #value="{ value, data }">
                                <a-tag v-if="data.label === 'userSetting.label.certification'" color="green"
                                    size="small">
                                    已认证
                                </a-tag>
                                <span v-else>{{ value }}</span>
                            </template>
                        </a-descriptions>
                    </a-space>
                </a-card>
                <!-- 用户修改面板 -->
                <a-card class="card">
                </a-card>
            </a-space>
        </a-layout-content>
    </a-layout>
</template>

<script setup lang='ts'>
import { ref, reactive } from 'vue'
// const userStore = useUserStore();
const userStore = reactive({
    avatar: '',
    name: '李浩然',
    accountId: '20230001',
    phone: '15132901146',
    registrationDate: '2023年5月1日'

})
const file = {
    uid: '-2',
    name: 'avatar.png',
    url: userStore.avatar,
};
const renderData = [
    {
        label: 'userSetting.label.name',
        value: userStore.name,
    },
    {
        label: 'userSetting.label.accountId',
        value: userStore.accountId,
    },
    {
        label: 'userSetting.label.phone',
        value: userStore.phone,
    },
    {
        label: 'userSetting.label.registrationDate',
        value: userStore.registrationDate,
    },
];
// const fileList = ref<FileItem[]>([file]);
// const uploadChange = (fileItemList: FileItem[], fileItem: FileItem) => {
//     fileList.value = [fileItem];
// };
// const customRequest = (options: RequestOption) => {
//     // docs: https://axios-http.com/docs/cancellation
//     const controller = new AbortController();

//     (async function requestWrap() {
//         const {
//             onProgress,
//             onError,
//             onSuccess,
//             fileItem,
//             name = 'file',
//         } = options;
//         onProgress(20);
//         const formData = new FormData();
//         formData.append(name as string, fileItem.file as Blob);
//         const onUploadProgress = (event: ProgressEvent) => {
//             let percent;
//             if (event.total > 0) {
//                 percent = (event.loaded / event.total) * 100;
//             }
//             onProgress(parseInt(String(percent), 10), event);
//         };

//         try {
//             // https://github.com/axios/axios/issues/1630
//             // https://github.com/nuysoft/Mock/issues/127

//             const res = await userUploadApi(formData, {
//                 controller,
//                 onUploadProgress,
//             });
//             onSuccess(res);
//         } catch (error) {
//             onError(error);
//         }
//     })();
//     return {
//         abort() {
//             controller.abort();
//         },
//     };
// };


</script>
<style scoped>
.page {
    margin-top: 40px;
    height: 95vh;
}

.card {
    width: 1200px;
}

.arco-card {
    padding: 14px 0 4px 4px;
    border-radius: 4px;
}
</style>