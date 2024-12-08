<template>
    <div>
        <h2>Average Rating: {{ averageRating }}</h2>
        <h2>Reviews</h2>
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
<style>
ul li {
    list-style: none;
}
</style>