<template>
    <beer-details :id="$route.params.id" :beerId="$route.params.beerId"></beer-details>
    <button @click.prevent="isShowForm = !isShowForm">
        {{ isShowForm ? 'Hide Update Form' : 'Update Beer' }}</button>
    <update-beer :id="$route.params.id" :beerId="$route.params.beerId" v-if="isShowForm"></update-beer>
    <delete-beer :id="$route.params.id" :beerId="$route.params.beerId"></delete-beer>
    <add-review :id="$route.params.id" :beerId="$route.params.beerId"></add-review>
    <review-list :id="$route.params.id" :beerId="$route.params.beerId"></review-list>

</template>

<script>
import BeerDetails from '../components/BeerDetails.vue';
import UpdateBeer from '../components/UpdateBeer.vue';
import DeleteBeer from '../components/DeleteBeer.vue';
import AddReview from '../components/AddReview.vue';
import ReviewList from '../components/ReviewList.vue';
export default {
    components: {
        BeerDetails,
        UpdateBeer,
        DeleteBeer,
        AddReview,
        ReviewList
    },
    data() {
        return {
            isShowForm: false
        }
    }
}
</script>
<style></style>