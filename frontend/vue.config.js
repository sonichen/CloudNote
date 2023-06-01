const StyleLintPlugin = require('stylelint-webpack-plugin')
const DeadCodePlugin = require('webpack-deadcode-plugin')
const path = require('path')

function resolve (dir) {
  return path.join(__dirname, dir)
}
module.exports = {
  devServer: {
    disableHostCheck: true,
    overlay: {
      warnings: false,
      errors: true
    },
    proxy: {
      '/trans': { target: 'https://fanyi-api.baidu.com/api', changeOrigin: true, ws: true },
      '/note': {
        target: 'http://mycloudnote.gz2vip.91tunnel.com',
        changeOrigin: true,
        ws: true,
        pathRewrite: {
          '^/note': ''
        }
      }
    }
  },
  css: {
    // Enable CSS source maps.
    sourceMap: true,
    loaderOptions: {
      sass: {
        implementation: require('sass') // This line must in sass option
      }
    }
  },
  configureWebpack: {
    // Fast source maps in dev
    name: 'note-c-web',
    plugins: [
      new StyleLintPlugin({
        files: ['src/**/*.{vue,htm,html,css,sss,less,scss,sass}']
      }),
      new DeadCodePlugin({
        patterns: [
          'src/**/*.*'
        ]
      })
    ],
    resolve: {
      alias: {
        '@': resolve('/src'),
        '@src': resolve('src/'),
        '@api': resolve('src/api'),
        '@assets': resolve('src/assets/'),
        '@components': resolve('src/components/'),
        '@router': resolve('src/router'),
        '@views': resolve('src/router/views'),
        '@layouts': resolve('src/router/layouts'),
        '@pages': resolve('src/pages/'),
        '@store': resolve('src/store'),
        '@design': resolve('src/design/index.scss'),
        '@utils': resolve('src/utils/'),
        '@customStyle': resolve('src/assets/sass/custom-variable.scss'),
        '@packages': resolve('src/pages/form_design/packages')
      }
    }
  }
}
