import Axios from 'axios'

export default {
  state: {
    place_job: []
  },
  mutations: {
    PLACE_JOB (state, payLoad) {
      state.place_job = payLoad
    },
    changeState (state) {
      state.place_job = []
    }
  },
  getters: {
    getplace_job: state => state.place_job
  },
  actions: {
    async GET_PLACE_JOB ({ commit, getters }) {
      if (getters.getplace_job.length === 0) {
        // commit('SET_PROCESSING',true)
        await Axios.get('http://10.10.12.232:9001/api/getAllPlaceJob')
          .then(resp => {
            commit('PLACE_JOB', resp.data)
            // commit('SET_PROCESSING',false)
          })
          // eslint-disable-next-line handle-callback-err
          .catch(function (error) {
            // commit('SET_PROCESSING',false)
            // commit('SET_ERROR',error.message)
          })
      }
    }
  }
}
