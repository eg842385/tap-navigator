<template>
  <div id="capstone-app">
    <header class="header">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'listBreweries' }"> Breweries</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'login' }">Login</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''"> Logout</router-link>
    </header>
    <router-view />
    <footer class="footer">
      © 2024 - {{currentYear}} Hop To It: The Tap Navigator. All Rights Reserved.
    </footer>
  </div>
</template>
<script>
export default {
  data() {
    return {
      currentYear: new Date().getFullYear()
    }
  }
}
</script>
<style>
.header {
  font-size: 20px;
  padding: 10px;
  background-color: white;
}
.footer {
  text-align: center;
  align-self: baseline;
  background-color: white;
}
</style>