package wang.l1n.api;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/7/13 20:51
 * @description：
 */

@SpringBootTest
@Slf4j
public class WxTest {

    @Autowired
    private WxMpService wxMpService;

    @Test
    public void test01(){
        try {
            WxMpUserList wxUserList = wxMpService.getUserService().userList(null);
            System.out.println(wxUserList);
        }catch (Exception e){
            log.error(e.getMessage());
        }

    }
}
