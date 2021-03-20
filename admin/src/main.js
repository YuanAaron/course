import Vue from 'vue'
import App from './App.vue'

Vue.config.productionTip = false

//把App.vue的template部分渲染到#app（index.html）
new Vue({
  render: h => h(App),
}).$mount('#app')
