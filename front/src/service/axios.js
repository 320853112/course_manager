import Vue from 'vue';
import axios from 'axios';
import qs from 'qs';
import router from '../router/index.js';

const queryStringFormat = data => qs.stringify(data);

const config = {
  headers: {
    // 'Content-Type': 'application/x-www-form-urlencoded; charset-utf-8',
    'Content-Type': 'application/json',
  },
  transformRequest: [queryStringFormat],
};
// eslint-disable-next-line no-underscore-dangle
const _axios = axios.create(config);

_axios.interceptors.request.use(
  config => config,
  error => Promise.reject(error),
);

_axios.interceptors.response.use(
  // response => response,
  (response) => {
    if (response.data.returnCode && response.data.returnCode === '401') {
      router.push('/login');
    }
    return response;
  },
  error => error.response,
);

// eslint-disable-next-line func-names
Plugin.install = function (Vue, options) {
  Vue.axios = _axios;
  window.axios = _axios;
  Object.defineProperties(Vue.prototype, {
    axios: {
      get() {
        return _axios;
      },
    },
    $axios: {
      get() {
        return _axios;
      },
    },
  });
};

Vue.use(Plugin);

export default _axios;
