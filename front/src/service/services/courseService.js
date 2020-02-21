import BaseService from './baseService';

export default class CourseService extends BaseService {
  // 获取所有课程信息
  async getCourse(data) {
    const url = this.API.course.getCourse;
    const result = await this.get(url, data);
    return result;
  }

  // 学生已选课程
  async getStuCourse(data) {
    const url = this.API.course.getStuCourse;
    const result = await this.get(url, data);
    return result;
  }

  // 新增课程
  async insertCourse(data = {}) {
    const url = this.API.course.insertCourse;
    const result = await this.post(url, {}, {}, data);
    return result;
  }

  // 删除课程
  async deleteCourse(data = {}) {
    const url = this.API.course.deleteCourse;
    const result = await this.post(url, {}, {}, data);
    return result;
  }

  // 编辑课程
  async updateCourse(data = {}) {
    const url = this.API.course.updateCourse;
    const result = await this.post(url, {}, {}, data);
    return result;
  }
}