<template>
    
     <div class="logo">
            <img :src= "brewery.img" alt="breweryLogo" class="image">
        </div>
    <div class="title">
        <h2>{{ beer.beerName }}</h2>
    </div>
    
    <div> 
        <table class="beerDetails">
            <thead>
                <tr>
                    <th v-for="(header, index) in tableHeaders" :key="index">
                        {{ header }}
                    </th>
                </tr>
            </thead>
            <tbody>
                <tr class="details">
                    <td>{{beer.beerType}}</td>
                    <td>{{ beer.description }}</td>
                    <td>{{beer.abv}}</td>
                    
                </tr>
            </tbody>
        </table>
        
    </div>
</template>

<script>
import BeerService from '../services/BeerService';
import BreweryService from '../services/BreweryService';

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
            beer: {},
            brewery: {},
            tableHeaders: ['Type ', 'Description', 'ABV'],
            imgUrl: '{{brewery.img}}'
        }
    },
    async created() {
        this.getBeerDetails(this.id, this.beerId)
        this.getUserIdFromBrewery();
    },


    methods: {
        getBeerDetails(id, beerId) {
            BeerService.getBeerDetailsByBeerId(id, beerId)
                .then(response => {
                    this.beer = response.data;
                })
                .catch(error => {
                    console.error('Error fetching beer details:', error);
                });
        },
        deleteBeer(){
            if(!this.isAdmin && !this.isCorrectBrewer){
                alert('You are not authorized.');
                return;
            };

            if(confirm('Are you sure you want to delete this beer?')){
                BeerService.deleteBeer(this.id, this.beerId)
                .then(() => {
                    alert('Beer has been deleted successfully.');
                    this.$router.push({name: 'combined-view', params:{id: this.id}});
                })
                .catch(error => {
                    console.error('Error deleting this beer', error);
                    alert('Failed to delete beer.');
                });
            }
        },

        getUserIdFromBrewery() {
            BreweryService.getBreweryById(this.id)
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
.title {
    font-size: 40px;
    display: flex;
    justify-content: center;
    align-items: center;
    
    margin-bottom: 10px;
}

.logo {
    display: flex;
    justify-content: center;
    
    
}

img {
    margin-top: 30px;
}

.beerDetails {
    width: 100%;
    border-collapse: collapse;
    max-width: 70%;
    background-color: rgba(255, 255, 255, 0.9);
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    margin: 0 auto;
}
.beerDetails th {
    font-size: 30px;
    background-color: rgba(228, 186, 61, 0.753);
    padding: 1rem;
}

td {
    text-align: left;
    padding: 30px;
    font-size: 25px;
}
</style>