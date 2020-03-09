const getters = {
  token: state => state.user.token,
  username: state => state.user.username,
  realname: state => state.user.realname,
  email: state => state.user.email,
  phoneNumber: state => state.user.phoneNumber,
  profilePhoto: state => state.user.profilePhoto,
  requestLists: state => state.user.requestLists
}
export default getters
