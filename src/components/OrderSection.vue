<script setup>
import { useShoppingStore } from '../stores/shoppingStore'

const store = useShoppingStore()
</script>

<template>
  <section class="orders-section">
    <h2>4. Orders</h2>
    <ul>
      <li v-for="o in store.orders" :key="o.id">
        {{ o.productName }} - Qty: {{ o.quantity }} - {{ o.status }}
        <button @click="store.deleteOrder(o.id)">Delete</button>
      </li>
    </ul>
  </section>
</template>
