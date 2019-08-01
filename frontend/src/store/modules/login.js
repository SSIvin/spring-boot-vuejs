import {HTTP} from '../http-common'

export default {
    state: {
        // place_job: []
    },
    mutations: {
        // PLACE_JOB(state, payLoad) {
        //     state.place_job = payLoad
        // },
        // changeState(state) {
        //     state.place_job = []
        // }
    },
    getters: {
        // getplace_job: state => state.place_job
    },
    actions: {
        async login({commit}, {username,password}) {
            //await  Axios.post('http://10.10.12.232:9001/auth',
            await  HTTP.post('auth',
                {
                    username: username,
                    password: password

                },
                {
                    headers: {
                        'Content-Type': 'application/json',
                    }
                })
                .then(resp => {
                    if (resp.status === 200) {

                        localStorage.setItem('token', resp.data.token)
                        // console.log(localStorage.getItem('token'))
                        //HTTP.defaults.headers.common['Authorization'] = 'Bearer_' + localStorage.getItem('token')
                        //HTTP.headers.common['Authorization'] = 'Bearer_'+ resp.data.token
                        //Axios.defaults.headers.common['Authorization'] = 'Bearer_'+ resp.data.token
                            //console.log(resp.data)
                        //router.push({name: '/find'});

                        // resp.json().then((data) => {
                        //     // console.log(data.token, this.userName)
                        //     sessionStorage.setItem('token', data.token)
                        //     // sessionStorage.setItem('user_name', this.userName)
                        //     // console.log(this.$router)
                        //     this.$router.replace('/')
                        // })
                    }
                })
                .catch(function (error) {
                    console.log('error afsdfsd')
                    // commit('SET_PROCESSING',false)
                    // commit('SET_ERROR',error.message)
                });

        }
    }
}
