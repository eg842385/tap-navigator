<template>
  <body>
    <header class="title">
      <div>
        <img src="src\assets\hoptoitlogo.jpg" alt="">
      </div>
    </header>
    <section id="about-us">
      <nav>
        <h3>About Us</h3>
        <p> Welcome to <strong>Hop To It: The Tap Navigator</strong> – your ultimate companion for discovering the finest
          breweries in Ohio! Our journey began with five Tech Elevator students who, bonded by their thirst for both
          knowledge and craft beer, embarked on this delicious endeavor. </p>
        <p> Fueled by our love for hops, malts, and the art of brewing, we set out to create an app that brings the best
          of Ohio’s brewery world to your fingertips. Whether you’re a seasoned beer enthusiast or a curious newbie, our
          app is designed to guide you to the perfect pint. </p>
        <p> Our team, a quirky mix of coding aficionados and beer connoisseurs, has poured their hearts (and a few pints)
          into building an app that’s as refreshing as your favorite brew. From the bustling city taprooms to the hidden
          gems in the Ohio countryside, we’ve got you covered. </p>
        <p> Join us on this hoppy adventure as we explore, sip, and share the best brews Ohio has to offer. Cheers to
          great beer and even greater friends! </p>
      </nav>
    </section>
    <section id="function">
      <div class="featuredBrewery">
        <random-brewery></random-brewery>
      </div>
    </section>
  </body>
</template>

<script>
import { RouterLink } from 'vue-router';
import RandomBrewery from '../components/RandomBrewery.vue';

export default {
  components: { RouterLink,
  RandomBrewery }
};
</script>

<style scoped>
body {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 100vh auto;
  grid-template-areas:
    "pic pic"
    "about-us function";
  margin: 0;
}

#function {
  grid-area: function;
  background-color: #FFFFE0;
  color: #FFFFE0;
  padding: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
}

#about-us {
  grid-area: about-us;
  background-color: rgb(13, 40, 18);
  color: #FFFFE0;
  padding: 0px 20px 20px 20px;

}

#about-us h3 {
  font-size: 30px;
  text-align: center;
}

p {
  font-size: 26px;
  text-align: center;
}

.title {
  grid-area: pic;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  background-image: url("@/assets/greenBeer.jpg");
  background-size: cover;
  background-position: bottom;
  background-repeat: no-repeat;
  text-align: center;
  width: 100vw;
  height: 100vh;
  font-size: 100%;
}

img {
  height: 500px;
  justify-content: flex-start;
  margin-left: 150px;
}

</style>