import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

export default {

  getBreweries() {
    return http.get('/breweries')
  },

  addBrewery(newBrewery) {
    const token = localStorage.getItem('token');
    
    return http.post('/breweries', newBrewery,{
        headers: {
            Authorization: `Bearer ${token}`
        }
    });
  },

}
