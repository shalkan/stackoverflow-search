import Vue from 'vue';
import Vuex from 'vuex';

import * as mutations from './search-page-mutations';
import * as actions from './search-page-actions';

Vue.use(Vuex);

export default {
  namespaced: true,
  state: {
    loading: false,
    searchString: '',
    page: 1,
    pageSize: 10,
    questions: [],
    hasMore: false
  },
  mutations: {
    [mutations.SET_LOADING](state, loading = false) {
      state.loading = !!loading;
    },
    [mutations.SET_SEARCH_STRING](state, searchString = '') {
      state.searchString = searchString;
    },
    [mutations.SET_PAGE](state, page = 1) {
      state.page = page;
    },
    [mutations.SET_QUESTIONS](state, questions = []) {
      state.questions = questions;
    },
    [mutations.SET_HAS_MORE](state, hasMore = false) {
      state.hasMore = hasMore;
    }
  },
  actions
};