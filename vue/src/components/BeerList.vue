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
        }
    },
    data() {
        return {
            beer: {},
        }
    },
    async created() {
        this.getBeerDetails(this.id);
    },

    methods: {
        getBeerDetails(id){
            BeerService.getBeersByBreweryId(id)
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