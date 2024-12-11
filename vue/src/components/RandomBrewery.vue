<template>
    <div class="random-brewery" v-if="brewery">
        <h2>Featured Brewery</h2>
        <div class="logo" v-if="brewery.img">
            <img :src="brewery.img" alt="" class="image">
        </div>
        <h3>{{ brewery.breweryName }}</h3>
        <p>{{ brewery.address }}, {{ brewery.city }}, {{ brewery.state }} {{ brewery.zipcode }}</p>
        <button @click="highlightRandomBrewery">Highlight Another Brewery</button>
    </div>
</template>
  

<script>
import BreweryService from '../services/BreweryService';

export default {
    data() {
        return {
            breweries: [],
            brewery: {}
        };
    },
    methods: {
        async fetchBreweries() {
            try {
                const response = await BreweryService.getBreweries();
                this.breweries = response.data;
                this.highlightRandomBrewery();
            } catch (error) {
                console.error('Error fetching breweries:', error);
            }
        },
        highlightRandomBrewery() {
            if (this.breweries.length > 0) {
                const randomIndex = Math.floor(Math.random() * this.breweries.length);
                this.brewery = this.breweries[randomIndex];
            }
        },
        getBreweryImage() {
            if (this.brewery && this.brewery.img) {
                return this.brewery.img;
            }
        }
    },
    created() {
        this.fetchBreweries();
    }
}
</script>
<style scoped></style>