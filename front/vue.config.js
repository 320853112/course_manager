module.exports = {
  lintOnSave: true,
  publicPath: process.env.NODE_ENV === 'production' ? '././' : '/',
  outputDir: '../server/src/main/static',
  devServer: {
    port: 8080,
    proxy: {
      '/course_manage': {
        target: 'http://47.100.60.220:8888',
        changeOrigin: true,
        ws: false,
        pathRewrite: {
          '^/course_manage': '/course_manage',
        },
        secure: false,
      },
    },
    hot: true,
    overlay: {
      warnings: false,
      errors: false,
    },
  },
}
