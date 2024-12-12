<template>
    <div class="container">
        <form @submit.prevent="submitForm" class="addBeer">
            <h2>Add a New Beer!</h2>
            <div>
                <label for="beerName">Name: </label>
                <input id="beerName" type="text" v-model="newBeer.beerName" required>
            </div>
            <div>
                <label for="beerType">Type: </label>
                <input id="beerType" type="text" v-model="newBeer.beerType" required>
            </div>
            <div>
                <label for="description">Description: </label>
                <textarea v-model="newBeer.description" placeholder="Enter your description here" required></textarea>
            </div>
            <div>
                <label for="abv">ABV: </label>
                <input id="abv" type="text" v-model="newBeer.abv" required>
            </div>
            <!-- <div>
                <label for="img">Img: </label>
                <input id="img" type="text" v-model="newBeer.img">
            </div> -->
            <button class="btn btn-submit">Submit</button>
            <button class="btn btn-cancel" v-on:click="cancelForm" type="button">Clear</button>
            <button class="btn btn-cancel" v-on:click="goBack" type="button">Go Back</button>
        </form>
    </div>
</template>
<script>
import BeerService from '../services/BeerService';
import BreweryService from '../services/BreweryService';

export default {
    computed: {
        currentUserId() {
            return this.$store.state.user.id;
        },
        isAdmin() {
            return this.$store.state.user.authorities[0].name == 'ROLE_ADMIN';
        },
        isCorrectBrewer() {
            return this.$store.state.user.authorities[0].name == 'ROLE_BREWER'
                && (this.currentUserId == this.brewery.userId);
        }
    },
    data() {
        return {
            newBeer: {
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
        this.getUserIdFromBrewery();
    },
    methods: {
        submitForm() {
            if (this.isAdmin || this.isCorrectBrewer) {
                BeerService.addBeer(this.$route.params.id, this.newBeer)
                    .then(response => {
                        console.log('Response: ', response);
                        if (response.status === 201 || response.status === 200) {
                            alert('New Beer Added Successfully!');
                            // const newBreweryId = response.data.breweryId;
                            this.cancelForm();
                            this.$router.push({
                                name: 'combined-view',
                                params: { id: this.$route.params.id }
                            });

                        } else {
                            console.log('Beer unable to be created.');
                        }
                    })
                    .catch(error => {
                        console.error('Failed to create beer:', error);
                    });
            } else {
                alert('You are not allowed to add a beer.');
                this.$router.push({ name: 'listBreweries' });
            }
        },

        cancelForm() {
            this.newBeer = {
                breweryId: this.$route.params.id,
                beerName: '',
                beerType: '',
                description: '',
                abv: '',
                img: '',
            };
        },

        goBack() {
            this.$router.push({
                name: 'combined-view',
                params: { id: this.$route.params.id }
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
        }
    }
}
</script>

<style scoped>
form {
    background-color: rgba(141, 141, 141, 0.5);
    justify-self: center;
    padding: 10px;
    width: 400px
}

.container {
    height: 100%;
    background-attachment: fixed;
    background-image: url('@/assets/verticalbeer.jpg');
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    padding: 20px;
}

h2 {
    color: white;
    text-shadow: -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000, 1px 1px 0 #000;
    font-size: 30px;
    justify-self: center;
    padding-bottom: 20px;
}

button {
    border-radius: 10px;
    margin-top: 10px;
    margin-left: 10px;
    height: 35px;
    width: 80px;
    font-size: 15px;
    cursor: pointer;
}

button:hover {
    background-color: rgba(228, 186, 61, 0.753);
}


label {
    display: block;
    font-size: 20px;
    margin-bottom: 5px;

}

div {
    color: white;
    text-shadow: -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000, 1px 1px 0 #000;
    font-size: 20px;
}

* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

input {
    width: 100%;
    padding: 10px;
    border-radius: 10px;

}

textarea {
    width: 100%;
    padding: 10px;
    border-radius: 10px;

}</style>