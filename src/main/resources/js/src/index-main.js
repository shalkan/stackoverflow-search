import 'babel-polyfill';
import Vue from 'vue';
import Vuex from 'vuex';
import VueRouter from 'vue-router';
import ElementUI from 'element-ui';
import {sync} from 'vuex-router-sync';

import store from './index-store';
import router from './index-router';


Vue.use(Vuex);
Vue.use(VueRouter);
Vue.use(ElementUI);

sync(store, router);

new Vue({
  el: '#app',
  store,
  router
});

