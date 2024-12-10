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

<style scoped>
.container {
    background-attachment: fixed;
    background-image: url('@/assets/verticalbeer.jpg'); 
    background-size: cover; 
    background-position: center; 
    background-repeat: no-repeat; 
    min-height: 100vh; 
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: space-between;
    padding: 20px;
    gap: 20px; 
}
.brewery-details, .beer-list {
    width: 100%;
    max-width: 1200px;
    padding: 20px;
    border-radius: 10px;
    
}
</style>