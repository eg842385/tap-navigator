<template>
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
                    <td>{{ beer.beerName }}</td>
                    <td>{{beer.beerType}}</td>
                    <td>{{ beer.description }}</td>
                    <td>{{beer.abv}}</td>
                    <td>{{beer.img}}</td>
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
            tableHeaders: ['Name ', 'Type ', 'Description', 'ABV', 'PIC']
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

<style scoped></style>