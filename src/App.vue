<script setup>
import { onMounted } from 'vue'
import { useShoppingStore } from './stores/shoppingStore'
import AuthSection from './components/AuthSection.vue'
import ProductSection from './components/ProductSection.vue'
import CartSection from './components/CartSection.vue'
import OrderSection from './components/OrderSection.vue'

const store = useShoppingStore()

onMounted(() => {
  store.loadProducts()
  store.loadCart()
  store.loadOrders()
})
</script>

<template>
  <main class="app-container">
    <h1>Shopping Cart</h1>
    <p v-if="store.message" class="system-message"><b>{{ store.message }}</b></p>

    <!-- Pinia-powered Modular Components (no props, no emits needed) -->
    <AuthSection />
    <ProductSection />
    <CartSection />
    <OrderSection />
  </main>
</template>
