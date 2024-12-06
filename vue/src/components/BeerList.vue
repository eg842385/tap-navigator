<template>
    <div>
        <h2>What's Currently On Tap</h2>
        <table>
            <tbody>
                <tr class="beerDetails" v-for="beer in beers" 
                :key="beer.beerId">
                    <td>{{beer.beerName}}</td>
                    <td>{{beer.beerType}}</td>
                    <td>{{beer.description}}</td>
                    <td>{{beer.abv}}</td>
                    <td>{{beer.img}}</td>
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
        }
    },
    async created() {
        this.getBeerDetails(this.id);
    },

    methods: {
        getBeerDetails(id){
            BeerService.getBeersByBreweryId(id)
            .then(response => {
                this.beers = response.data;
            })
            .catch(error => {
            console.error('Error fetching beer details:', error);
        });
        }
    }
}
</script>

<style scoped></style>