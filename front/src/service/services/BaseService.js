import qs from 'qs';
import axios from '../axios';
import API from '../api';

const queryStringFormat = data => qs.stringify(data, { indices: false });

export default class {
  constructor() {
    this.API = API;
  }

  // eslint-disable-next-line class-methods-use-this
  async get(url, data = {}) {
    let result = await axios.get(url, { params: data, headers: {} });
    try {
      result = result.data;
    } catch (error) {
      result.data = {
        result: false,
        message: error.toString(),
      };
    }
    return result;
  }

  // eslint-disable-next-line class-methods-use-this
  async post(url, data = {}, headers = {}, params = {}, transformRequest) {
    let result = {};
    try {
      result = await axios(
        {
          method: 'POST',
          url,
          data,
          params,
          headers,
          transformRequest,
        },
      );
      result = result.data;
    } catch (err) {
      result = {
        success: false,
        message: err.toString(),
        returnCode: err.status,
      };
    }
    return result;
  }
}
