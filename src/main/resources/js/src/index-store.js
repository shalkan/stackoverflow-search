import Vue from 'vue';
import Vuex from 'vuex';

import searchPageStore from './search/search-page-store';

import * as searchPageMutations from './search/search-page-mutations';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    [searchPageMutations.MAIN_NAMESPACE]: searchPageStore
  }
});

