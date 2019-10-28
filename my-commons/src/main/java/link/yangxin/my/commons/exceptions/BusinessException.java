package link.yangxin.my.commons.exceptions;

/**
 * @author yangxin
 * @date 2019/10/28
 */
public class BusinessException extends RuntimeException {

    private Integer code;

    public BusinessException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message) {
        super(message);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}