<template>
    <div>
        <h2>What's Currently On Tap</h2>
        <div>
            <button @click.prevent="goToAddBeerForm">Add a Beer</button>
        </div>
        <table>
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
    </div>
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
</style>