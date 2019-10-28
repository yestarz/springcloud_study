package link.yangxin.order.controller;

import link.yangxin.my.commons.R;
import link.yangxin.my.commons.controller.BaseController;
import link.yangxin.order.common.OrderCreateRequest;
import link.yangxin.order.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yangxin
 * @date 2019/10/28
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Resource
    private OrderService orderService;

    @PostMapping("/create")
    public R<String> create(@RequestBody OrderCreateRequest request) {
        String s = orderService.create(request);
        return success(s);
    }

}