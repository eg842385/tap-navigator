<template>
    <body>
        <div class="title">
            <h2>Brewery List</h2>
        </div>
        <div class="top">
            <input type="text" name="brewery-filter" class="filter" v-model="filterText"
                placeholder="Filter Breweries by Name or Location">
            <button @click.prevent="goToAddBreweryForm">Add a New Brewery!</button>
        </div>
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
                    <tr class="details" v-for="brewery in filterBreweries" :key="brewery.id">
                        <td class="name">
                            <router-link :to="{ name: 'combined-view', params: { id: brewery.breweryId } }">
                                {{ brewery.breweryName }}
                            </router-link>
                        </td>
                        <td class="description">{{ brewery.description }}</td>
                        <td class="location">{{ brewery.address }} {{ brewery.city }}, {{ brewery.state }} {{
                            brewery.zipcode }}
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </body>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            breweries: [],
            filterText: '',
            tableHeaders: ['Name', 'Description', 'Location']
        }
    },
    computed: {
        filterBreweries() {
            const search = this.filterText.toLowerCase();
            if (!search) {
                return this.breweries;
            }
            return this.breweries.filter((brewery) => {

                return (brewery.breweryName.toLowerCase().includes(search) ||
                    brewery.city.toLowerCase().includes(search) ||
                    brewery.state.toLowerCase().includes(search));
            });
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
        goToAddBreweryForm() {
            // alert('Button clicked');
            // console.log('Navigating to Add Brewery form');
            this.$router.push({ name: 'addBreweries' });
        },


    }

}
</script>

<style scoped>
html,
body {
    height: 100%;
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}
body {
    height: 100vh;
    background-image: url('@/assets/verticalbeer.jpg');
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
}
.container {
    font-family: Arial, sans-serif;
    display: flex;
    align-items: center;
    justify-content: center;
    max-width: 90%;
    padding: 20px;
    margin: 0 auto;
}
.brewerieslist th {
    font-size: 25px;
    background-color: peachpuff;
    border: 1px solid black;
    padding: 1rem;
}
.title {
    font-size: 40px;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 10px;
}
.title h2{
    color: white;
}
.filter {
    width: 300px;
    height: 30px;
}

.top {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px;
    max-height: 40px;
    margin: 0 70px;
}

button {
    border-radius: 10px;
    height: 30px;
}

button:hover {
    background-color: peachpuff;
}

.name {
    text-align: center;
}

td {
    text-align: left;
    border: 1px solid black;
    padding: 10px;
}

.brewerieslist {
    width: 100%;
    border-collapse: collapse;
    max-width: fit-content;
    background-color: rgba(255, 255, 255, 0.9); 
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    margin: 0 auto;
}

.brewerieslist tr:nth-child(even) {
    background-color: #f8f8f8;
  }
</style>