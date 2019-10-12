import axios from 'axios'

class GroupService {
  private static _instance = new GroupService();

  private constructor() {}

  static get instance() {
    return this._instance;
  }

  getGroups(majorId, courseNum) {
    return new Promise((res, rej) => {
      axios.get(`/api/groups/major-id/${majorId}/course-num/${courseNum}`)
        .then(response => res(response))
        .catch(error => rej(error.response));
    });
  }

  getGroupsByMajorId(majorId) {
    return new Promise((res, rej) => {
      axios.get(`/api/groups/major-id/${majorId}`)
        .then(response => res(response.data))
        .catch(error => rej(error.response));
    });
  }

  getGroupsByMajorIdAndLocaleId(majorId, localeId) {
    return new Promise((res, rej) => {
      axios.get(`/api/groups/major-id/${majorId}/locale-id/${localeId}`)
        .then(response => res(response.data))
        .catch(error => rej(error.response));
    });
  }
}

export default GroupService.instance;