package wang.l1n.api.entity.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wang.l1n.api.entity.User;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/7/12 21:45
 * @description： 登录 返回信息实体类
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginResponse extends User {

    private String token;
}
