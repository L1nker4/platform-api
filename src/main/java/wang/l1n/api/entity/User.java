package wang.l1n.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import wang.l1n.api.entity.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/7/12 13:18
 * @description： 用户类
 */

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "tb_user")
@ToString
public class User extends BaseEntity {

    @Column(columnDefinition = "VARCHAR(255) COMMENT '用户昵称'")
    private String nickname;

    @Column(columnDefinition = "VARCHAR(10) COMMENT '性别'")
    private String sex;

    @Column(columnDefinition = "VARCHAR(11) COMMENT '手机号码'")
    private String phone;

    @Column(columnDefinition = "VARCHAR(50) COMMENT '密码'")
    private String password;

    @Column(columnDefinition = "text COMMENT '头像'")
    private String avatar;

    @Column(columnDefinition = "VARCHAR(50) COMMENT 'openid' ")
    private String openid;

    @Column(columnDefinition = "VARCHAR(50) COMMENT 'sessionKey' ")
    private String sessionKey;

    @Column(columnDefinition = "VARCHAR(50) COMMENT 'unionid' ")
    private String unionid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(columnDefinition = "datetime(6) COMMENT '上次登录时间'")
    private Date lastLoginTime;



}
