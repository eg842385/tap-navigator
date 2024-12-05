<template>


  <form>
    <h2>Update Brewery</h2>

    <div>
      <label for="name"> Brewery Name: </label>
      <input id="name" type="text" v-model="brewery.breweryName" />
    </div>
    <div>
      <label for="brewerId">Brewer ID: </label>
      <input id="brewerId" type="text"  v-model="brewery.userId"/>
    </div>
    <div>
      <label for="description">Description: </label>
      <input id="description" type="text"  v-model="brewery.description" />
    </div>
    <div>
      <label for="address"> Address: </label>
      <input id="address" type="text"  v-model="brewery.address" />
    </div>
    <div>
      <label for="city"> City: </label>
      <input id="city" type="text"  v-model="brewery.city" />
    </div>
    <div>
      <label for="state"> State: </label>
      <input id="state" type="text"  v-model="brewery.state"/>
    </div>
    <div>
      <label for="zipcode"> Zipcode: </label>
      <input id="zipcode" type="text"  v-model="brewery.zipcode"/>
    </div>
    <button class="btn btn-submit">Submit</button>
    <button class="btn btn-cancel" v-on:click="cancelForm" type="button">
      Cancel
    </button>
  </form>
</template>




<script>
import BreweryService from "../services/BreweryService";

export default {
  data() {
    return {
      brewery: {
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

  created() {
    this.getBreweryData();
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
  },
};
</script>




<style scoped>
</style>