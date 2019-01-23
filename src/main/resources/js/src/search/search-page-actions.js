import * as mutations from '../search/search-page-mutations'

import axios from 'axios'

export const doSearch = (context, payload) => {
  context.commit(mutations.SET_LOADING, true);
  let page = context.state.questions && context.state.questions.length > 0 ? context.state.page + 1 : 1;
  context.commit(mutations.SET_PAGE, page);
  axios.get("/search", {
    params: {
      searchString: context.state.searchString,
      pageNumber: page,
      pageSize: context.state.pageSize,
    }
  }).then(response => {
    if (response.status == 200) {
      context.commit(mutations.SET_HAS_MORE, response.data.hasMore);
      context.commit(mutations.SET_QUESTIONS, context.state.questions.concat(response.data.items));
    }
    context.commit(mutations.SET_LOADING, false);
  }).catch(err => {
    console.error(err);
    context.dispatch('clearSearch');
    //TODO: show error message
  })
}

export const clearSearch = (context, payload = {}) => {
  context.commit(mutations.SET_LOADING, true);
  context.commit(mutations.SET_SEARCH_STRING);
  context.commit(mutations.SET_PAGE);
  context.commit(mutations.SET_QUESTIONS);
  context.commit(mutations.SET_HAS_MORE);
  context.commit(mutations.SET_LOADING, false);
}