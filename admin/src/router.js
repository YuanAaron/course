import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/login.vue'
import Admin from './views/admin.vue'

Vue.use(Router);

export default new Router({
  mode: 'history', //推荐，但还可以是hash
  base: process.env.BASE_URL,
  routes: [{
    path: '*',
    redirect: "/login"
  },{
    path: '/login',
    component: Login
  },{
    path: '/admin',
    component: Admin
  }]
})
