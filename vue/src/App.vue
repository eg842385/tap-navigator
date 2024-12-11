<template>
  <div id="capstone-app">
    <header class="header">
      <div class="links">
        <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'listBreweries' }"> Breweries</router-link>&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'login' }">Login</router-link>&nbsp;|&nbsp;
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''"> Logout</router-link>
      </div>
      <div class="title">
        <p><b>Hop To It: The TapNavigator</b></p>
      </div>
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
<style scoped>
.links{
  display: flex;
  justify-content: space-between;
}
.header {
  display:flex;
  justify-content: space-between;
  align-items:center;
  font-size: 20px;
  padding: 0px 5px;
  background-color: rgba(255, 255, 255, 0.631);
}
.title{
  display: flex;
  justify-content: flex-start;
  text-align: right;
  top: 0;
}
.footer {
  position: fixed;
  bottom:0;
  text-align: center;
  align-self: baseline;
  background-color: white;
  margin-top:auto;
  left: 0;
  width: 100%;
}

</style>