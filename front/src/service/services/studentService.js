import BaseService from './BaseService';

export default class studentService extends BaseService {
  // 返回所有学生信息
  async getStuInfo(data) {
    const url = this.API.student.getStuInfo;
    const result = await this.get(url, data);
    return result;
  }
}