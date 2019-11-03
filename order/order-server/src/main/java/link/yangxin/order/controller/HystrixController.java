package link.yangxin.order.controller;

import com.alibaba.fastjson.JSON;
import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import link.yangxin.my.commons.R;
import link.yangxin.my.commons.controller.BaseController;
import link.yangxin.product.api.ProductApi;
import link.yangxin.product.common.vo.ProductInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 测试服务降级
 *
 * @author yangxin
 * @date 2019/11/3
 */
@RestController
@RequestMapping("/hystrix")
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController extends BaseController {

    @Resource
    private ProductApi productApi;

    /**
     * 配置服务降级
     * @see com.netflix.hystrix.HystrixCommandProperties 配置类
     * @return
     */
    @GetMapping("/getProductList")
    //@HystrixCommand(fallbackMethod = "fallback")
    //@HystrixCommand// 可以用类上的默认的降级方法
    // 配置超时时间
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String getProductList() {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject("http://localhost:8080/product/listForProductId", Arrays.asList("157875196366160022"), String.class);
        return result;
        // 降级也并非只是调用其他服务才会触发，如果本服务也抛出了异常，那么也会触发降级
    }


    /**
     * 配置服务熔断
     * circuitBreaker:断路器，及时的切断故障。当故障达到一定的值，断路器将会跳闸，
     *
     * @return
     */
    @GetMapping("/getProductList2")
    //@HystrixCommand(fallbackMethod = "fallback")
    //@HystrixCommand// 可以用类上的默认的降级方法
    // 配置超时时间
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 设置熔断
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),// 10s
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 错误百分比条件
    })
    public String getProductList2(Integer number) {
        if (number % 2 == 0) {
            return "success";

        } else {
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.postForObject("http://localhost:8080/product/listForProductId", Arrays.asList("157875196366160022"), String.class);
            return result;
        }
    }


    /**
     * 使用yml配置超时时间
     * @return
     */
    @GetMapping("/getProductListYml")
    @HystrixCommand
    public String getProductListYml() {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject("http://localhost:8080/product/listForProductId", Arrays.asList("157875196366160022"), String.class);
        return result;
        // 降级也并非只是调用其他服务才会触发，如果本服务也抛出了异常，那么也会触发降级
    }


    @GetMapping("/testFeign")
    public String testFeign(){
        R<List<ProductInfoVO>> list = productApi.listForProductId(Arrays.asList("157875196366160022"));
        return JSON.toJSONString(list);
    }

    private String fallback(){
        return "太拥挤！请稍后再试！";
    }

    private String defaultFallback(){
        return "defaultFallback : 太拥挤！请稍后再试！";
    }

}