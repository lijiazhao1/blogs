<template>
  <div class="container">
    <div class="flex justify-between items-center my-4">
      <h1 class="text-2xl font-bold">个人中心</h1>
      <button @click="auth.logout()" class="btn btn-secondary">退出登录</button>
    </div>

    <div class="card">
      <h2 class="text-xl font-semibold mb-4">基本信息</h2>
      <form @submit.prevent="handleUpdateInfo">
        <input type="hidden" :value="user.id" id="userId">
        
        <div class="form-group">
          <label class="form-label" for="username">用户名</label>
          <input 
            type="text" 
            id="username" 
            v-model="user.username" 
            class="form-control" 
            disabled
          >
        </div>

        <div class="form-group">
          <label class="form-label" for="nickname">昵称</label>
          <input 
            type="text" 
            id="nickname" 
            v-model="user.nickname" 
            class="form-control" 
            required
          >
        </div>

        <div class="form-group">
          <label class="form-label" for="email">邮箱</label>
          <input 
            type="email" 
            id="email" 
            v-model="user.email" 
            class="form-control" 
            required
          >
        </div>

        <div class="form-group">
          <label class="form-label" for="avatar">头像URL</label>
          <input 
            type="text" 
            id="avatar" 
            v-model="user.avatar" 
            class="form-control"
          >
        </div>

        <div class="form-group">
          <label class="form-label" for="intro">个人简介</label>
          <textarea 
            id="intro" 
            v-model="user.intro" 
            class="form-control" 
            rows="3"
          ></textarea>
        </div>

        <button type="submit" class="btn btn-primary">更新信息</button>
        <div class="success-message" v-if="infoSuccess">{{ infoSuccess }}</div>
      </form>
    </div>

    <div class="card">
      <h2 class="text-xl font-semibold mb-4">修改密码</h2>
      <form @submit.prevent="handleResetPassword">
        <div class="form-group">
          <label class="form-label" for="oldPassword">旧密码</label>
          <input 
            type="password" 
            id="oldPassword" 
            v-model="oldPassword" 
            class="form-control" 
            required
          >
        </div>

        <div class="form-group">
          <label class="form-label" for="newPassword">新密码</label>
          <input 
            type="password" 
            id="newPassword" 
            v-model="newPassword" 
            class="form-control" 
            required 
            minlength="6"
          >
        </div>

        <button type="submit" class="btn btn-primary">重置密码</button>
        <div class="success-message" v-if="passwordSuccess">{{ passwordSuccess }}</div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { getUserInfo, updateUser, resetPassword, auth } from '../api';

const user = ref({});
const oldPassword = ref('');
const newPassword = ref('');
const infoSuccess = ref('');
const passwordSuccess = ref('');
const router = useRouter();

const loadUserInfo = async () => {
  try {
    const result = await getUserInfo(auth.getUser().id);
    user.value = result.data;
  } catch (err) {
    console.error('加载用户信息失败', err);
  }
};

const handleUpdateInfo = async () => {
  try {
    await updateUser(user.value);
    infoSuccess.value = '信息更新成功';
  } catch (err) {
    console.error('更新失败', err);
  }
};

const handleResetPassword = async () => {
  try {
    await resetPassword(user.value.id, oldPassword.value, newPassword.value);
    passwordSuccess.value = '密码重置成功';
    oldPassword.value = '';
    newPassword.value = '';
  } catch (err) {
    console.error('密码重置失败', err);
  }
};

onMounted(() => {
  if (!auth.isAuthenticated()) {
    router.push('/login');
  } else {
    loadUserInfo();
  }
});
</script>