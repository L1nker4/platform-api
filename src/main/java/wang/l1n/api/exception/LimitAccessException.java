package wang.l1n.api.exception;

/**
 * @author     ：L1nker4
 * @date       ： 创建于  2020/1/4 14:20
 * @description： 限流异常类
 */
public class LimitAccessException extends Exception {

    private static final long serialVersionUID = -3608667856397125671L;

    public LimitAccessException(String message) {
        super(message);
    }
}