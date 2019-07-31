// vue.config.js
module.exports = {
    devServer: {
        proxy: {
            "/auth":{
                target: 'http://10.10.12.232:9001',
                changeOrigin: true
            }
        },
    }
}
