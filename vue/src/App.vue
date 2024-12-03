<template>
  <div id="capstone-app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link> &nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'addBreweries' }" v-if="$store.state.token != ''"> Breweries</router-link>
    </div>
    <router-view />
  </div>
</template>
