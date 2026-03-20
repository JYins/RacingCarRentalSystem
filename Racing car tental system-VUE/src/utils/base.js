const base = {
  get() {
    return {
      url: 'http://localhost:9341/',
      name: '',
      indexUrl: 'http://localhost:9341/front/index.html'
    }
  },
  getProjectName() {
    return {
      projectName: 'Racing Car Rental'
    }
  }
}

export default base
