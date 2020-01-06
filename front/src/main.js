import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import service from './service/service'
import './service/axios'
import './registerServiceWorker'
import ViewUI from 'view-design'
import 'view-design/dist/styles/iview.css'

Vue.use(service)
Vue.use(ViewUI)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
