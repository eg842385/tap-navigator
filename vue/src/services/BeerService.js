import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

function getAuthHeader(){
    const token = localStorage.getItem('token');
    return { Authorization: `Bearer ${token}`};
  }
  
  export default {
  
    getBeersByBreweryId(id) {
        return http.get(`/breweries/${id}/beers`);
      },
  
  }