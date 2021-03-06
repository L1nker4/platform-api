package wang.l1n.api.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/7/12 21:20
 * @description： MD5工具类
 */

public class MD5Util {

    protected MD5Util(){

    }

    private static final String ALGORITH_NAME = "md5";

    private static final int HASH_ITERATIONS = 2;

    public static String encrypt(String password) {
        return new SimpleHash(ALGORITH_NAME, password, ByteSource.Util.bytes(password), HASH_ITERATIONS).toHex();
    }

    public static String encrypt(String username, String password) {
        return new SimpleHash(ALGORITH_NAME, password, ByteSource.Util.bytes(username.toLowerCase() + password),
                HASH_ITERATIONS).toHex();
    }

    public static void main(String[] args) {
        System.out.println(encrypt("l1nker4","admin"));
    }

}
