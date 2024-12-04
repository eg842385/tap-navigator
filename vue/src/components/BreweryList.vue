<template>
    <div class="container">
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
                    <td class="name">
                        <router-link :to="{ name: 'breweryDetails', params: { id: brewery.breweryId } }">
                            {{ brewery.breweryName }}
                        </router-link>
                    </td>
                    <td class="description">{{ brewery.description }}</td>
                    <td class="location">{{ brewery.address }} {{ brewery.city }}, {{ brewery.state }} {{ brewery.zipcode }}</td>
                </tr>
            </tbody>
        </table>
        <button @click.prevent="goToAddBreweryForm" >Add a New Brewery!</button>
    </div>
</template>

<script>
import axios from 'axios';

export default {
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

<style scoped>
.container {
    font-family: Arial, sans-serif;
    display: flex;
    align-content: center;
    justify-content: center; 
}

button {
    padding: 10px;
    max-height: 40px;
    border-radius: 20px;
}

button:hover{
    background-color: red;
}

.name {
    text-align: center;
}

td {
    text-align: left;
    border: 1px solid black;
    padding: 10px;
}

table {
    width: 100%;
    margin: 20px 0;
    border-collapse: collapse;
    max-width: fit-content;
    margin: 20px auto;    
}



</style>