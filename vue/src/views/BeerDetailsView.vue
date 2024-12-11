<template>
    <body>
        <div class="container">
            <div class="details">
                <beer-details :id="$route.params.id" :beerId="$route.params.beerId"></beer-details>
            </div>
            <div class="buttons" v-if="canSeeForm">
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
import BreweryService from '../services/BreweryService';
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
            isShowForm: false,
            canSeeForm: false,
            brewery: {}
        }
    },
    computed: {
        currentUserId() {
            return this.$store.state.user.id;
        },
        isAdmin() {
            return this.$store.state.user.authorities[0].name == 'ROLE_ADMIN';
        },
        isCorrectBrewer() {
            return this.$store.state.user.authorities[0].name == 'ROLE_BREWER' && (this.currentUserId == this.brewery.userId);
        }
    },
    created() {
        this.getUserIdFromBrewery();
    },
    methods: {
        canUpdateBeer() {
            if (this.isAdmin || this.isCorrectBrewer) {
                this.canSeeForm = true;
            }
        },
        getUserIdFromBrewery() {
            BreweryService.getBreweryById(this.$route.params.id)
                .then(response => {
                    this.brewery = response.data;
                    this.canUpdateBeer();
                })
                .catch(error => {
                    console.error('Error fetching brewery details:', error);
                });
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

.container {
    height: 100%;
    background-attachment: fixed;
    background-image: url('@/assets/verticalbeer.jpg');
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    min-height: 100vh;
}

body {
    min-height: 100vh;
}

.details {
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

.reviews {
    display: flex;
    justify-content: space-evenly;
    align-content: center;
}

.list-reviews {
    width: 50%;
}

.add-reviews {
    width: 50%;
}
</style>