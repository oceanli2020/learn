const getters = {
  token: state => state.user.token,
  refreshToken: state => state.user.refreshToken,
  username: state => state.user.username
}
export default getters
