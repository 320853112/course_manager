import BaseService from './BaseService';

export default class StudentService extends BaseService {
  // 获取所有学生信息
  async getStuInfo(data) {
    const url = this.API.student.getStuInfo;
    const result = await this.get(url, data);
    return result;
  }

  // 新增学生信息
  async insertStuInfo(data = {}) {
    const url = this.API.student.insertStuInfo;
    const result = await this.post(url, {}, {}, data);
    return result;
  }

  // 删除学生信息
  async deleteStuInfo(data = {}) {
    const url = this.API.student.deleteStuInfo;
    const result = await this.post(url, {}, {}, data);
    return result;
  }

  // 编辑学生信息
  async updateStuInfo(data = {}) {
    const url = this.API.student.updateStuInfo;
    const result = await this.post(url, {}, {}, data);
    return result;
  }
}