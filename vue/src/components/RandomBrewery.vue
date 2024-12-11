<template>
    <body>
        <div class="random-brewery" v-if="brewery">
            <h2>Featured Breweries</h2>
            <div class="logo">
                <img :src="brewery.img || defaultImg" alt="" class="image">
            </div>
            <h3>{{ brewery.breweryName }}</h3>
            <p>{{ brewery.address }}, {{ brewery.city }}, {{ brewery.state }} {{ brewery.zipcode }}</p>
            <button @click="highlightRandomBrewery">Highlight Another Brewery</button>
        </div>
    </body>
</template>
  

<script>
import BreweryService from '../services/BreweryService';

export default {
    data() {
        return {
            breweries: [],
            brewery: {},
            defaultImg: ('@/assets/pouring.jpg')
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
    border-collapse: collapse;
    background-color: #FFFFE0;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    color: rgb(13, 40, 18);
    
}

</style>