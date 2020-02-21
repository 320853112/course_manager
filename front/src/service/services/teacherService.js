import BaseService from './baseService';

export default class TeacherService extends BaseService {
  // 获取所有教师信息
  async getTeacher(data) {
    const url = this.API.teacher.getTeacher;
    const result = await this.get(url, data);
    return result;
  }

  // 教师获取自己所授课程
  async getTeacherCourse(data) {
    const url = this.API.teacher.getTeacherCourse;
    const result = await this.get(url, data);
    return result;
  }

  // 新增教师信息
  async insertTeacher(data = {}) {
    const url = this.API.teacher.insertTeacher;
    const result = await this.post(url, {}, {}, data);
    return result;
  }

  // 删除教师信息
  async deleteTeacher(data = {}) {
    const url = this.API.teacher.deleteTeacher;
    const result = await this.post(url, {}, {}, data);
    return result;
  }

  // 编辑教师信息
  async updateTeacher(data = {}) {
    const url = this.API.teacher.updateTeacher;
    const result = await this.post(url, {}, {}, data);
    return result;
  }
}