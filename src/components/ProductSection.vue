<script setup>
import { ref } from 'vue'
import { useShoppingStore } from '../stores/shoppingStore'

const store = useShoppingStore()

const product = ref({ name: '', price: 0, quantity: 0 })
const editId = ref('')

function saveProduct() {
  store.saveProduct({
    product: { ...product.value },
    editId: editId.value
  })
  product.value = { name: '', price: 0, quantity: 0 }
  editId.value = ''
}

function editProduct(p) {
  product.value = { name: p.name, price: p.price, quantity: p.quantity }
  editId.value = p.id
}
</script>

<template>
  <section class="products-section">
    <h2>2. Products</h2>
    <div class="form-row">
      <input v-model="product.name" placeholder="Name" />
      <input v-model.number="product.price" type="number" placeholder="Price" />
      <input v-model.number="product.quantity" type="number" placeholder="Quantity" />
      <button @click="saveProduct">{{ editId ? 'Update' : 'Add' }} Product</button>
    </div>

    <ul>
      <li v-for="p in store.products" :key="p.id">
        {{ p.name }} - Rs.{{ p.price }} - Qty: {{ p.quantity }}
        <button @click="store.addProductToCart(p)">Add to Cart</button>
        <button @click="editProduct(p)">Edit</button>
        <button @click="store.deleteProduct(p.id)">Delete</button>
      </li>
    </ul>
  </section>
</template>
