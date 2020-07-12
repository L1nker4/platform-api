package wang.l1n.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wang.l1n.api.dao.UserRepository;
import wang.l1n.api.entity.User;
import wang.l1n.api.exception.RedisConnectException;
import wang.l1n.api.utils.CacheUtil;
import wang.l1n.api.utils.MD5Util;

@SpringBootTest
class PlatformApiApplicationTests {

    @Autowired
    private CacheUtil cacheUtil;

    @Autowired
    private UserRepository userRepository;
    @Test
    void contextLoads() throws RedisConnectException {
        cacheUtil.set("hello", "world");
    }

    @Test
    void test(){
        User user = new User();
        String password = MD5Util.encrypt("l1nker4", "admin");
        user.setPhone("13912603149");
        user.setClassname("计算机1183");
        user.setUsername("l1nker4");
        user.setStudentNumber("1181301328");
        user.setSex("男");
        user.setDepartment("计算机与软件工程学院");
        user.setPassword(password);

        userRepository.save(user);
    }

}
