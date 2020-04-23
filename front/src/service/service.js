import BaseService from './services/BaseService';
import LoginService from './services/LoginService';
import AdminService from './services/adminService';
import TeacherService from './services/teacherService';
import StudentService from './services/studentService';
import CourseService from './services/courseService';

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
