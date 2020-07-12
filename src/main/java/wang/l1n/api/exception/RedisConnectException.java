package wang.l1n.api.exception;

/**
 * @author     ：L1nker4
 * @date       ： 创建于  2020/1/4 18:38
 * @description： Redis 连接异常类
 */
public class RedisConnectException extends Exception {

    private static final long serialVersionUID = 1639374111871115063L;

    public RedisConnectException(String message) {
        super(message);
    }
}
