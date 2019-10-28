package link.yangxin.my.commons.controller;

import link.yangxin.my.commons.R;

/**
 * @author yangxin
 * @date 2019/10/20
 */
public class BaseController {

    protected <T> R<T> success(T t) {
        return R.success(t);
    }

    protected <T> R<T> success() {
        return R.success();
    }


    protected <T> R<T> fail(String message) {
        return R.fail(message);
    }

    protected <T> R<T> fail(int code, String message) {
        return R.fail(code, message);
    }

}