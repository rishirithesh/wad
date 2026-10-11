import { defineStore } from 'pinia'
import { ref } from 'vue'

const API = 'http://localhost:8080/api'

export const useShoppingStore = defineStore('shopping', () => {
  // Global State
  const message = ref('')
  const loggedIn = ref('')
  const products = ref([])
  const cart = ref([])
  const orders = ref([])

  // Helper function for API requests
  function send(url, method, body) {
    return fetch(API + url, {
      method: method,
      headers: { 'Content-Type': 'application/json' },
      body: body ? JSON.stringify(body) : undefined
    })
  }

  // Load Actions
  async function loadProducts() {
    products.value = await (await fetch(API + '/products')).json()
  }

  async function loadCart() {
    cart.value = await (await fetch(API + '/cart')).json()
  }

  async function loadOrders() {
    orders.value = await (await fetch(API + '/orders')).json()
  }

  // Auth Actions
  async function register(user) {
    const res = await send('/auth/register', 'POST', user)
    message.value = await res.text()
  }

  async function login(user) {
    const res = await send('/auth/login', 'POST', user)
    message.value = await res.text()
    loggedIn.value = res.ok ? user.username : ''
  }

  // Product Actions
  async function saveProduct({ product, editId }) {
    if (editId) {
      await send('/products/' + editId, 'PUT', product)
    } else {
      await send('/products', 'POST', product)
    }
    await loadProducts()
  }

  async function deleteProduct(id) {
    await send('/products/' + id, 'DELETE')
    await loadProducts()
  }

  // Cart Actions
  async function addToCart(item) {
    await send('/cart', 'POST', item)
    await loadCart()
  }

  async function addProductToCart(p) {
    await send('/cart', 'POST', { productName: p.name, quantity: 1 })
    await loadCart()
  }

  async function removeFromCart(id) {
    await send('/cart/' + id, 'DELETE')
    await loadCart()
  }

  async function clearCart() {
    await send('/cart', 'DELETE')
    await loadCart()
  }

  // Order Actions
  async function placeOrder() {
    for (const item of cart.value) {
      await send('/orders', 'POST', { productName: item.productName, quantity: item.quantity })
    }
    await clearCart()
    await loadOrders()
  }

  async function deleteOrder(id) {
    await send('/orders/' + id, 'DELETE')
    await loadOrders()
  }

  return {
    message,
    loggedIn,
    products,
    cart,
    orders,
    loadProducts,
    loadCart,
    loadOrders,
    register,
    login,
    saveProduct,
    deleteProduct,
    addToCart,
    addProductToCart,
    removeFromCart,
    clearCart,
    placeOrder,
    deleteOrder
  }
})
