import Vue from 'vue'
import vuetify from 'vuetify'
import router from './router/router.js'
import App from './pages/App.vue'
import 'vuetify/dist/vuetify.min.css'
import store from './store/index.js'
import AlertCmp from './pages/Alert.vue'

// import VueResource from 'vue-resource'

Vue.config.productionTip = false

// Vue.use(VueResource)
Vue.component('app-alert', AlertCmp)
Vue.use(vuetify, {
  theme: {
    primary: '#9652ff',
    success: '#3cd1c2',
    info: '#ffaa2c',
    error: '#f83e70'
  }
})
let app
if (!app) {
  app = new Vue({
    el: '#app',
    store,
    router,
    render: a => a(App)
  })
}
