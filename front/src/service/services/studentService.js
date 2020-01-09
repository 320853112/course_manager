import BaseService from './BaseService';

export default class StudentService extends BaseService {
  // 获取所有学生信息
  async getStuInfo(data) {
    const url = this.API.student.getStuInfo;
    const result = await this.get(url, data);
    return result;
  }
}