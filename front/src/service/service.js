import BaseService from './services/BaseService';
import LoginService from './services/LoginService';
import adminService from './services/adminService';
import teacherService from './services/teacherService';
import studentService from './services/studentService';
import courseService from './services/courseService';

export class Service {
  constructor() {
    this.base = new BaseService();
    this.login = new LoginService();
    this.admin = new adminService();
    this.teacher = new teacherService();
    this.student = new studentService();
    this.course = new courseService();
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
