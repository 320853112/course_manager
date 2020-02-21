import BaseService from './baseService';

export default class StudentService extends BaseService {
  // 获取所有学生信息
  async getStuInfo(data) {
    const url = this.API.student.getStuInfo;
    const result = await this.get(url, data);
    return result;
  }

  // 删除学生信息
  async deleteStuInfo(data = {}) {
    const url = this.API.student.deleteStuInfo;
    const result = await this.post(url, {}, {}, data);
    return result;
  }
}