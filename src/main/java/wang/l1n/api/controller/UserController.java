package wang.l1n.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wang.l1n.api.entity.common.CommonResult;
import wang.l1n.api.entity.request.LoginUserRequest;
import wang.l1n.api.entity.request.RegisterUserRequest;
import wang.l1n.api.service.IUserService;

import javax.validation.Valid;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/7/12 16:48
 * @description： 用户接口
 */

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("login")
    public CommonResult login(@RequestBody @Valid LoginUserRequest request, BindingResult result) throws Exception {
        if (result.hasErrors()){
            for (ObjectError error : result.getAllErrors()){
                return new CommonResult().failed(error.getDefaultMessage());
            }
        }
        return userService.login(request);
    }

    @GetMapping("test")
    public String test(){
        return "hello";
    }

    @PostMapping
    public void registerUser(RegisterUserRequest request, BindingResult bindingResult){

    }

}
