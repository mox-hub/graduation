<template>
  <a-layout class="layout">
    <a-layout-content class="layout-content">
      <!-- 页面标题 -->
      <div class="title-box">
        <div class="title-text">
          Stable Diffusion
        </div>
        <div class="subtitle-text">
          通过文本生成风格化图片
        </div>
        <a-typography>
          <a-typography-title :heading="4" style="font-family: 'Fira Code'">
            Design and implementation of web online classification system for news text
          </a-typography-title>
        </a-typography>
      </div>

      <a-space direction="vertical" size="large">
        <router-link to="/home/generate">
          <a-button class="start-button">
            开始生成 &emsp;
            <icon-double-right />
          </a-button>
        </router-link>
        <a-typography>
          <a-typography-title :heading="3"  style="font-family: 幼圆, serif; font-weight: 600">
            通过文本：生成风格多样化的图片
          </a-typography-title>
        </a-typography>
        <a-space size="large">
          <div v-for="(item, index) in imageList" :key="index">
            <a-image class="image-border" fit="cover" :src="item.imageUrl"
                     v-model:width="item.width" v-model:height="item.height"
                     v-model:preview="preview" />
          </div>
        </a-space>
        <a-typography style="text-align: center">
          <a-typography-title :heading="3"  style="font-family: 幼圆, serif; font-weight: 600">
            关于项目情况
          </a-typography-title>
          <a-typography-paragraph style="font-size: 20px; width: 1200px">
            本项目为合肥工业大学毕设项目。基于对现有的图像生成方法的研究，选用了Stable Diffusion模型作为项目的主要依托，对风格化图片生成系统进行工作研究，完成了本系统的设计和实现。
          </a-typography-paragraph>
        </a-typography>
        <a-typography style="text-align: center">
          <a-typography-title :heading="3" style="font-family: 幼圆, serif; font-weight: 600">
            Stable Diffusion模型介绍
          </a-typography-title>
        </a-typography>
        <a-card class="card" hoverable>
          <a-row style="width: 1200px" justify="center">
            <a-col :span="12" >
              <a-image class="card-image" :src="cardImage" :preview="preview" :width="600" :height="600"></a-image>
            </a-col>
            <a-col :span="12">
              <a-typography class="card-typography">
                <a-typography-title style="font-weight: 600">
                  Stable Diffusion
                </a-typography-title>
                <a-typography-paragraph style="font-size: 20px">
                  Stable Diffusion模型，简称SD，使用的是近年来较为流行扩散模型（Diffusion）。最初是基于Latent Diffusion Models（潜在扩散模型，LDMs）的一种文本生成图像（text-to-image）模型。该模型是在Stability AI提供的计算支持和LAION提供的数据支持的加持下，Stable Diffusion在LAION-5B的子集上对Latent Diffusion Models进行训练，从而得到了用于文本生成图像的模型。
                </a-typography-paragraph>
                <a-typography-paragraph style="font-size: 20px">
                  Latent Diffusion Models通过潜在表示空间的层面中中迭代“去噪”数据来生成图像，再将潜在空间中的结果解码为完整的图像，让文图生成的模型能够在普通的GPU上，在较快的时间内生成图片，大大降低了用户的使用门槛。
                </a-typography-paragraph>
              </a-typography>
            </a-col>
          </a-row>
        </a-card>
      </a-space>


    </a-layout-content>
  </a-layout>
</template>

<script setup lang="ts">

import {ref} from "vue";

const preview = ref(false)
const cardImage = ref('https://img.moxhub.cn/blog/astronaut-in-space.png')
const imageList = ref([
  {
      imageUrl: 'https://img.moxhub.cn/blog/show1.png',
      width: 384,
      height: 384,
  },
  {
    imageUrl: 'https://img.moxhub.cn/blog/show2.png',
    width: 384,
    height: 384,
  },
  {
    imageUrl: 'https://img.moxhub.cn/blog/show3.png',
    width: 384,
    height: 384,
  },
])
</script>

<style scoped>
.layout-content {
  text-align: center;
  min-width: 420px;
  height: 1200px;
}

.layout ::-webkit-scrollbar {
  width: 0 !important;
  height: 0;
}

.start-button {
  font-size: 24px;
  font-weight: 600;
  font-family: 幼圆;
  width: 300px;
  height: 60px;
  border-radius: 20px;
}
.start-button:hover {
  font-style: oblique;
  background-image: linear-gradient(90deg, #dec36d, #62ffb8 50%, #62f2ff 98%);
  -webkit-box-shadow: 10px 10px 99px 6px #62f2ff;
  -moz-box-shadow: 10px 10px 99px 6px rgba(76,201,240,1);
  box-shadow: 10px 10px 99px 6px #62f2ff;
}
.card {
  display: flex;
  width: 1220px;
  margin-bottom: 200px;
  border-radius: 10px;
}
.card-image {
  flex: 1;
}
.card-typography {
  margin: 10px;
  flex: 1;
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
  font-size: 81px;
  font-weight: 600;
}
.subtitle-text {
  background-image: linear-gradient(90deg, #dec36d, #62ffb8 50%, #62f2ff 98%);
  -webkit-background-clip: text;
  color: transparent;
  background-clip: text;
  background-size: 300% 100%;
  font-size: 64px;
  font-weight: 600;
}

.image-border {
  border-radius: 10px;
}

</style>