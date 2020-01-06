import Vue from "vue";
import axios from "axios";
import API from "../api";


export default class BaseService {
  constructor() {
    this.API = API;
  }

  async get(url, data = {}) {
    let result = await Vue.axios.get(url, {
      params: data
    });
    result = result.data;
    return result;
  }

  async post(url, data = {}, config = {}) {
    let result = {};
    try {
      result = await Vue.axios.post(url, data, config, {
        headers: {
          "Content-Type": "x-www-form-urlencoded; charset-utf-8"
          // 'Content-Type': 'application/json; charset-utf-8',
        }
      });
      result = result.data;
    } catch (err) {
      result.data = {
        result: false,
        message: err.toString()
      };
    }
    return result;
  }

  async jsonPost(url, data = {}) {
    let result = {};
    try {
      result = await axios.post(url, data, {
        headers: {
          "Content-Type": "application/json; charset-utf-8"
        }
      });
      result = result.data;
    } catch (err) {
      result.data = {
        result: false,
        message: err.toString()
      };
    }
    return result;
  }
}
