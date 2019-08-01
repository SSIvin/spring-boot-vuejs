import {HTTP} from "../http-common";

export default {
  state: {
    listPersons: []
  },
  mutations: {
    SET_Persons (state, payLoad) {
      state.listPersons = payLoad
    }
  },
  getters: {
    listPersonsCount: state => state.listPersons.length
  },
  actions: {
    async GET_personAction ({ commit }, data) {
      commit('SET_PROCESSING', true)
      console.log(localStorage.getItem('token'))
      await HTTP.post('api/find', data)
        .then(resp => {
          commit('SET_Persons', resp.data)
          commit('SET_PROCESSING', false)
        })
        .catch(function (error) {
          commit('SET_PROCESSING', false)
          commit('SET_ERROR', error.message)
        })
    }
  }

}
