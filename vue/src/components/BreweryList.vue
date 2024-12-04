<template>
    <div>
        <table>
            <thead>
                <tr>
                    <th v-for="(header, index) in tableHeaders" :key="index">
                        {{ header }}
                    </th>
                </tr>
            </thead>
            <tbody>
                
            </tbody>

        </table>
    </div>
</template>

<script>
import BreweryService from '../services/BreweryService';
export default {
    data() {
        return {
            tableHeaders: ['Brewery Name | ', 'Description | ', 'Location']
        }
    },
    methods: {

    }

}
</script>

<style></style>