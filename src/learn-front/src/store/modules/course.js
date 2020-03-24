const course = {
  state: {
    courseTypeId: ''
  },
  mutations: {
    SET_COURSETYPE_ID: (state, courseTypeId) => {
      state.courseTypeId = courseTypeId
    }
  }
}
export default course
