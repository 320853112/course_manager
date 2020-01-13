import BaseService from './BaseService';

export default class TeacherService extends BaseService {
  // 获取所有教师信息
  async getTeacher(data) {
    const url = this.API.teacher.getTeacher;
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
}