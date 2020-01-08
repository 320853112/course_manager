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
}
