<template>
    <form @submit.prevent="submitForm" class="addBrewery">
        <h2>Add a New Brewery!</h2>
        <div>
            <label for="name">Brewery Name: </label>
            <input id="name" type="text" v-model="newBrewery.breweryName" required>
        </div>
        <div>
            <label for="brewerId">Brewer ID: </label>
            <input id="brewerId" type="number" v-model="newBrewery.userId" required>
        </div>
        <div>
            <label for="address">Address: </label>
            <input id="address" type="text" v-model="newBrewery.address" required>
        </div>
        <div>
            <label for="city">City: </label>
            <input id="city" type="text" v-model="newBrewery.city" required>
        </div>
        <div>
            <label for="state">State: </label>
            <input id="state" type="text" v-model="newBrewery.state" required>
        </div>
        <div>
            <label for="zipcode">Zipcode: </label>
            <input id="zipcode" type="number" v-model="newBrewery.zipcode" required>
        </div>
        <div>
            <label for="description">Description: </label>
            <textarea v-model="newBrewery.description" placeholder="Enter your description here" required></textarea>
        </div>
        <button class="btn btn-submit">Submit</button>
        <button class="btn btn-cancel" v-on:click="cancelForm" type="button">Cancel</button>
    </form>
</template>

<script>
import breweryService from '../services/BreweryService';

export default {
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
            newBrewery: {
                breweryId: '',
                breweryName: '',
                userId: '',
                description: '',
                address: '',
                city: '',
                state: '',
                zipcode: ''
            },
        };
    },
    methods: {

        submitForm() {
            if (this.isAdmin) {
                breweryService.addBrewery(this.newBrewery)
                    .then(response => {
                        console.log('Response: ', response);
                        if (response.status === 201 || response.status === 200) {
                            alert('New Brewery Added Successfully!');
                            const newBreweryId = response.data.breweryId;
                            this.cancelForm();
                            this.$router.push({
                                name: 'combined-view',
                                params: { id: newBreweryId }
                            });

                        } else {
                            console.log('Brewery unable to be created.');
                        }
                    })
                    .catch(error => {
                        console.error('Failed to create brewery:', error);
                    });
            } else {
                alert('You are not allowed to add a brewery.');
                this.$router.push({ name: 'listBreweries' });
            }
        },

        cancelForm() {
            this.newBrewery = {
                // id: '',
                breweryName: '',
                userId: '',
                description: '',
                address: '',
                city: '',
                state: '',
                zipcode: '',
            };
        },
    }
}

</script>

<style>
.addBrewery h2 {
    text-align: center;
    margin-bottom: 20px;
}
</style>