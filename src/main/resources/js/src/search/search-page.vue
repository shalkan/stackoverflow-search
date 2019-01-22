<template>
    <div v-loading.body="loading">
        <h2>Search on stackoverflow:</h2>
        <el-input placeholder="Type search text" :value="searchString"
                  @change="searchStringChange"/>
        <el-button size="small" type="primary" @click="doSearch">Search</el-button>
        <el-button size="small" type="primary" @click="clearSearch">Clear</el-button>
        <div v-show="!!questions && questions.length > 0">
            <h2>Questions</h2>
            <el-card>
                <el-table :data="questions" style="width: 100%" stripe>
                    <el-table-column>
                        <template slot-scope="scope">
                            <div><a :href="scope.row.originalLink" target="_blank">{{scope.row.title}}</a></div>
                            author: {{scope.row.author}} posted: {{scope.row.postDate | formatDateTime}}
                            <el-tag  v-show="scope.row.isAnswered" type="success">Answered</el-tag>
                        </template>
                    </el-table-column>
                </el-table>
                <el-button size="small" type="primary" v-show="hasMore" @click="doSearch">Show more</el-button>
            </el-card>

        </div>
    </div>
</template>
<script>
  import Vue from 'vue'
  import Vuex, {mapState} from 'vuex'
  import ElementUI from 'element-ui'
  import 'element-ui/lib/theme-chalk/index.css'
  import store from '../index-store';
  import router from '../index-router';

  import locale from 'element-ui/lib/locale'
  import lang from 'element-ui/lib/locale/lang/ru-RU'

  import * as mutations from '../search/search-page-mutations'

  import moment from 'moment'

  locale.use(lang)
  Vue.use(ElementUI)
  Vue.use(Vuex)

  export default Vue.component('SearchPage', {
    computed: mapState({
      loading: state => state[mutations.MAIN_NAMESPACE].loading,
      searchString: state => state[mutations.MAIN_NAMESPACE].searchString,
      questions: state => state[mutations.MAIN_NAMESPACE].questions,
      hasMore: state => state[mutations.MAIN_NAMESPACE].hasMore
    }),
    store,
    router,
    methods: {
      searchStringChange(value) {
        store.commit(mutations.NAMESPACE + mutations.SET_SEARCH_STRING, value);
      },
      doSearch() {
        store.dispatch(mutations.NAMESPACE + 'doSearch');
      },
      clearSearch() {
        store.dispatch(mutations.NAMESPACE + 'clearSearch');
      }
    },
    filters: {
      formatDateTime: (date) => {
        if (!date) return '';
        return moment(date).format('DD.MM.YYYY HH:mm');
      }
    }
  })
</script>