import Axios from "axios";

export const HTTP = Axios.create({
    baseURL:process.env.VUE_APP_API_URL // 'http://10.10.12.232:9001/'
})

HTTP.interceptors.request.use(
    (config) => {
        config.headers['Authorization'] = (localStorage.getItem('token')) ? 'Bearer_' + localStorage.getItem('token') : ''
        return config;
    },

    (error) => {
        return Promise.reject(error);
    }
);

export default HTTP;

// if (localStorage.getItem('token')) {
//     HTTP.defaults.headers.common['Authorization'] = 'Bearer_' + localStorage.getItem('token')
// } else {
//     HTTP.defaults.headers.common['Authorization'] = ''
// }



