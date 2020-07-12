package wang.l1n.api.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import wang.l1n.api.entity.common.CommonResult;
import wang.l1n.api.exception.ForestException;
import wang.l1n.api.exception.LimitAccessException;
import wang.l1n.api.exception.RedisConnectException;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import java.util.List;
import java.util.Set;

/**
 * @author     ：L1nker4
 * @date       ： 创建于  2020/1/13 20:22
 * @description：    全局异常处理类
 */
@Slf4j
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ForestException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult handleParamsInvalidException(ForestException e) {
        log.error("系统错误：{}", e.getMessage());
        return new CommonResult().failed(e.getMessage());
    }


    /**
     * 统一处理请求参数校验(实体对象传参)
     *
     * @param e BindException
     * @return CommonResult
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult validExceptionHandler(BindException e) {
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            message.append(error.getField()).append(error.getDefaultMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return new CommonResult().validateFailed(message.toString());

    }

    /**
     *
     * @param e LimitAccessException
     * @return CommonResult
     */
    @ExceptionHandler(value = LimitAccessException.class)
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public CommonResult handleLimitAccessException(LimitAccessException e) {
        log.warn(e.getMessage());
        return new CommonResult().failed(e.getMessage());
    }

    /**
     * 处理redis连接异常
     * @param e RedisConnectException
     * @return CommonResult
     */
    @ExceptionHandler(value = RedisConnectException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult handleRedisConnectException(RedisConnectException e){
        log.error(e.getMessage());
        return new CommonResult().failed(e.getMessage());
    }


    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public CommonResult handleUnauthorizedException(Exception e) {
        log.error("权限不足，{}", e.getMessage());
        return new CommonResult().failed(e.getMessage());
    }
}
