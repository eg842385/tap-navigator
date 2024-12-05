<template>

  <div>test</div>

  <form >

    <h2>Update Brewery</h2>
    
    <div>
      <label for="name"> Brewery Name: </label>
      <input id="name" type="text">
    </div>
    <div>
      <label for="brewerId">Brewer ID: </label>
      <input id="brewerId" type="text">
    </div>
    <div>
      <label for="description">Description: </label>
      <input id="description" type="text">
    </div>
    <div>
      <label for="address"> Address: </label>
      <input id="address" type="text">
    </div>
    <div>
      <label for="city"> City: </label>
      <input id="city" type="text">
    </div>
    <div>
      <label for="state"> State:  </label>
      <input id="state" type="text">
    </div>
    <div>
      <label for="zipcode"> Zipcode:  </label>
      <input id="zipcode" type="text">
    </div>
    <button class="btn btn-submit">Submit</button>
        <button class="btn btn-cancel" v-on:click="cancelForm" type="button">Cancel</button>

  </form>



</template>




<script>
export default {


};
</script>




<style scoped>

</style>