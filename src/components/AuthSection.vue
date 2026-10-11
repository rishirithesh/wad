<script setup>
import { ref } from 'vue'
import { useShoppingStore } from '../stores/shoppingStore'

const store = useShoppingStore()
const user = ref({ username: '', password: '' })

function onRegister() {
  store.register({ ...user.value })
}

function onLogin() {
  store.login({ ...user.value })
}
</script>

<template>
  <section class="auth-section">
    <h2>1. Register / Login</h2>
    <p v-if="store.loggedIn" class="logged-in-msg">Logged in as: <strong>{{ store.loggedIn }}</strong></p>
    <div class="form-row">
      <input v-model="user.username" placeholder="Username" />
      <input v-model="user.password" type="password" placeholder="Password" />
      <button @click="onRegister">Register</button>
      <button @click="onLogin">Login</button>
    </div>
  </section>
</template>
