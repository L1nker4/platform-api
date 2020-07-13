package wang.l1n.api.service;

import wang.l1n.api.entity.common.CommonResult;
import wang.l1n.api.entity.request.LoginUserRequest;
import wang.l1n.api.entity.request.RegisterUserRequest;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/7/12 16:50
 * @description： 用户服务接口类
 */
public interface IUserService {


    /**
     * 登录接口
     * @param request
     * @return
     */
    CommonResult login(LoginUserRequest request);

    CommonResult registerUser(RegisterUserRequest request);
}
