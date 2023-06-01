import Main from '@/layout/main'

export default [{
  path: '/',
  redirect: 'read-mode', // 默认为第一个-阅读模式
  component: Main,

  children: [{
    path: 'read-mode',
    component: () =>
      import('@/pages/ReadMode/ReadMode')
  },
  {
    path: 'rich-text',
    component: () =>
      import('@/pages/RichText/RichText')
  },
  {
    path: 'mark-down',
    component: () =>
      import('@/pages/MarkDown/MarkDown')
  },
  {
    path: 'manipulate',
    component: () =>
      import('@/pages/Manipulation/index')
  },
  {
    path: 'collection',
    component: () =>
      import('@/pages/Collection/index')
  },
  {
    path: 'bin',
    component: () =>
      import('@/pages/Bin/index')
  },
  {
    path: 'version-manage',
    component: () =>
      import('@/pages/VersionManage/index')
  }
  ]
}, {
  path: '/login',
  name: 'Login',
  component: () =>
    import('@/pages/Login')
}, {
  path: '/register',
  name: 'Register',
  component: () =>
    import('@/pages/Register')
}, {
  path: '/user-info',
  name: 'UserInfo',
  component: () =>
    import('@/pages/UserSetting/index')
}, {
  path: '/account-setting',
  name: 'AccountSetting',
  component: () =>
    import('@/pages/AccountSetting/index')
},
{
  path: '/share/:id',
  name: 'Share',
  component: () =>
    import('@/pages/Share/SharePage')
}
]
