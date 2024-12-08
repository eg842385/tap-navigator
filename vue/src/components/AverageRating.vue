<!-- computed: {
    averageRating() {
        const reviews = this.reviews;

        if (reviews.length === 0) {
            return 0;
        }

        let sum = reviews.reduce((currentSum, review) => {
            return currentSum + review.rating;
        }, 0);
        return (sum / reviews.length).toFixed(2);
    }
}, -->