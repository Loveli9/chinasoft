import Vue from 'vue'
import App from '@/App.vue'
import router from '@/router'
import store from '@/store'
import elementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import ZuiCss from '../node_modules/zui/dist/css/zui.min.css'
import NewProject from '@/components/NewProject/NewProject.vue'
import NewIteration from '@/components/NewIteration/NewIteration.vue'
import CutPage from '@/components/CutPage/CutPage.vue'
import Import from './components/Import/Import.vue'
import request from './util/request.js'

Vue.config.productionTip = false
Vue.use(elementUI)
Vue.prototype.$ZuiCss = ZuiCss;   
Vue.prototype.request = request;
Vue.component('NewProject',NewProject)
Vue.component('NewIteration',NewIteration)
Vue.component('CutPage',CutPage)
Vue.component('Import',Import)
 
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
console.log("123")
