<template>
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
                    <td>{{ brewery.breweryName }}</td>
                    <td>{{ brewery.description }}</td>
                    <td>{{ brewery.address }} {{ brewery.city }}, {{ brewery.state }} {{ brewery.zipcode }}</td>
                </tr>
            </tbody>
        </table>
    </div>
    <div>
        <button @click.prevent="goToUpdateBrewery" >Update Brewery</button>
    </div>
</template>

<script>
import BreweryService from '../services/BreweryService';


export default {
    props : {
        id: {
            type: String,
            required: true
        }
    },
    data() {
        return {
            brewery: {},
            tableHeaders: ['Brewery Name ', 'Description ', 'Location']
        }
    },
    async created() {
        this.getBreweryDetails(this.id);
    },
    methods: {
       getBreweryDetails(id){
        BreweryService.getBreweryById(id)
        .then(response => {
            this.brewery = response.data;
        })
        .catch(error => {
            console.error('Error fetching brewery details:', error);
        });
       },

       goToUpdateBrewery(){
        this.$router.push({name: 'updateBrewery', params:{id : this.$route.params.id} });

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