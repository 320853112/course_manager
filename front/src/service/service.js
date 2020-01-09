import BaseService from './services/BaseService';
import LoginService from './services/LoginService';
import AdminService from './services/AdminService';
import TeacherService from './services/TeacherService';
import StudentService from './services/StudentService';
import CourseService from './services/CourseService';

export class Service {
  constructor() {
    this.base = new BaseService();
    this.login = new LoginService();
    this.admin = new AdminService();
    this.teacher = new TeacherService();
    this.student = new StudentService();
    this.course = new CourseService();
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
