package link.yangxin.my.commons;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author yangxin
 * @date 2019/10/20
 */
@Data
@Accessors(chain = true)
public class R<T> implements Serializable {

    private int code = 0;

    private String message;

    private boolean success;

    private T data;

    public static <T> R<T> success(T t) {
        return new R<T>().setSuccess(true).setData(t);
    }

    public static <T> R<T> success() {
        return new R<T>().setSuccess(true);
    }

    public static <T> R<T> fail(String message) {
        return new R<T>().setSuccess(false).setMessage(message);
    }

    public static <T> R<T> fail(int code, String message) {
        return new R<T>().setSuccess(false).setMessage(message).setCode(code);
    }

}