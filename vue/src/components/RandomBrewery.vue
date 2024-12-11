<template>
    <div class="random-brewery" v-if="brewery">
        <h2>Featured Breweries</h2>
        <div class="logo">
            <img :src="brewery.img" alt="" class="image">
        </div>
        <h3>{{ brewery.breweryName }}</h3>
        <p>{{ brewery.address }}, {{ brewery.city }}, {{ brewery.state }} {{ brewery.zipcode }}</p>
        <button class="button" @click="highlightRandomBrewery">Highlight Another Brewery</button>
    </div>
</template>
  
<script>
import BreweryService from '../services/BreweryService';

export default {
    data() {
        return {
            breweries: [],
            brewery: {},
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
        startAutoHighlight() {
            this.intervalId = setInterval(this.highlightRandomBrewery, 10000);
        },
        stopAutoHighlight() {
            if (this.intervalId) {
                clearInterval(this.intervalId);
                this.intervalId = null;
            }
        },
    },
    created() {
        this.fetchBreweries();
        this.startAutoHighlight();
    },
    beforeDestroy() {
        this.stopAutoHighlight();
    }
};
</script>

<style scoped>
.random-brewery {
    color: rgb(13, 40, 18);
    text-align: center;
}
h2{
    font-size: 30px;
}
img {
    height: 350px;
}
h3{
    font-size: 26px;
}
p{
    font-size: 20px;
}
.button {
    border-radius: 8px;
    height: 50px;
    width: 270px;
    font-size: 17px;
    cursor: pointer;
}
button:hover {
    background-color:  rgba(228, 186, 61, 0.753);
}
</style>