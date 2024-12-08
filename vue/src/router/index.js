import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import AddBreweryView from '../views/AddBreweryView.vue';
import BreweryListView from '../views/BreweryListView.vue';
// import BreweryDetailsView from '../views/BreweryDetailsView.vue';
import UpdateBreweryView from '../views/UpdateBreweryView.vue';
import CombinedBeerBreweryView from '../views/CombinedBeerBreweryView.vue';
import BeerDetailsView from '../views/BeerDetailsView.vue';
import AddBeerView from '../views/AddBeerView.vue';


/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/breweries/add",
    name: "addBreweries",
    component: AddBreweryView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/breweries",
    name: "listBreweries",
    component: BreweryListView,
    meta: {
      requiresAuth: false
    }
  },
  // {
  //   path: "/breweries/:id",
  //   name: "breweryDetails",
  //   component: BreweryDetailsView,
  //   props: true,
  //   meta: {
  //     requiresAuth: false
  //   }
  // },
  {
    path: "/breweries/:id/update",
    name: "updateBrewery",
    component: UpdateBreweryView,
    meta: {
      requiresAuth: true
    }
  },
  // /beers from url below
  {
    path: "/breweries/:id", 
    name: "combined-view",
    component: CombinedBeerBreweryView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/breweries/:id/beers/:beerId",
    name: "beerDetails",
    component: BeerDetailsView,
    props: true,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/breweries/:id/beers/add",
    name: "addBeer",
    component: AddBeerView,
    meta: {
      requiresAuth: true
    }
  },


];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {

  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return {name: "login"};
  }
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;
