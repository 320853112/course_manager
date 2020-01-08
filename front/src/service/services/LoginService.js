import BaseService from './BaseService';
export default class LoginService extends BaseService {
  // 管理员登录
  async adminLogin(data, headers = {}, ) {
    const url = this.API.login.adminLogin;
    const transformRequest = [function (data) {
      data = JSON.stringify(data);
      return data;
    }];
    const result = await this.post(url, data, headers, {}, transformRequest);
    return result;
  }

  // 教师登录

  // 学生登录
  async stuLogin(data, headers = {}, ) {
    const url = this.API.login.stuLogin;
    const transformRequest = [function (data) {
      data = JSON.stringify(data);
      return data;
    }];
    const result = await this.post(url, data, headers, {}, transformRequest);
    return result;
  }

  // 获取用户角色
  async getUserRole(data) {
    const url = this.API.login.getUserRole;
    const result = await this.get(url, data);
    return result;
  }
}
