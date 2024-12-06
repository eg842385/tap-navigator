<template>
    <beer-details :id="$route.params.id" :beerId="$route.params.beerId"></beer-details>
    <button @click.prevent="isShowForm = !isShowForm">
        {{ isShowForm ? 'Hide Update Form' : 'Update Beer' }}</button>
    <update-beer :id="$route.params.id" :beerId="$route.params.beerId" v-if="isShowForm"></update-beer>
    <delete-beer :id="$route.params.id" :beerId="$route.params.beerId"></delete-beer>
</template>

<script>
import BeerDetails from '../components/BeerDetails.vue';
import UpdateBeer from '../components/UpdateBeer.vue';
import DeleteBeer from '../components/DeleteBeer.vue';
export default {
    components: {
        BeerDetails,
        UpdateBeer,
        DeleteBeer
    },
    data() {
        return {
            isShowForm: false
        }
    }
}
</script>
<style></style>