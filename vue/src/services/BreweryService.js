import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

function getAuthHeader(){
  const token = localStorage.getItem('token');
  return { Authorization: `Bearer ${token}`};
}

export default {

  getBreweries() {
    return http.get('/breweries')
  },

  addBrewery(newBrewery) {
        return http.post('/breweries/add', newBrewery, 
        { headers: getAuthHeader() });
  },

  getBreweryById(id) {
    return http.get(`/breweries/${id}`);
  },

  updateBrewery(brewery) {
    return http.put(`breweries/${brewery.breweryId}/update`, brewery,
    { headers: getAuthHeader() })
  }


}
