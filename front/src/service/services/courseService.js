import BaseService from './BaseService';

export default class courseService extends BaseService {
  // 获取所有课程信息
  async getCourse(data) {
    const url = this.API.course.getCourse;
    const result = await this.get(url, data);
    return result;
  }
}