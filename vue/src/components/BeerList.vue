<template>
    <body>
        <div>
            <div class="titleTap">
                <h2>What's Currently On Tap</h2>
            </div>
            
            <table class="beerlist">
                <thead>
                    <tr>
                        <th v-for="(header, index) in tableHeaders" :key="index">
                            {{ header }}
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="beerDetails" v-for="beer in beers" :key="beer.beerId">
                        <td>
                            <router-link :to="{ name: 'beerDetails', params: { id: this.$route.params.id, beerId: beer.beerId } }">
                                {{ beer.beerName }}</router-link>
                        </td>
                        <td>{{ beer.beerType }}</td>
                    </tr>
                </tbody>
            </table>
            <div class="btn">
                <button @click.prevent="goToAddBeerForm">Add a Beer</button>
            </div>
        </div>
    </body>
</template>

<script>
import BeerService from '../services/BeerService';

export default {
    props: {
        id: {
            type: String,
            required: true
        }
    },
    data() {
        return {
            beers: [],
            tableHeaders: ['Name ', 'Type']
        }
    },
    async created() {
        this.getBeerDetails(this.id);
    },

    methods: {
        getBeerDetails(id) {
            BeerService.getBeersByBreweryId(id)
                .then(response => {
                    this.beers = response.data;
                })
                .catch(error => {
                    console.error('Error fetching beer details:', error);
                });
        },

        goToAddBeerForm(){
            this.$router.push({name: 'addBeer', params: {id: this.id}});
        }
    }
}
</script>

<style scoped>
html,
body {
    height: 100%;
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

body {
    height: 100vh;
}

.beerlist th {
    font-size: 30px;
    background-color: rgba(228, 186, 61, 0.753);
 
    padding: 1rem;
}
.titleTap {
    font-size: 30px;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 10px;
}
button {
    border-radius: 10px;
    height: 50px;
    width: 170px;
    font-size: 17px;
}
.btn{
    display: flex;
    justify-content: flex-end;
}
button:hover {
    background-color:  rgba(228, 186, 61, 0.753);
}
.beerlist {
    width: 100%;
    border-collapse: collapse;
    max-width: 40%;
    background-color: rgba(255, 255, 255, 0.9);
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    margin: 0 auto;
}

.beerlist tr:nth-child(even) {
    background-color: #f8f8f8;
}

td {
    font-size:25px;
    padding: 20px;
}
</style>