import Vue from 'vue'
import App from './app.vue'
import router from './router'
import axios from 'axios'

Vue.config.productionTip = false
//Vue.prototype.xxx可以理解为Vue组件的全局变量，可以在任意Vue组件中使用this.xxx来获取这个值($是代表全局属性的一个约定)
Vue.prototype.$ajax = axios

//axios拦截器
//目前该拦截器只是用来打印日志，后面还会扩展，比如登录的拦截等
axios.interceptors.request.use(function (config) {
  console.log("请求：",config);
  return config;
},error => {});
axios.interceptors.response.use(function (response) {
  console.log("响应：",response);
  return response;
},error => {});

//把app.vue的template部分渲染到#app（index.html）
new Vue({
  router,
  render: h => h(App),
}).$mount('#app');

//当前是开发环境还是生产环境
console.log("环境：",process.env.NODE_ENV); //代码中通过process.env.XXX使用环境变量
