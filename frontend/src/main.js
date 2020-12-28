import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import VueAxios from 'vue-axios'
import { makeServer } from './server'

Vue.use(ElementUI)
Vue.use(VueAxios, axios)
Vue.config.productionTip = false

makeServer()

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
