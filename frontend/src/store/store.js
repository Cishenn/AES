import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  // State提供了唯一的公共数据源，所有共享的数据都要统一放在Store的State中进行存储
  state: {
    teacherId: '',
    schoolId: '',
    addmissionsId: '',
    eduId: '',
    SubordinateschoolsId: '',
    errorMessage: [],
    schoolName: ''
  },
  // Mutation用于变更Store中的数据
  mutations: {
    setteacherId (state, ID) {
      state.teacherId = ID
    },
    setschoolId (state, ID) {
      state.schoolId = ID
    },
    setaddmissionsId (state, ID) {
      state.addmissionsId = ID
    },
    seteduId (state, ID) {
      state.eduId = ID
    },
    setsubordinatesschoolsId (state, ID) {
      state.SubordinateschoolsId = ID
    },
    seterrorMessage (state, content) {
      state.errorMessage = content
    },
    clearerror (state) {
      state.errorMessage = null
    },
    setschoolname (state, name) {
      state.schoolName = name
    }
  },
  // Action用于处理异步任务
  actions: {

  },
  // Getter用于对Store中的数据进行加工处理形成新的数据，类似Vue中的计算属性
  getters: {
    getTeacherId (state) {
      return state.teacherId
    },
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
