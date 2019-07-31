export default {
  state: {
    processing: false,
    error: null
  },
  mutations: {
    SET_PROCESSING (state, payLoad) {
      state.processing = payLoad
    },
    SET_ERROR (state, payLoad) {
      state.error = payLoad
    },
    CLEAR_ERROR (state) {
      state.error = null
    }
  },
  getters: {
    getProcessing: (state) => {
      return state.processing
    },
    getError: (state) => {
      state.error
    }
  }

}
