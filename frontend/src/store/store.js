import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  // State提供了唯一的公共数据源，所有共享的数据都要统一放在Store的State中进行存储
  state: {
    teacherId: '',
    schoolId: '',
    addmissionsId: ''
  },
  // Mutation用于变更Store中的数据
  mutations: {
    setteacherId (state, ID) {
      state.schoolId = ID
    },
    setschoolId (state, ID) {
      state.schoolId = ID
    },
    setaddmissionsId (state, ID) {
      state.addmissionsId = ID
    }
  },
  // Action用于处理异步任务
  actions: {

  },
  // Getter用于对Store中的数据进行加工处理形成新的数据，类似Vue中的计算属性
  getters: {
    getNickName: state => {
      return state.profile.nickName
    },
    getProfile: state => {
      return state.profile
    },
    isAuthenticated: (state, getters) => {
      return !!getters.getProfile.email
    }
  }
})
