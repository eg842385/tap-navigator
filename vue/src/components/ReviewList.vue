<template>
    <div class="container">
        <h2 class="avg">Average Rating: {{ averageRating }}</h2>
        <ul>
            <li v-for="r in reviews" :key="r.reviewId">
                <div>
                    <img v-for="i in Math.round(r.review.rating)" :key="i" src="@/assets/star.png" alt="star" />
                </div>
                <p>{{ r.review.review || "No comments provided" }}</p>
                <h4>{{ r.username }}</h4>
            </li>
        </ul>
    </div>
</template>
<script>
import ReviewService from '../services/ReviewService';
export default {
    props: {
        id: {
            type: String,
            required: true
        },
        beerId: {
            type: String,
            required: true
        }
    },
    data() {
        return {
            reviews: [],
            averageRating: 0
        };
    },
    created() {
        this.getReviewData(this.id, this.beerId);
    },
    methods: {

        getReviewData(id, beerId) {
            ReviewService.getListOfReviews(id, beerId)
                .then(response => {
                    this.reviews = response.data;
                    console.log(response.data);
                    this.calculateAverageRating();
                })
                .catch(error => {
                    console.error('Error fetching reviews:', error);
                });
        },
        calculateAverageRating() {
            const reviews = this.reviews;
            if (reviews.length === 0) {
                this.averageRating = 0;
                return;
            }

            let sum = reviews.reduce((currentSum, review) => {
                return currentSum + review.review.rating;
            }, 0);
            this.averageRating = (sum / reviews.length).toFixed(2);
            return this.averageRating;   
        }

    }
}
</script>
<style scoped>
.avg{
    display: flex;
    font-size: 30px;
    color: white;
    text-shadow: -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000, 1px 1px 0 #000;  
    background-color: rgba(141, 141, 141, 0.5);
    width: fit-content;
    justify-self: center;
    border: 1px solid black;
    
}

h2 {
    padding-left: 10px;
    padding-right: 10px;
}



ul li {
    list-style: none;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    border-radius: 10px;
    padding: 10px;
    background-color: rgba(255, 255, 255, 0.9);
}
li {
    margin: 10px;
}
img {
    height: 2rem;
}
li p, h4 {
    font-size: 20px;
}

</style>