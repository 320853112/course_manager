import BaseService from './BaseService';

export default class LoginService extends BaseService {
  // 管理员登录
  async adminLogin(data) {
    const url = this.API.login.adminLogin;
    const result = await this.post(url, data);
    return result;
  }
}
