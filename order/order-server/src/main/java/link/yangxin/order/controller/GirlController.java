package link.yangxin.order.controller;

import link.yangxin.order.config.GirlConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yangxin
 * @date 2019/11/10
 */
@RestController
@RequestMapping("/girl")
public class GirlController {

    @Resource
    private GirlConfig girlConfig;

    @GetMapping("/test")
    public String test(){
        return girlConfig.toString();
    }

}