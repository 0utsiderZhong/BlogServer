<template>
  <div class="simple-header">
    <head-room :speed="600" :zIndex="1003" style="position: fixed;">
      <header>
        <div id="header" class="show" >
          <ul id="nav" >
            <li class="nav-dropdown-container" >
              <router-link class="nav-link" to="/" style="font-weight: bold">
                Home
              </router-link>
            <li>
              <ul class="nav-dropdown">
                <li v-for="item in categories" v-bind:key="item.title">
                  <router-link class="nav-link" :to="routerLink(item)">
                    {{ item.title }}
                  </router-link>
                </li>
              </ul>
            </li>
            <li class="nav-dropdown-container" v-for="item in menus" v-bind:key="item.order">
              <a v-if="item.target ===true" class="nav-link" :href="item.url" :target="item.target">
                {{ item.title }}
              </a>
              <router-link v-if="item.target === false" class="nav-link" :to="item.url">
                {{ item.title }}
              </router-link>
            </li>
          </ul>
        </div>
      </header>
    </head-room>
  </div>
</template>

<script>
import {headroom} from 'vue-headroom';
import {mapState, mapMutations, mapActions} from 'vuex';

export default {
  name: 'simple-header',
  data() {
    return {
      searchKeyWords: '',
      searchResult: [],
      showMobileSearchView: false
    };
  },
  mounted() {
    if (!this.$store.state.base.categories) this.getCategories();
    if (!this.$store.state.base.menus) this.getMenus();
  },
  computed: {
    ...mapState({
      categories: state => state.base.categories,
      menus: state => state.base.menus,
      siteTheme: state => state.base.siteTheme
    })
  },
  methods: {
    ...mapMutations({
      updateSiteTheme: 'base/UPDATE_SITE_THEME'
    }),
    ...mapActions({
      getCategories: 'base/GET_CATEGORIES',
      getMenus: 'base/GET_MENUS'
    }),
    rootRouterLink(category) {
      let router = {};
      router.name = category.category_type;
      return router;
    },
    routerLink(category) {
      let router = {};
      router.name = "articles/category";
      router.params = {};
      router.params['id'] = category.id;
      return router;
    },
  },
  components: {
    'head-room': headroom,
    // 'search-view': SearchView
  }
};
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
@import "stylus/header.styl";
</style>
