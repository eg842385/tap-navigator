<template>
    <body>
        <div class="title">
            <h2>Brewery List</h2>
        </div>
        <div class="top">
            <input type="text" name="brewery-filter" class="filter" v-model="filterText"
                placeholder="Search Breweries by Name or Location">
            <button class="button" @click.prevent="goToAddBreweryForm" v-if="this.showIf">Add a New Brewery!</button>
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
            tableHeaders: ['Name', 'Location'],
            showIf: false
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
        },
        isAdmin() {
            return this.$store.state.user.authorities[0].name === 'ROLE_ADMIN';
        }

    },
    async created() {
        try {
            const response = await axios.get('http://localhost:9000/breweries');
            this.breweries = response.data;
            this.showIfAdmin();
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
        showIfAdmin() {
            if (this.isAdmin) {
                this.showIf = true;
            }
        }
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
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: flex-start;
    background-attachment: fixed;
    background-image: url('@/assets/beers.jpg');
    background-size: cover;
    background-position: 50% 80%;
    background-repeat: no-repeat;
    min-height: 100vh;
    width: 100%;
}

.container {
    display: flex;
    align-items: center;
    justify-content: center;
    max-width: 90%;
    padding: 20px;
    margin: 20px auto 0;
}

.brewerieslist th {
    font-size: 30px;
    background-color: rgba(228, 186, 61, 0.753);
    border: 1px solid black;
    padding: 1rem;
}

.title {
    font-size: 40px;
    display: flex;
    align-items: center;
    padding: 5px;
    color: white;
    text-shadow: -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000, 1px 1px 0 #000;
    background-color: rgba(141, 141, 141, 0.5);
    width: fit-content;
    justify-self: center;
    border: 1px solid black;
    margin-bottom: 40px;
    position: relative;
    top: 20px;
}

.title h2 {
    color: white;
    margin: 20px;
}

.filter {
    width: 350px;
    height: 40px;
    font-size: 17px;
    cursor: pointer;
    border-radius: 10px;
    text-align: center;
}

.top {
    display: flex;
    justify-content: space-evenly;
    align-items: center;
    padding: 10px;
    max-width: 90%;
    max-height: 40px;
    margin: 0 auto;
}

.button {
    border-radius: 10px;
    height: 50px;
    width: 200px;
    font-size: 17px;
    cursor: pointer;
}

button:hover {
    background-color: rgba(228, 186, 61, 0.753);
}

.name {
    text-align: center;
}

td {
    text-align: left;
    border: 1px solid black;
    padding: 10px;
    font-size: 25px;
}

.brewerieslist {
    width: 100%;
    border-collapse: collapse;
    max-width: fit-content;
    background-color: rgba(255, 255, 255, 0.9);
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    margin: 0 auto;
    opacity: 85%;
}

.brewerieslist tr:nth-child(even) {
    background-color: #f8f8f8;
}</style>