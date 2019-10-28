package link.yangxin.productserver.config;

import link.yangxin.my.commons.R;
import link.yangxin.my.commons.exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author yangxin
 * @date 2019/10/28
 */
@RestControllerAdvice
@Slf4j
public class MyExceptionHandler {

    @ExceptionHandler
    public <T> R<T> handle(Exception e) {
        log.error(e.getMessage(), e);
        if (e instanceof BusinessException) {
            R<T> r = R.fail(e.getMessage());
            if (((BusinessException) e).getCode() != null) {
                r.setCode(((BusinessException) e).getCode());
            }
            return r;
        }
        R<T> r = R.fail(e.getMessage());
        r.setCode(500);
        return r;
    }

}