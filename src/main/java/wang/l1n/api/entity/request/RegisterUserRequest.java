package wang.l1n.api.entity.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/7/12 21:10
 * @description： 用户注册 请求信息实体
 */

@Data
public class RegisterUserRequest {

    @NotBlank(message = "学号不能为空")
    private String studentNumber;

    @NotBlank(message = "密码不能为空")
    private String password;

}
