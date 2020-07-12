package wang.l1n.api.service;

import wang.l1n.api.entity.common.CommonResult;
import wang.l1n.api.entity.request.LoginUserRequest;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/7/12 16:50
 * @description： 用户服务接口类
 */
public interface IUserService {


    CommonResult login(LoginUserRequest request) throws Exception;
}
