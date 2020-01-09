import BaseService from './BaseService';

export default class TeacherService extends BaseService {
  // 获取所有教师信息
  async getTeacher(data) {
    const url = this.API.teacher.getTeacher;
    const result = await this.get(url, data);
    return result;
  }
}