package wang.l1n.api.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.l1n.api.auth.JWTToken;
import wang.l1n.api.auth.JWTUtil;
import wang.l1n.api.dao.UserRepository;
import wang.l1n.api.entity.User;
import wang.l1n.api.entity.common.CommonResult;
import wang.l1n.api.entity.common.ConstantVariable;
import wang.l1n.api.entity.request.LoginUserRequest;
import wang.l1n.api.entity.request.RegisterUserRequest;
import wang.l1n.api.entity.response.LoginResponse;
import wang.l1n.api.exception.ForestException;
import wang.l1n.api.service.IUserService;
import wang.l1n.api.utils.CacheUtil;
import wang.l1n.api.utils.DateUtil;
import wang.l1n.api.utils.ForestUtil;
import wang.l1n.api.utils.MD5Util;

import java.time.LocalDateTime;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/7/12 16:50
 * @description： 用户服务实现类
 */

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    public static final String ERROR_MESSAGE = "用户名或密码错误";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CacheUtil cacheUtil;

    @Override
    public CommonResult login(LoginUserRequest request) {
        String username = request.getUsername();
        String password = MD5Util.encrypt(username, request.getPassword());
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new ForestException(ERROR_MESSAGE);
        }
        if (!StringUtils.equals(user.getPassword(), password)) {
            throw new ForestException(ERROR_MESSAGE);
        }
        String token = ForestUtil.encryptToken(JWTUtil.sign(username, password));
        LocalDateTime expireTime = LocalDateTime.now().plusDays(1);
        String expStr = DateUtil.formatFullTime(expireTime);
        JWTToken jwtToken = new JWTToken(token, expStr);
        try {
            cacheUtil.setex(ConstantVariable.USER_CACHE_PREFIX + username, jwtToken.getToken(), 3600 * 24L);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        LoginResponse response = new LoginResponse();
        BeanUtils.copyProperties(user, response);
        response.setToken(token);
        return new CommonResult().success(response);
    }

    @Override
    public CommonResult registerUser(RegisterUserRequest request) {
        //TODO : 注册功能
        return new CommonResult().success("yes");
    }
}
