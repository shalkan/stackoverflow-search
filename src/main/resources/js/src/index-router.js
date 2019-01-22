import Vue from 'vue';
import VueRouter from 'vue-router';

import SearchPage from './search/search-page.vue';

Vue.use(VueRouter);

const routes = [
  {path: '/', component: SearchPage}
];

const router = new VueRouter({
  routes
});

export default router;