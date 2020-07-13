package wang.l1n.api.config;

import lombok.AllArgsConstructor;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wang.l1n.api.properties.WxMpProperties;

import java.util.stream.Collectors;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/7/13 20:43
 * @description： Weixin 配置类
 */

@Configuration
public class WxMpConfiguration {

    @Autowired
    private WxMpProperties wxMpProperties;

    @Bean
    public WxMpService wxMpService() {
        WxMpService service = new WxMpServiceImpl();
        WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
        config.setToken(wxMpProperties.getToken());
        config.setAesKey(wxMpProperties.getAesKey());
        config.setAppId(wxMpProperties.getAppId());
        config.setSecret(wxMpProperties.getSecret());
        service.setWxMpConfigStorage(config);
        return service;
    }
}
