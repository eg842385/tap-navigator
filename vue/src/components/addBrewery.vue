<template>
    <form @submit.prevent="submitForm" class="addBrewery">
        <h2>Add a New Brewery!</h2>
        <div>
            <label for="name">Brewery Name: </label>
            <input id="name" type="text" v-model="newBrewery.name">
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
                id: '',
                name: '',
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

        async submitForm() {
            try {
                const response = await breweryService.addBrewery(this.newBrewery);
                console.log('Brewery created: ', response.data)
            } catch(error) {
                console.error('Failed to create brewery:', error);
            }


            // if (this.newBrewery.id === 0) {
            //     // add
            //     breweryService
            //         .addBrewery(this.newBrewery)
            //         .then(response => {
            //             if (response.status === 201) {
            //                 console.log('New Brewery Added Successfully!');
            //                 this.$router.push({ name: 'BreweryView', params: { id: this.newBrewery.id } });
            //             }
            //         })
            //         .catch(error => {
            //             this.handleErrorResponse(error, 'adding');
            //         });
            // } else {
                
            // }
        },
        cancelForm() {
            this.newBrewery = {
                id: '',
                name: '',
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

<style></style>