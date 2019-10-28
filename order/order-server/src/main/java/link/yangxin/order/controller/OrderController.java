package link.yangxin.order.controller;

import link.yangxin.my.commons.R;
import link.yangxin.my.commons.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangxin
 * @date 2019/10/28
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {


    public R<Void> create(){
        return null;
    }

}