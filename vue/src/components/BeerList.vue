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
                <button @click.prevent="goToAddBeerForm" v-if="this.showIf">Add a Beer</button>
            </div>
        </div>
    </body>
</template>

<script>
import BeerService from '../services/BeerService';
import BreweryService from '../services/BreweryService';

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
            brewery: {},
            tableHeaders: ['Name ', 'Type'],
            showIf: false
        }
    },
    async created() {
        this.getBreweryDetails(this.id);
        this.getBeerDetails(this.id);
    },
    computed: {
        currentUserId() {
            return this.$store.state.user.id;
        },
        isAdmin() {
            return this.$store.state.user.authorities[0].name === 'ROLE_ADMIN';
        },
        isCorrectBrewer() {
            return this.$store.state.user.authorities[0].name === 'ROLE_BREWER' && (this.currentUserId == this.brewery.userId);
        }
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
        },
        getBreweryDetails(id) {
            BreweryService.getBreweryById(id)
                .then(response => {
                    this.brewery = response.data;
                    this.ifCurrentBrewer();
                })
                .catch(error => {
                    console.error('Error fetching brewery details:', error);
                });
        },
        ifCurrentBrewer(){
            if(this.isAdmin || this.isCorrectBrewer){
                this.showIf = true;
            }
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
    color:white;
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