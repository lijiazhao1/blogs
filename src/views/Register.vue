<template>
  <div class="container">
    <div class="card">
      <h1 class="text-2xl font-bold mb-6 text-center">用户注册</h1>
      <form @submit.prevent="handleRegister">
        <div class="form-group">
          <label class="form-label" for="username">用户名</label>
          <input 
            type="text" 
            id="username" 
            v-model="username" 
            class="form-control" 
            required
          >
          <div class="error-message" v-if="error">{{ error }}</div>
        </div>

        <div class="form-group">
          <label class="form-label" for="password">密码</label>
          <input 
            type="password" 
            id="password" 
            v-model="password" 
            class="form-control" 
            required 
            minlength="6"
          >
        </div>

        <div class="form-group">
          <label class="form-label" for="nickname">昵称</label>
          <input 
            type="text" 
            id="nickname" 
            v-model="nickname" 
            class="form-control" 
            required
          >
        </div>

        <div class="form-group">
          <label class="form-label" for="email">邮箱</label>
          <input 
            type="email" 
            id="email" 
            v-model="email" 
            class="form-control" 
            required
          >
        </div>

        <div class="flex justify-between items-center">
          <button type="submit" class="btn btn-primary">注册</button>
          <router-link to="/login" class="text-blue-500 hover:underline">已有账号？登录</router-link>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { register } from '../api';

const username = ref('');
const password = ref('');
const nickname = ref('');
const email = ref('');
const error = ref('');
const router = useRouter();

const handleRegister = async () => {
  try {
    await register({ username: username.value, password: password.value, nickname: nickname.value, email: email.value });
    router.push('/login');
  } catch (err) {
    error.value = err.message || '注册失败';
  }
};
</script>