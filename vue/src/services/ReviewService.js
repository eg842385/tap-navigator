import axios from 'axios';

const http = axios.create({
    baseURL: import.meta.env.VITE_REMOTE_API
});

function getAuthHeader() {
    const token = localStorage.getItem('token');
    return { Authorization: `Bearer ${token}` };
}

export default {
    addReview(id, beerId, newReview) {
        return http.post(`breweries/${id}/beers/${beerId}/reviews`, newReview, { headers: getAuthHeader() });
    },

    getListOfReviews(id, beerId){
        return http.get(`breweries/${id}/beers/${beerId}/reviews`);
    }
}