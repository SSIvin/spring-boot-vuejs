// vue.config.js

const path = require('path');


module.exports = {
    //Каталог, в котором при запуске vue-cli-service build будут создаваться файлы сборки для production
    outputDir: 'dist',
    //Каталог (относительно outputDir) для хранения сгенерированных статических ресурсов (js, css, img, fonts)
    assetsDir: 'static',

    // devServer: {
    //     proxy: {
    //         '/auth': {
    //             target: 'http://localhost:8098', // this configuration needs to correspond to the Spring Boot backends' application.properties server.port
    //             ws: true,
    //             changeOrigin: true
    //         }
    //     }
    // },

    chainWebpack: config => {
        config
            .plugin('copy')
            .use(require('copy-webpack-plugin')), [{
            from: path.resolve(__dirname, '../static'),
            to: 'static', ignore: ['.*']
        }]
    },
    configureWebpack: config => {
        if (process.env.NODE_ENV === 'production') {
            // изменение конфигурации для production...
        } else {
            // изменения для разработки...
        }
    }
}
