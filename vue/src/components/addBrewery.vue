<template>
  <div class="container">
    <form @submit.prevent="submitForm" class="addBrewery">
      <h2>Add a New Brewery!</h2>
      <div>
        <label for="name">Brewery Name: </label>
        <input id="name" type="text" v-model="newBrewery.breweryName" required />
      </div>
      <div>
        <label for="brewerId">Brewer ID: </label>
        <input id="brewerId" type="number" v-model="newBrewery.userId" required />
      </div>
      <div>
        <label for="address">Address: </label>
        <input id="address" type="text" v-model="newBrewery.address" required />
      </div>
      <div>
        <label for="city">City: </label>
        <input id="city" type="text" v-model="newBrewery.city" required />
      </div>
      <div>
        <label for="state">State: </label>
        <input id="state" type="text" v-model="newBrewery.state" required />
      </div>
      <div>
        <label for="zipcode">Zipcode: </label>
        <input id="zipcode" type="number" v-model="newBrewery.zipcode" required />
      </div>
      <div>
        <label for="description">Description: </label>
        <textarea v-model="newBrewery.description" placeholder="Enter your description here" required></textarea>
      </div>
      <div class="button-container">
        <button class="btn btn-submit">Submit</button>
        <button class="btn btn-cancel" v-on:click="cancelForm" type="button">
          Cancel
        </button>
      </div>
    </form>
  </div>
</template>

<script>
import breweryService from "../services/BreweryService";

export default {
  computed: {
    currentUserId() {
      return this.$store.state.user?.id;
    },
    isAdmin() {
      return this.$store.state.user?.authorities?.[0]?.name == "ROLE_ADMIN";
    },
    isCorrectBrewer() {
      return (
        this.$store.state.user?.authorities?.[0]?.name == "ROLE_BREWER" &&
        this.currentUserId == this.brewery.userId
      );
    },
  },
  data() {
    return {
      newBrewery: {
        breweryId: "",
        breweryName: "",
        userId: "",
        description: "",
        address: "",
        city: "",
        state: "",
        zipcode: "",
      },
    };
  },
  methods: {
    submitForm() {
      if (this.isAdmin) {
        breweryService
          .addBrewery(this.newBrewery)
          .then((response) => {
            console.log("Response: ", response);
            if (response.status === 201 || response.status === 200) {
              alert("New Brewery Added Successfully!");
              const newBreweryId = response.data.breweryId;
              this.cancelForm();
              this.$router.push({
                name: "combined-view",
                params: { id: newBreweryId },
              });
            } else {
              console.log("Brewery unable to be created.");
            }
          })
          .catch((error) => {
            console.error("Failed to create brewery:", error);
          });
      } else {
        alert("You are not allowed to add a brewery.");
        this.$router.push({ name: "listBreweries" });
      }
    },

    cancelForm() {
      this.newBrewery = {
        breweryName: "",
        userId: "",
        description: "",
        address: "",
        city: "",
        state: "",
        zipcode: "",
      };
      this.$router.push({ name: "listBreweries" });
    },
  },
};
</script>

<style scoped>
form {
  background-color: rgba(141, 141, 141, 0.5);
  justify-self: center;
  padding: 10px;
  width: 400px;
}

.container {
  height: 100%;
  background-attachment: fixed;
  background-image: url('@/assets/verticalbeer.jpg');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  padding: 20px;
}

h2 {
  color: white;
  text-shadow: -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000,
    1px 1px 0 #000;
  font-size: 30px;
  justify-self: center;
  padding-bottom: 20px;
}

button {
  border-radius: 10px;
  margin-top: 10px;
  margin-left: 10px;
  height: 40px;
  width: 120px;
  font-size: 15px;
  cursor: pointer;
}

button:hover {
  background-color: rgba(228, 186, 61, 0.753);
}

.label,
label {
  display: block;
  font-size: 20px;
  margin-bottom: 10px;
}
div {
  color: white;
  text-shadow: -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000,
    1px 1px 0 #000;
  font-size: 20px;
}
input,
textarea {
  width: 92%;
  padding: 10px;
  margin-bottom: 15px;
  border-radius: 10px;
  font-size: 16px;
}

textarea {
  resize: vertical;
}

.button-container {
  display: flex;
  justify-content: space-between;
}
</style>
