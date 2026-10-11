<script setup>
import { ref } from 'vue'
import { useShoppingStore } from '../stores/shoppingStore'

const store = useShoppingStore()
const cartForm = ref({ productName: '', quantity: 1 })

function submitAddToCart() {
  store.addToCart({ ...cartForm.value })
  cartForm.value = { productName: '', quantity: 1 }
}
</script>

<template>
  <section class="cart-section">
    <h2>3. Cart</h2>
    <div class="form-row">
      <input v-model="cartForm.productName" placeholder="Product name" />
      <input v-model.number="cartForm.quantity" type="number" placeholder="Quantity" />
      <button @click="submitAddToCart">Add Item</button>
    </div>

    <ul>
      <li v-for="c in store.cart" :key="c.id">
        {{ c.productName }} - Qty: {{ c.quantity }}
        <button @click="store.removeFromCart(c.id)">Remove</button>
      </li>
    </ul>

    <div class="cart-actions">
      <button @click="store.clearCart">Clear Cart</button>
      <button @click="store.placeOrder">Place Order</button>
    </div>
  </section>
</template>
