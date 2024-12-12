<template>
    <body>
        <div class="container">
            <div class="logo" v-if="brewery.img">
                <img :src="brewery.img" alt="" class="image">
            </div>
            <div class="title">
                <h2>{{ brewery.breweryName }}</h2>
            </div>
            <div>
                <table class="breweryDetails">
                    <thead>
                        <tr>
                            <th v-for="(header, index) in tableHeaders" :key="index">
                                {{ header }}
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="details">
                            <td>{{ brewery.description }}</td>
                            <td>{{ brewery.address }} {{ brewery.city }}, {{ brewery.state }} {{ brewery.zipcode }}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="button">
                <button @click.prevent="goToUpdateBrewery" v-if="this.showIf" >Update Brewery</button>
            </div>
        </div>
    </body>
</template>

<script>
import BreweryService from '../services/BreweryService';
export default {
    props: {
        id: {
            type: String,
            required: true
        }
    },
    data() {
        return {
            brewery: {},
            tableHeaders: ['Description ', 'Location'],
            showIf: false
        }
    },
    computed: {
        currentUserId() {
            return this.$store.state.user.id;
        },
        isAdmin() {
            return this.$store.state.user.authorities[0].name === 'ROLE_ADMIN';
        },
        isCorrectBrewer() {
            return this.$store.state.user.authorities[0].name === 'ROLE_BREWER' && (this.currentUserId == this.brewery.userId);
        }
    },
    async created() {
        this.getBreweryDetails(this.id);
    },
    methods: {
        getBreweryDetails(id) {
            BreweryService.getBreweryById(id)
                .then(response => {
                    this.brewery = response.data;
                    this.ifCurrentBrewer();
                })
                .catch(error => {
                    console.error('Error fetching brewery details:', error);
                });
        },

        goToUpdateBrewery() {
            this.$router.push({ name: 'updateBrewery', params: { id: this.$route.params.id } });

        },

        ifCurrentBrewer(){
            if(this.isAdmin || this.isCorrectBrewer){
                this.showIf = true;
            }
        }
    }

}
</script>

<style scoped>
.logo {
    display: flex;
    justify-content: center;
}

img {
    max-width: 300px; 
    max-height: 300px;
    padding: 20px;
    width: auto; 
    height: auto;
}
.breweryDetails th {
    font-size: 30px;
    background-color: rgba(228, 186, 61, 0.753);
    padding: 1rem;
}

.breweryDetails {
    width: 100%;
    border-collapse: collapse;
    max-width: 70%;
    background-color: rgba(255, 255, 255, 0.9);
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    margin: 0 auto;
}

td {
    text-align: center;
    padding: 30px;
    font-size: 25px;
}

.breweryDetails th:first-child{
    border-top-left-radius: 10px;
}

.breweryDetails th:last-child{
    border-top-right-radius: 10px;
}

.title {
    font-size: 40px;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 10px;
    color: white;
    text-shadow: -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000, 1px 1px 0 #000;  
    background-color: rgba(141, 141, 141, 0.5);
    width: fit-content;
    justify-self: center;
    border: 1px solid black;
    margin-bottom: 10px;
}

* { margin: 0; padding: 0; box-sizing: border-box; }

button {
    border-radius: 10px;
    height: 50px;
    width: 170px;
    font-size: 17px;
}
.button{
    display: flex;
    justify-content: flex-end;
    cursor: pointer;
}
button:hover {
    background-color:  rgba(228, 186, 61, 0.753);
}

button {
    border-radius: 10px;
    cursor: pointer;
}

.details {
    text-align: center;
    padding: 70px;
}

table {
    width: 100%;
    margin: 20px;
    align-content: center;
    background-color: white;
}</style>