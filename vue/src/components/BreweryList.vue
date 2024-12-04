<template>
    <div>
        <button @click="this.$router.push({name:'addBreweries'})">Add a New Brewery!</button>
        <table>
            <thead>
                <tr>
                    <th v-for="(header, index) in tableHeaders" :key="index">
                        {{ header }}
                    </th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="brewery in breweries" :key="brewery.id">
                    <td>{{ brewery.breweryName }}</td>
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
    method: {
        goToAddBreweryForm(){
            alert('Button clicked');
            console.log('Navigating to Add Brewery form');
            this.$router.push({name: 'addBreweries'});
        }
    }

}
</script>

<style></style>