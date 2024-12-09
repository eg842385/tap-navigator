<template>
    <div class="container">
        <section class="brewery-details">
            <brewery-details :id="$route.params.id"></brewery-details>
        </section>
        <section class="beer-list">
            <beer-list :id="$route.params.id"></beer-list>
        </section>
    </div>
</template>

<script>
import BreweryDetails from '../components/BreweryDetails.vue';
import BeerList from '../components/BeerList.vue';
export default {
    components: {
        BeerList,
        BreweryDetails
    },
}
</script>

<style>

</style>