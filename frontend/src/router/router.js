import Vue from 'vue'
import VueRouter from 'vue-router'
import Persons from '../pages/Persons.vue'
import Folder from '../pages/Folder.vue'
import Find from '../pages/Find.vue'
import OsnPerson from '../pages/OsnPerson.vue'
import uploadFiles from '../pages/uploadFiles.vue'
import Login from '../pages/Login.vue'
import Index from '../pages/Index.vue'

Vue.use(VueRouter)

const authLogin = () => {
  let token = localStorage.getItem('token')
  return token !== undefined && token !== null
}

const router = new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/',
      component: Index,
      name: 'home',
      meta: { auth: true }
    },
    {
      path: '/persons',
      component: Persons,
      meta: { auth: true }
    },
    {
      path: '/folder',
      component: Folder,
      meta: { auth: true }
    },
    {
      path: '/find',
      component: Find,
      name: 'find',
      meta: { auth: true }
    },
    {
      path: '/message/edit/:id',
      component: OsnPerson,
      // props: true,
      name: 'OsnPerson',
      meta: { auth: true }
    },
    {
      path: '/message/add',
      component: OsnPerson,
      name: 'addPerson',
      meta: { auth: true }
    },
    {
      path: '/uploadFiles',
      component: uploadFiles,
      name: 'uploadFiles',
      meta: { auth: true }
    },
    { path: '/auth', component: Login, meta: { auth: false } },
    { path: '*', redirect: '/' }
  ]
})

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.auth)) {
    if (!authLogin()) {
      next({
        path: '/auth',
        query: { redirect: to.fullPath }
      })
    } else {
      next()
    }
  } else {
    next()
  }
})

export default router
