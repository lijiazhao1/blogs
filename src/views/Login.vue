<template>
  <div class="container">
    <div class="card">
      <h1 class="text-2xl font-bold mb-6 text-center">用户登录</h1>
      <form @submit.prevent="handleLogin">
        <div class="form-group">
          <label class="form-label" for="username">用户名</label>
          <input 
            type="text" 
            id="username" 
            v-model="username" 
            class="form-control" 
            required
          >
        </div>

        <div class="form-group">
          <label class="form-label" for="password">密码</label>
          <input 
            type="password" 
            id="password" 
            v-model="password" 
            class="form-control" 
            required
          >
          <div class="error-message" v-if="error">{{ error }}</div>
        </div>

        <div class="flex justify-between items-center">
          <button type="submit" class="btn btn-primary">登录</button>
          <router-link to="/register" class="text-blue-500 hover:underline">没有账号？注册</router-link>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { login, auth } from '../api';

const username = ref('');
const password = ref('');
const error = ref('');
const router = useRouter();

const handleLogin = async () => {
  try {
    const result = await login(username.value, password.value);
    auth.setUser(result.data);
    router.push('/user-info');
  } catch (err) {
    error.value = err.message || '登录失败';
  }
};
</script>