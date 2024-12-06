<template>
    <div>{{beer}}</div>
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
            // tableHeaders: ['Brewery Name ', 'Description ', 'Location']
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

<style></style>