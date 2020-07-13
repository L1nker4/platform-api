package wang.l1n.api.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/7/13 20:04
 * @description： 公众号 properties
 */

@Data
@ConfigurationProperties(prefix = "wx.mp")
@Component
public class WxMpProperties {
    /**
     * 设置微信公众号的appid
     */
    private String appId;

    /**
     * 设置微信公众号的app secret
     */
    private String secret;

    /**
     * 设置微信公众号的token
     */
    private String token;

    /**
     * 设置微信公众号的EncodingAESKey
     */
    private String aesKey;
}
