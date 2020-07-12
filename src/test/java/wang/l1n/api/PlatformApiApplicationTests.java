package wang.l1n.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wang.l1n.api.exception.RedisConnectException;
import wang.l1n.api.utils.CacheUtil;

@SpringBootTest
class PlatformApiApplicationTests {

    @Autowired
    private CacheUtil cacheUtil;
    @Test
    void contextLoads() throws RedisConnectException {
        cacheUtil.set("hello", "world");
    }

}
