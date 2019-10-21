import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      component: () => import( '@/views/Login/Login.vue')
    },
    {
      path: '/Main',
      component: () => import( '@/views/Main/Main.vue'),
      children:[
        {
          path:'/Main/Member',
          component:() => import('@/views/Member/Member.vue')
        },
        {
          path:'/Main/ProjectKanban',
          component:() => import('@/views/ProjectKanban/ProjectKanban.vue')
        },
        {
          path:'/Main/ViewField',
          component:() => import('@/views/ViewField/ViewField.vue')
        },
        {
          path:'/Main/ViewIteration',
          component:() => import('@/views/ViewIteration/ViewIteration.vue')
        },
        {
          path:'/Main/ViewProject',
          component:() => import('@/views/ViewProject/ViewProject.vue')
        },
        {
          path:'/Main/ViewTask',
          component:() => import('@/views/ViewTask/ViewTask.vue')
        },
        {
          path:'/Main/ProjectInfo',
          name:"ProjectInfo",
          component:() => import('@/views/ProjectInfo/ProjectInfo.vue')
        },
        {
          path:'/Main/IterationTask',
          component:() => import('@/views/IterationTask/IterationTask.vue')
        },
        {
          path:'/Main/UserManagement',
          component:() => import('@/views/UserManagement/UserManagement.vue')
        },
        {
          path:'/Main/CodeView',
          component:() => import('@/views/CodeView/CodeView.vue')
        },
        {
          path:'/Main/testImport',
          component:() => import('@/views/testImport/testImport.vue')
        },
      ]
    }
  ]
})
