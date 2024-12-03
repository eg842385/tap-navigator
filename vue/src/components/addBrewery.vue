<template>
    <form @submit.prevent="submitForm" class="addBrewery">
        <h2>Add a New Brewery!</h2>
        <div>
            <label for="name">Brewery Name: </label>
            <input id="name" type="text" v-model="newBrewery.breweryName">
        </div>
        <div>
            <label for="brewerId">Brewer ID: </label>
            <input id="brewerId" type="number" v-model="newBrewery.userId">
        </div>
        <div>
            <label for="description">Description: </label>
            <input id="description" type="text" v-model="newBrewery.description">
        </div>
        <div>
            <label for="address">Address: </label>
            <input id="address" type="text" v-model="newBrewery.address">
        </div>
        <div>
            <label for="city">City: </label>
            <input id="city" type="text" v-model="newBrewery.city">
        </div>
        <div>
            <label for="state">State: </label>
            <input id="state" type="text" v-model="newBrewery.state">
        </div>
        <div>
            <label for="zipcode">Zipcode: </label>
            <input id="zipcode" type="number" v-model="newBrewery.zipcode">
        </div>
        <button class="btn btn-submit">Submit</button>
        <button class="btn btn-cancel" v-on:click="cancelForm" type="button">Cancel</button>
    </form>
</template>

<script>
import breweryService from '../services/BreweryService';

export default {
    // props: {
    //     brewery: {
    //         type: Object,
    //         // required: true
    //     }
    // },
    data() {
        return {
            newBrewery: {
                // id: '',
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

        // submitForm() {
        //     try {
        //         const response = breweryService.addBrewery(this.newBrewery);
        //         console.log('Response: ', response);
        //         if (response.status === 200) {
        //             console.log('New Brewery Added Successfully!');
        //         } else if (response.status === 403) {
        //             console.log('You are not authorized to create a brewery.');
        //         } else {
        //             console.log('Brewery unable to created.');
        //         }
        //     } catch (error) {
        //         console.error('Failed to create brewery:', error);
        //     }

        // },
            submitForm() {
                if ( this.$store.state.user.authorities[0].name == 'ROLE_ADMIN'){
                breweryService.addBrewery(this.newBrewery)
                    .then(response => {
                        console.log('Response: ', response);
                        if (response.status === 201 || response.status === 200) {
                            console.log('New Brewery Added Successfully!');
                            alert('New Brewery Added Successfully!');
                            this.cancelForm();
                        } else if (response.status === 403 || response.status === 401) {
                            console.log('You are not authorized to create a brewery.');
                        } else {
                            console.log('Brewery unable to be created.');
                        }
                    })
                    .catch(error => {
                        console.error('Failed to create brewery:', error);
                    });
                } else {
                    alert('You are not allowed to be here!!');
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