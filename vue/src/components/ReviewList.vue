<template>
    <div>
        <h2>Reviews</h2>
        <ul>
            <li v-for="r in reviews" :key="r.reviewId">
                <h3>{{ r.rating }}</h3>
                <p>{{ r.review || "No comments provided" }}</p>
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
            reviews: []
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
                })
                .catch(error => {
                    console.error('Error fetching reviews:', error);
                });
        }
    }
}
</script>
<style></style>