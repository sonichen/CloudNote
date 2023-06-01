const mutations = {
  setNoteContent (state, content) {
    state.noteContent = JSON.parse(JSON.stringify(content))
  },
  updateCallAPI (state) {
    state.callAPI++
  },
  updateTag (state) {
    state.changeTags++
  },
  updateMenu (state) {
    state.changeMenu++
  }
}

export default mutations
