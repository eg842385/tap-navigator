<template>
    <div>{{beers}}</div>
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