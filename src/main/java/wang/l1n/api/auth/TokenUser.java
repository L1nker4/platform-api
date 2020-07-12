package wang.l1n.api.auth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/7/12 20:11
 * @description： 用户缓存DTO
 */

@Data
public class TokenUser implements Serializable {

    @ApiModelProperty("用户id")
    private String id;

    @ApiModelProperty("用户昵称")
    private String nickname;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("学号")
    private String studentNumber;

    @ApiModelProperty("学院")
    private String department;

    @ApiModelProperty("班级")
    private String classname;
}
