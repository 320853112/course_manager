import LoginService from './services/LoginService';
import BaseService from './services/BaseService';

export class Service {
  constructor() {
    this.base = new BaseService();
    this.login = new LoginService();
  }
}
const services = {
  install(Vue, name = '$service') {
    Object.defineProperty(
      Vue.prototype,
      name,
      {
        value: new Service()
      },
    );
  },
};

export default services;
