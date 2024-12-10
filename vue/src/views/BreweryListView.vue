<template>
    <brewery-list></brewery-list>
</template>

<script>
import BreweryList from '../components/BreweryList.vue';


export default {
    components: {
        BreweryList
    }
}
</script>

<style>
body {
    background-attachment: fixed;
    
    background-image: url('@/assets/verticalbeer.jpg');
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
}

</style>