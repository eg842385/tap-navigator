<template>
    <div> 
        <table class="beerDetails">
            <thead>
                <tr>
                    <th v-for="(header, index) in tableHeaders" :key="index">
                        {{ header }}
                    </th>
                </tr>
            </thead>
            <tbody>
                <tr class="details">
                    <td>{{ beer.beerName }}</td>
                    <td>{{beer.beerType}}</td>
                    <td>{{ beer.description }}</td>
                    <td>{{beer.abv}}</td>
                    <td>{{beer.img}}</td>
                </tr>
            </tbody>
        </table>
    </div>
    <div>
        <!-- <button @click.prevent="goToUpdateBeer" >Update Beer</button> -->
    </div>
</template>

<script>
import BeerService from '../services/BeerService';

export default {
    props: {
        id: {
            type: String,
            required: true
        },
        beerId: {
            type: String,
            required: true
        }
    },
    data() {
        return {
            beer: {},
            tableHeaders: ['Name ', 'Type ', 'Description', 'ABV', 'PIC']
        }
    },
    async created() {
        this.getBeerDetails(this.id, this.beerId)
    },


    methods: {
        getBeerDetails(id, beerId) {
            BeerService.getBeerDetailsByBeerId(id, beerId)
                .then(response => {
                    this.beer = response.data;
                })
                .catch(error => {
                    console.error('Error fetching beer details:', error);
                });
        }
    }
}
</script>

<style scoped></style>