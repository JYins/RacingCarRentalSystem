import Vue from 'vue'
import VueRouter from 'vue-router'

import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import Pay from '@/views/pay'
import Register from '@/views/register'
import Center from '@/views/center'
import CarStorage from '@/views/modules/carStorage/list'
import CarReturn from '@/views/modules/carReturn/list'
import DispenseArea from '@/views/modules/dispenseArea/list'
import UseOrder from '@/views/modules/useOrder/list'
import User from '@/views/modules/user/list'
import CarInfo from '@/views/modules/carInfo/list'
import CarDispense from '@/views/modules/carDispense/list'
import Admin from '@/views/modules/admin/list'

Vue.use(VueRouter)

const routes = [
  {
    path: '/index',
    component: Index,
    meta: { title: 'Console' },
    children: [
      {
        path: '',
        name: 'dashboard-home',
        component: Home,
        meta: { title: 'Dashboard' }
      },
      {
        path: '/updatePassword',
        name: 'change-password',
        component: UpdatePassword,
        meta: { title: 'Change Password' }
      },
      {
        path: '/pay',
        name: 'payment',
        component: Pay,
        meta: { title: 'Payment' }
      },
      {
        path: '/center',
        name: 'profile',
        component: Center,
        meta: { title: 'Profile' }
      },
      {
        path: '/carStorage',
        name: 'car-storage',
        component: CarStorage,
        meta: { title: 'Storage & Service' }
      },
      {
        path: '/carReturn',
        name: 'car-returns',
        component: CarReturn,
        meta: { title: 'Return Reviews' }
      },
      {
        path: '/dispenseArea',
        name: 'pickup-locations',
        component: DispenseArea,
        meta: { title: 'Pickup Locations' }
      },
      {
        path: '/useOrder',
        name: 'rental-orders',
        component: UseOrder,
        meta: { title: 'Rental Orders' }
      },
      {
        path: '/user',
        name: 'customer-accounts',
        component: User,
        meta: { title: 'Customer Accounts' }
      },
      {
        path: '/carInfo',
        name: 'fleet-catalog',
        component: CarInfo,
        meta: { title: 'Fleet Catalog' }
      },
      {
        path: '/carDispense',
        name: 'active-fleet',
        component: CarDispense,
        meta: { title: 'Active Fleet' }
      },
      {
        path: '/admin',
        name: 'admin-access',
        component: Admin,
        meta: { title: 'Admin Access' }
      }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: { title: 'Sign In' }
  },
  {
    path: '/register',
    name: 'register',
    component: Register,
    meta: { title: 'Register' }
  },
  {
    path: '/',
    redirect: '/index'
  },
  {
    path: '*',
    component: NotFound,
    meta: { title: 'Not Found' }
  }
]

const router = new VueRouter({
  mode: 'hash',
  routes
})

router.afterEach(to => {
  const pageTitle = to.meta && to.meta.title ? to.meta.title : 'Console'
  document.title = `${pageTitle} | Racing Car Rental`
})

export default router
