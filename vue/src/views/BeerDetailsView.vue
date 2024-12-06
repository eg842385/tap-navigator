<template>
    <beer-details :id="$route.params.id" :beerId="$route.params.beerId"></beer-details>
    <button @click.prevent="isShowForm = !isShowForm">
        {{isShowForm ? 'Hide Update Form' : 'Update this Beer'}}</button>
    <update-beer :id="$route.params.id" :beerId="$route.params.beerId" v-if="isShowForm"></update-beer>
</template>

<script>
import BeerDetails from '../components/BeerDetails.vue';
import UpdateBeer from '../components/UpdateBeer.vue';
export default {
    components:{
        BeerDetails,
        UpdateBeer
    },
    data() {
        return {
            isShowForm: false
        }
    }
}
</script>
<style></style>