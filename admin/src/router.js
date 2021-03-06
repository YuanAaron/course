import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/login.vue'
import Admin from './views/admin.vue'
import Welcome from './views/admin/welcome.vue'
import Category from './views/admin/category.vue'
import Course from './views/admin/course.vue'
import Chapter from './views/admin/chapter.vue'
import Section from './views/admin/section.vue'

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
    path: '/',
    name: 'admin', //添加name属性，后续做通用的sidebar激活样式方法时会用到
    component: Admin,
    children: [{
      path: 'welcome', //注意子路由不能有/
      name: 'welcome',
      component: Welcome
    },{
      path: 'business/category',
      name: 'business/category',
      component: Category
    },{
      path: 'business/course',
      name: 'business/course',
      component: Course
    },{
      path: 'business/chapter',
      name: 'business/chapter',
      component: Chapter
    },{
      path: 'business/section',
      name: 'business/section',
      component: Section
    }]
  }]
})
