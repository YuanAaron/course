import Vue from 'vue'
import App from './app.vue'
import router from './router'
import axios from 'axios'

Vue.config.productionTip = false
//Vue.prototype.xxx可以理解为Vue组件的全局变量，可以在任意Vue组件中使用this.xxx来获取这个值($是代表全局属性的一个约定)
Vue.prototype.$ajax = axios

//把app.vue的template部分渲染到#app（index.html）
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
