import Vue from 'vue'
import App from './app.vue'
import router from './router'

Vue.config.productionTip = false

//把app.vue的template部分渲染到#app（index.html）
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
