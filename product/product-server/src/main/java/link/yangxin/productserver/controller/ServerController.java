package link.yangxin.productserver.controller;

import link.yangxin.my.commons.controller.BaseController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangxin
 * @date 2019/11/2
 */
@RestController
public class ServerController extends BaseController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/msg")
    public String msg(){
        return "this is product server's msg ,port is " + port;
    }

}