import _Vue from 'vue';
import axios from 'axios';
import qs from 'qs';
import router from '../router/index';

const vueInstance = new _Vue();

const queryStringFormat = data => qs.stringify(data, {
  indices: false
});
const axiosConfig = {
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded; charset-utf-8',
    // 'Content-Type': 'application/json; charset-utf-8',
  },
  transformRequest: [queryStringFormat],
};

const _axios = axios.create(axiosConfig);

_axios.interceptors.response.use(
  // Do something with response data
  (response) => {
    if (response.returnCode === '401') {
      router.push('/login');
    }
    return response;
  }
);

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

_Vue.use(Plugin);

export default Plugin;
