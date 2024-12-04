<template>
  <!DOCTYPE html>
  <html lang="en">

  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Photography Portfolio</title>
    <link href="https://fonts.googleapis.com/css2?family=Kalam:wght@400;700&family=Oswald:wght@300;400;600&display=swap"
      rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
    <link rel="stylesheet" href="css/style.css">
  </head>

  <body>
    <section id="left-panel">
      <header>
        <h1>BEER FINDER</h1>
      </header>
      <h2>drannkkkk</h2>
      <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Fuga eaque quo cumque minus accusamus maiores
        consectetur! Expedita quis cumque, voluptatibus voluptatem laboriosam nobis unde! Porro consequuntur ad
        repudiandae doloribus odio?</p>
      <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Mollitia tenetur earum eligendi dicta magni vel illo
        architecto maiores, explicabo consequatur, hic omnis ex, vero quod porro beatae! Nobis, beatae accusantium.</p>
      <nav>
        <ul>
          <li><a href="#">Home</a></li>
          <li><a href="#">About</a></li>
          <li><a href="#">Portfolio</a></li>
          <li><a href="#">Services</a></li>
          <li><a href="#">Contact</a></li>
        </ul>
      </nav>
      <footer>
        <ul class="social-icons">
          <li><a href="#"><i class="fab fa-facebook fa-3x"></i></a></li>
          <li><a href="#"><i class="fab fa-instagram fa-3x"></i></a></li>
          <li><a href="#"><i class="fab fa-twitter fa-3x"></i></a></li>
          <li><a href="#"><i class="fab fa-pinterest fa-3x"></i></a></li>
          <li><a href="#"><i class="fab fa-behance fa-3x"></i></a></li>
        </ul>
      </footer>
    </section>
    <section id="right-panel">
      <div id="welcome">
        Welcome to my site
      </div>
    </section>
  </body>

  </html>
</template>

<script>
export default {
};
</script>
<style>
body {
  margin: 0;
  padding: 0;
  min-height: 100vh;
  /* Photo by Andre Furtado on Unsplash */
  background: url(../img/andre-furtado-JtV6zyOZSrA-unsplash.jpg) left / cover no-repeat;
}

#left-panel {
  background-color: #F7F7F7;
}

ul.social-icons {
  list-style-type: none;
  margin: 20px 20px;
  padding: 0;
}

body {
  display: flex;
}

#left-panel {
  display: flex;
  flex-direction: column;
  flex-basis: 35%;
}

.social-icons {
  display: flex;
  justify-content: space-between;
}

nav {
  flex-grow: 1;
}

#right-panel {
  display: flex;
  justify-content: center;
  align-items: center;
  flex: 1;
}

#welcome {
  display: flex;
  justify-content: center;
  align-items: center;
}

/* YOU WON'T NEED TO CHANGE ANY STYLES BELOW THIS LINE */
h1,
h2 {
  font-family: 'Kalam', cursive;
  margin: 0;
  text-align: center;
}

h1 {
  font-size: 5rem;
}

h2 {
  font-size: 2rem;
  font-weight: 400;
  margin: 20px 0;
}

header {
  background-color: #F2CC65;
}

p {
  font-family: 'Oswald', sans-serif;
  font-weight: 300;
  font-size: 1.1rem;
  line-height: 2.1rem;
  padding: 0 20px;
}

nav ul {
  list-style-type: none;
  margin: 0 20px 0 20px;
  padding: 0;
}

nav ul li {
  font-family: 'Oswald', sans-serif;
  font-size: 2rem;
  font-weight: 400;
  text-transform: uppercase;
  border-bottom: 1px solid #707070;
  padding-top: 30px
}

nav a,
nav a:visited {
  text-decoration: none;
  color: #1B1B1B;
}

nav a:hover {
  color: #F2CC65;
  padding-left: 5px;
}

.fab {
  color: #3A3B00
}

.social-icons li a:hover .fab {
  color: #F2CC65
}

#welcome {
  background-color: #F2CC65;
  font-family: 'Kalam', cursive;
  text-align: center;
  font-size: 5rem;
  padding: 20px;
  border-radius: 10px;
}

@media(max-width: 1024px) {

  h1,
  #welcome {
    font-size: 3rem;
  }

  h2 {
    font-size: 1.4rem;
  }

  .fa-3x {
    font-size: 1.5rem;
  }
}

@media(max-width: 425px) {
  #left-panel {
    flex-basis: 100%;
  }

  nav ul li {
    font-size: 1rem;
  }

  ul.social-icons {
    margin-top: 40px;
  }

  #right-panel {
    display: none;
  }
}
</style>
