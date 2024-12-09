<template>
    <form @submit.prevent="submitForm" class="addReview">
        <h2>Write a Review</h2>
        <div>
            <label for="rating">Rating: </label>
            <select name="" id="" v-model.number="newReview.rating" required>Rating:
                <option value="" disabled selected>--</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select>
        </div>
        <div>
            <label for="review">Review: </label>
            <textarea name="review" id="review" cols="30" rows="10" v-model="newReview.review" required></textarea>
        </div>
        <button class="btn btn-submit">Submit</button>
        <button class="btn btn-cancel" v-on:click="cancelForm" type="button">Cancel</button>
    </form>
</template>

<script>
import ReviewService from '../services/ReviewService';
import BreweryService from '../services/BreweryService';

export default {
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
    data() {
        return {
            newReview: {
                beerId: this.$route.params.beerId,
                rating: '',
                review: '',
                userId: this.$store.state.user.id,

            },
            brewery: {}
        };
    },
    created() {
        this.getUserIdFromBrewery();
    },
    methods: {
        submitForm() {
            if (this.isCorrectBrewer) {
                alert("You cannot add a review to your own brewery.");
                return;
            };
            ReviewService.addReview(this.$route.params.id, this.$route.params.beerId, this.newReview)
                .then(response => {
                    console.log('Response: ', response);
                    if (response.status === 201 || response.status === 200) {
                        alert('New Review Added Successfully!');
                        // const newBreweryId = response.data.breweryId;
                        this.cancelForm();
                        this.$router.go(0);

                    } else {
                        console.log('Review unable to be created.');
                    }
                })
                .catch(error => {
                    console.error('Failed to create review:', error);
                });

        },

        cancelForm() {
            this.newReview = {
                beerId: this.$route.params.beerId,
                rating: '',
                review: '',
                userId: this.$store.state.user.id,
            };
        },

        getUserIdFromBrewery() {
            BreweryService.getBreweryById(this.$route.params.id)
                .then(response => {
                    this.brewery = response.data;
                })
                .catch(error => {
                    console.error('Error fetching brewery details:', error);
                });
        },
    }
}
</script>

<style></style>