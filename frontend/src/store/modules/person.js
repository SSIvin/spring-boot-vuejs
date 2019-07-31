import Axios from 'axios'

export default {
  state: {
    profile: {
      name: 'Sergey'
    },
    listPersonOne: {
      f: '',
      i: '',
      o: '',
      birthday: '',
      id: 0,
      placeJob: null
    },
    modals: {
      dialogSignUp: false,
      dialogSignIn: false
    }
  },
  mutations: {
    FillPersonMutation (state, payLoad) {
      state.listPersonOne.f = payLoad.f
      state.listPersonOne.i = payLoad.i
      state.listPersonOne.o = payLoad.o
      state.listPersonOne.birthday = payLoad.birthday
      state.listPersonOne.id = payLoad.id
      state.listPersonOne.placeJob = payLoad.placeJob
    },
    updatePersonMutation (state, person) {

    },

    dialogShow (state, { name, show }) {
      state.modals[name] = show
    },

    deletePersonMutation (state, index) {
      state.listPersons.splice(index, 1)
    },
    CLEAR_PERSON (state) {
      state.listPersonOne.f = ''
      state.listPersonOne.i = ''
      state.listPersonOne.o = ''
      state.listPersonOne.birthday = ''
      state.listPersonOne.id = 0
      state.listPersonOne.placeJob = null
      // for (var member in state.listPersonOne) {
      //     cleanData() (state.listPersonOne[member])
      // }
    }
  },
  getters: {
    getprofile: state => state.profile,
    getPersonOneMass (state) {
      return (id) => {
        return state.listPersons.find(
          (item) => {
            return item.id === id
          }
        )
      }
    },
    getPersonOne (state) {
      return state.listPersonOne
    },
    testOne (state, getters) {
      return (id) => {
        return getters.getPersonOne(id)
      }
    }
  },
  actions: {
    async updateMessageAction ({ commit }, data) {
      await Axios.put('http://10.10.12.232:9001/api/update/' + data.id, data)
        .then(resp => {
          console.log('Save ok')
        })
        .catch(function (error) {
          console.log(error)
        })
    },
    async CreateMessageAction ({ commit }, data) {
      await Axios.post('http://10.10.12.232:9001/api/add', data)
        .then(resp => {
          console.log('create ok')
        })
        .catch(function (error) {
          console.log(error)
        })
    },
    async deletePersonAction ({ commit }, data) {
      await Axios.delete('http://10.10.12.232:9001/api/delete/' + data.item.id)
        .then(resp => {
          commit('deletePersonMutation', data.login)
          console.log('delete ok')
        })
        .catch(function (error) {
          console.log(error)
        })
    },
    async GET_personOneAction ({ commit }, data) {
      // commit('SET_PROCESSING',true)
      await Axios.post('http://10.10.12.232:9001/api/findOne', Object(data))
        .then(resp => {
          // commit('clearPersonMutation');
          if (resp.data) {
            commit('FillPersonMutation', resp.data)
            commit('SET_PROCESSING', false)
          }
        })
        .catch(function (error) {
          commit('SET_PROCESSING', false)
          commit('SET_ERROR', error.message)
        })
    },
    async GET_FILE ({ commit }) {
      await Axios.get('http://10.10.12.232:9001/api/download/customers.xlsx', { responseType: 'blob' })
        .then((resp) => {
          const url = window.URL.createObjectURL(new Blob([resp.data]))
          const link = document.createElement('a')
          link.href = url
          link.setAttribute('download', 'file.xlsx')
          document.body.appendChild(link)
          link.click()
        })
    }
  }
}
// Vue.http.get('/message/persons')
//     .then(resp => {
//
//     })
//
