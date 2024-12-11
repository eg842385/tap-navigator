<template>
    <body>
        <div class="container">
            <div class="details">
                <beer-details :id="$route.params.id" :beerId="$route.params.beerId"></beer-details>
            </div>
            <div class="buttons">
                <button class="button" @click.prevent="isShowForm = !isShowForm">
                    {{ isShowForm ? 'Hide Update Form' : 'Update Beer' }}</button>
                <update-beer :id="$route.params.id" :beerId="$route.params.beerId" v-if="isShowForm"></update-beer>
                <delete-beer :id="$route.params.id" :beerId="$route.params.beerId"></delete-beer>
            </div>
            <div class="reviews">
                <review-list class="list-reviews" :id="$route.params.id" :beerId="$route.params.beerId"></review-list>
                <add-review class="add-review" :id="$route.params.id" :beerId="$route.params.beerId"></add-review>
            </div>
        </div>
    </body>
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
<style scoped>
html,
body {
    height: 100%;
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}
body {
    min-height: 100vh;
    background-color: peachpuff;
}
.details{
    margin-bottom: 30px;
}
.buttons {
    display: flex;
    justify-content: space-evenly;
    margin-bottom: 20px;
    cursor: pointer;
}
.button {
    border-radius: 10px;
    height: 50px;
    width: 150px;
    font-size: 17px;
    cursor: pointer;
}
button:hover {
    background-color: rgba(228, 186, 61, 0.753);
}
.reviews{
    display: flex;
    justify-content: space-evenly;
    align-content: center;
}
.list-reviews {
width: 50%;
}
.add-reviews{
    width:50%;
}
 
</style>