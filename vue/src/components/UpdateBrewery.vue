<template>
  <div class="container">
    <form @submit.prevent="updateBrewery" class="update-brewery">
      <h2>Update Brewery</h2>
      <div>
        <label for="name"> Brewery Name: </label>
        <input id="name" type="text" v-model="brewery.breweryName" />
      </div>
      <div>
        <label for="brewerId">Brewer ID: </label>
        <input id="brewerId" type="text" v-model="brewery.userId" />
      </div>
      <div>
        <label for="description">Description: </label>
        <textarea
          v-model="brewery.description"
          placeholder="Enter your description here"
          required
        ></textarea>
      </div>
      <div>
        <label for="address"> Address: </label>
        <input id="address" type="text" v-model="brewery.address" />
      </div>
      <div>
        <label for="city"> City: </label>
        <input id="city" type="text" v-model="brewery.city" />
      </div>
      <div>
        <label for="state"> State: </label>
        <input id="state" type="text" v-model="brewery.state" />
      </div>
      <div>
        <label for="zipcode"> Zipcode: </label>
        <input id="zipcode" type="text" v-model="brewery.zipcode" />
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
import BreweryService from "../services/BreweryService";

export default {
  data() {
    return {
      brewery: {
        // id: "",
        breweryName: "",
        userId: "",
        description: "",
        address: "",
        city: "",
        state: "",
        zipcode: "",
        img: ""
      },
    };
  },

  created() {
    this.getBreweryData();
  },

  computed: {
    currentUserId() {
      return this.$store.state.user.id;
    },
    isAdmin() {
      return this.$store.state.user.authorities[0].name == "ROLE_ADMIN";
    },
    isCorrectBrewer() {
      return (
        this.$store.state.user.authorities[0].name == "ROLE_BREWER" &&
        this.currentUserId == this.brewery.userId
      );
    },
  },

  methods: {
    getBreweryData() {
      const breweryId = this.$route.params.id;

      BreweryService.getBreweryById(breweryId)
        .then((response) => {
          this.brewery = response.data;
        })
        .catch((error) => {
          console.error("Error fetching brewery details:", error);
        });
    },

    updateBrewery() {
      if (this.isAdmin || this.isCorrectBrewer) {
        BreweryService.updateBrewery(this.brewery)
          .then(() => {
            this.$router.push({
              name: "combined-view",
              params: { id: this.$route.params.id },
            });
          })
          .catch((error) => {
            console.error("Error updating brewery: ", error);
          });
      } else {
        alert("You are not authorized to update a brewery.");
      }
    },

    cancelForm() {
      this.$router.push({
        name: "combined-view",
        params: { id: this.$route.params.id },
      });
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
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

input,
textarea {
  width: 100%;
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