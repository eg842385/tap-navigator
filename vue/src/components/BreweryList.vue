<template>
    <div>
        <button @click.prevent="goToAddBreweryForm" >Add a New Brewery!</button>
        <table class="brewerieslist">
            <thead>
                <tr>
                    <th v-for="(header, index) in tableHeaders" :key="index">
                        {{ header }}
                    </th>
                </tr>
            </thead>
            <tbody>
                <tr class="details" v-for="brewery in breweries" :key="brewery.id">
                    <td>
                        <router-link :to="{ name: 'breweryDetails', params: { id: brewery.breweryId } }">
                            {{ brewery.breweryName }}
                        </router-link>
                    </td>
                    <td>{{ brewery.description }}</td>
                    <td>{{ brewery.address }} {{ brewery.city }}, {{ brewery.state }} {{ brewery.zipcode }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    components: {
        BreweryDetailsVue
    },
    data() {
        return {
            breweries: [],
            tableHeaders: ['Brewery Name ', 'Description ', 'Location']
        }
    },
    async created() {
        try {
            const response = await axios.get('http://localhost:9000/breweries');
            this.breweries = response.data;
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    },
    methods: {
        goToAddBreweryForm(){
            // alert('Button clicked');
            // console.log('Navigating to Add Brewery form');
            this.$router.push({name: 'addBreweries'});
        }


    }

}
</script>

<style>
.brewerieslist {
    padding: 1rem;
}

.details {
    text-align: center;
    border: 1px solid black;
}

table {
    width: 100%;
    margin: 20px;
    align-content: center;
    
}


</style>