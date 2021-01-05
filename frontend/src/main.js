import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import store from '@/store/store.js'
// import VueAxios from 'vue-axios'
// import { makeServer } from './server'

Vue.use(ElementUI)
Vue.prototype.$axios = axios
Vue.config.productionTip = false

axios.defaults.baseURL = 'http://localhost:8080/'
// makeServer()

new Vue({
  axios,
  router,
  store,
  render: h => h(App)
}).$mount('#app')
