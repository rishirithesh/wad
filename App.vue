<template>
  <div>
    <h1>Room and Booking</h1>

    <form @submit.prevent="saveRoom">
      <input v-model="form.roomId" placeholder="id" required>
      <input v-model.number="form.roomNumber" type="number" placeholder="number" required>
      <input v-model="form.roomType" placeholder="type" required>
      <input v-model.number="form.capacity" type="number" placeholder="cap" required>
      <select v-model="form.availability" required>
        <option :value="true">Available (true)</option>
        <option :value="false">Occupied (false)</option>
      </select>

      <button type="submit">
        {{ editingId !== null ? "Update" : "Add" }}
      </button>
      <button type="button" @click="resetForm">Clear</button>
    </form>

    <button type="button" @click="loadRooms">Show All</button>

    <h2>Room List</h2>

    <div v-for="r in rooms" :key="r.id || r.roomId">
      {{ r.roomId }} - {{ r.roomNumber }} - {{ r.roomType }} - Cap: {{ r.capacity }} - Status: {{ r.availability ? 'Available' : 'Occupied' }}
      <button @click="editRoom(r)">Edit</button>
      <button @click="deleteRoom(r.id || r.roomId)">Delete</button>
      <hr>
    </div>

    <p>{{ message }}</p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      rooms: [],
      form: { roomId: "", roomNumber: "", roomType: "", capacity: "", availability: true },
      editingId: null,
      message: ""
    };
  },

  methods: {
    async loadRooms() {
      try {
        let res = await fetch("/room");
        this.rooms = await res.json();
      } catch (err) {
        this.message = "Error loading rooms: " + err.message;
      }
    },

    async saveRoom() {
      try {
        let url = "/room";
        let method = "POST";

        if (this.editingId !== null) {
          url += "/" + this.editingId;
          method = "PUT";
        }

        let res = await fetch(url, {
          method: method,
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({
            roomId: this.form.roomId,
            roomNumber: Number(this.form.roomNumber),
            roomType: this.form.roomType,
            capacity: Number(this.form.capacity),
            availability: Boolean(this.form.availability)
          })
        });

        if (!res.ok) {
          throw new Error("Server error " + res.status);
        }

        this.message = this.editingId !== null ? "Updated successfully" : "Saved successfully";
        this.resetForm();
        await this.loadRooms();
      } catch (err) {
        this.message = "Error saving room: " + err.message;
      }
    },

    editRoom(r) {
      this.form = {
        roomId: r.roomId,
        roomNumber: r.roomNumber,
        roomType: r.roomType,
        capacity: r.capacity,
        availability: r.availability
      };
      this.editingId = r.id || r.roomId;
    },

    async deleteRoom(id) {
      try {
        let res = await fetch("/room/" + id, {
          method: "DELETE"
        });
        if (!res.ok) {
          throw new Error("Server error " + res.status);
        }
        this.message = "Deleted successfully";
        await this.loadRooms();
      } catch (err) {
        this.message = "Error deleting room: " + err.message;
      }
    },

    resetForm() {
      this.form = { roomId: "", roomNumber: "", roomType: "", capacity: "", availability: true };
      this.editingId = null;
    }
  },

  mounted() {
    this.loadRooms();
  }
};
</script>

<style>
body {
  font-family: Arial, sans-serif;
  background: #f0f4f8;
  margin: 30px;
}

input, select, button {
  padding: 10px;
  margin: 5px;
}

button {
  background: blue;
  color: white;
  border: none;
  cursor: pointer;
}

button:hover{
  background-color: green;
  cursor: pointer;
}

hr {
  border: 1px solid #ddd;
}
</style>
