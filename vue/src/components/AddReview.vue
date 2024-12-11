<template>
    <div class="container">
        <form @submit.prevent="submitForm" class="addReview">
            <h2>Write a Review</h2>
            <div class="rating">
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
            <div class="text">
                <label for="review">Review: </label>
                <textarea name="review" id="review" cols="50" rows="10" v-model="newReview.review" required></textarea>
            </div>
            <div class="buttons">
                <button class="btn btn-submit">Submit</button>
                <button class="btn btn-cancel" v-on:click="cancelForm" type="button">Cancel</button>
            </div>
        </form>
    </div>
</template>

<script>
import ReviewService from '../services/ReviewService';
import BreweryService from '../services/BreweryService';

export default {
    computed: {
        currentUserId() {
            return this.$store.state.user?.id || null;
        },
        isAdmin() {
            return this.$store.state.user?.authorities?.[0]?.name == 'ROLE_ADMIN';
        },
        isCorrectBrewer() {
            return this.$store.state.user?.authorities?.[0]?.name == 'ROLE_BREWER' && (this.currentUserId == this.brewery.userId);
        }
    },
    data() {
        return {
            newReview: {
                beerId: this.$route.params.beerId,
                rating: '',
                review: '',
                userId: this.$store.state.user?.id,
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
            } else if (this.currentUserId == null){
                alert("You must login to add a review.");
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
                userId: this.currentUserId,
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

<style scoped>
h2 {
    justify-content: center;
    font-size: 30px;
    color: white;
    text-shadow: -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000, 1px 1px 0 #000; 
}
.addReview {
    background-color: rgba(141, 141, 141, 0.5);
    border: 1px solid black;
    padding-left: 10px;
    padding-right: 10px;
    padding-bottom: 10px;
  }
.container {  
    height: fit-content;
    padding-left: 10px;
    padding-right: 10px;
    padding-bottom: 10px;
    line-height: 1px;
    margin-top: 17px;
}

.text{
    display: flex;
    text-align: center;
    
}
.text label {
    margin-top: 75px;
    font-size: 25px;
    color: white;
    text-shadow: -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000, 1px 1px 0 #000; 
}
.text textarea {
    font-size: 18px;
    border-radius: 10px;
}
.rating label {
    font-size: 25px;
    color: white;
    text-shadow: -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000, 1px 1px 0 #000; 
}
.rating select {
    font-size: 23px;
    margin-left: 3px;
    margin-bottom: 20px;
    cursor: pointer;
    width: 15%;
    border-radius: 10px;
}
.buttons{
    display: flex;
    justify-content: space-evenly;
    padding-top: 10px;
    cursor: pointer;

}
button {
    border-radius: 10px;
    height: 50px;
    width: 150px;
    font-size: 17px;
    cursor: pointer;
    
}
button:hover {
    background-color: rgba(228, 186, 61, 0.753);
}
</style>