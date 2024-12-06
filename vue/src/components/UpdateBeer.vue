<template>
    <form @submit.prevent="updateBeer" class="updateBeer">
        <h2>Update a Beer!</h2>
        <div>
            <label for="beerName">Name: </label>
            <input id="beerName" type="text" v-model="updatedBeer.beerName" required>
        </div>
        <div>
            <label for="beerType">Type: </label>
            <input id="beerType" type="text" v-model="updatedBeer.beerType" required>
        </div>
        <div>
            <label for="description">Description: </label>
            <textarea v-model="updatedBeer.description" placeholder="Enter your description here" required></textarea>
        </div>
        <div>
            <label for="abv">ABV: </label>
            <input id="abv" type="text" v-model="updatedBeer.abv" required>
        </div>
        <div>
            <label for="img">Img: </label>
            <input id="img" type="text" v-model="updatedBeer.img">
        </div>
        <button class="btn btn-submit">Submit</button>
    </form>
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
    data() {
        return {
            isFormShowing: true,
            updatedBeer: {
                beerId: this.$route.params.beerId,
                breweryId: this.$route.params.id,
                beerName: '',
                beerType: '',
                description: '',
                abv: '',
                img: '',
            },

            brewery: {}
        };
    },
    created() {
        this.getBeerData();
        this.getUserIdFromBrewery();
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
    methods: {
        getBeerData() {
            BeerService.getBeerDetailsByBeerId(this.id, this.beerId)
                .then(response => {
                    this.updatedBeer = response.data;
                })
                .catch(error => {
                    console.error("Error fetching beer details:", error);
                });
        },

        getUserIdFromBrewery() {
            const breweryId = this.$route.params.id;
            BreweryService.getBreweryById(breweryId)
                .then(response => {
                    this.brewery = response.data;
                })
                .catch(error => {
                    console.error('Error fetching brewery details:', error);
                });
        },

        updateBeer() {
            if (this.isAdmin || this.isCorrectBrewer) {
                BeerService.updateBeer(this.id, this.beerId, this.updatedBeer)
                    .then(() => {
                        alert('Beer details updated successfully!');
                        this.isFormShowing = false;
                        this.$router.go(0);
                        // this.$router.push({name: 'beerDetails', params: {id: this.$route.params.id, beerId: this.$route.params.beerId}});
                    })
                    .catch((error) => {
                        console.error('Error updating beer: ', error);
                    });
            } else {
                alert('You are not authorized to update a beer.');
            }
        }
    },
}
</script>

<style scoped></style>