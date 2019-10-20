package link.yangxin.productserver.controller;

import link.yangxin.my.commons.R;
import link.yangxin.my.commons.controller.BaseController;

/**
 * @author yangxin
 * @date 2019/10/20
 */
public class ProductController extends BaseController {

    public R<String> test(){
        return success("success");
    }

}