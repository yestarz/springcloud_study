package link.yangxin.apigateway.exception;

/**
 * @author yangxin
 * @date 2019/11/2
 */
public class RateLimitException extends RuntimeException {

    public RateLimitException() {
    }

    public RateLimitException(String message) {
        super(message);
    }
}