<template>
  <body>
    <header>
      <h1>BEER FINDER</h1>
    </header>

    
      <section id="left-panel">
        <nav>
          <ul>
            <li><router-link to="/breweries">Breweries</router-link></li>
            <li><a href="#">About</a></li>
            <li><a href="#">Portfolio</a></li>
            <li><a href="#">Services</a></li>
            <li><a href="#">Contact</a></li>
          </ul>
        </nav>
      </section>
      <section id="content">
        <nav>
          <ul>
            <li><a href="#">Home</a></li>
            <li><a href="#">About</a></li>
            <li><a href="#">Portfolio</a></li>
            <li><a href="#">Services</a></li>
            <li><a href="#">Contact</a></li>
          </ul>
        </nav>
      </section>
      <section id="right-panel">
        <nav>
          <ul>
            <li><a href="#">Home</a></li>
            <li><a href="#">About</a></li>
            <li><a href="#">Portfolio</a></li>
            <li><a href="#">Services</a></li>
            <li><a href="#">Contact</a></li>
          </ul>
        </nav>
      </section>
   

    <footer>
      <h1>FOOTER</h1>
    </footer>
  </body>
</template>

<script>
import { RouterLink } from 'vue-router';

export default {
    components: { RouterLink }
};
</script>





<style scoped>
body {
  display: grid;
  grid-template-columns: 1fr 2fr 2fr 1fr;
  grid-template-rows: 100px 1fr 100px;
  grid-template-areas:
    "header header header header"
    "left-panel content content right-panel"
    "footer footer footer footer";
  height: 100vh;
  margin: 0;
    


}

ul {
   list-style-type: none; padding-left: 0;
  }

#left-panel {
  grid-area: left-panel;
  background-color: yellowgreen;
  justify-items: left;
}

#right-panel {
  grid-area: right-panel;
  background-color: royalblue;
  
}

header {
  grid-area: header;
  background-color: peachpuff;
  text-align: center;
  width: 100vw;
  
}

footer {
  grid-area: footer;
  background-color: blueviolet;
  justify-items: center;
  
  
}

#content {
  grid-area: content;
  background-color: brown;
  

}
</style>