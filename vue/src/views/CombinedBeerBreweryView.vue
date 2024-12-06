<template>
    <brewery-details :id="$route.params.id" ></brewery-details>
    <beer-list :id="$route.params.id"></beer-list>
</template>

<script>
import BreweryDetails from '../components/BreweryDetails.vue';
import BeerList from '../components/BeerList.vue';
export default {
    components:{
        BeerList,
        BreweryDetails
    },
    
}
</script>

<style>
</style>