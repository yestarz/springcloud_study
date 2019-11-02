package link.yangxin.order.server.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 微服务调用的几种方式
 * <p>
 * 1. 直接使用new RestTemplate，传入地址和端口进行调用
 *
 * @author yangxin
 * @date 2019/11/2
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class RemoteApiTest {

    /**
     * 直接使用地址和端口进行调用
     */
    @Test
    public void method1() {
        RestTemplate restTemplate = new RestTemplate();
        String msg = restTemplate.getForObject("http://localhost:8080/msg", String.class);
        log.info("======= msg is {} ============", msg);
    }

    /**
     * 利用loadBalancerClient 选择一个product实例，获取地址和端口信息
     */
    @Resource
    private LoadBalancerClient loadBalancerClient;

    @Test
    public void test2(){
        ServiceInstance instance = loadBalancerClient.choose("product");
        String template = "http://%s:%s/msg";
        String url = String.format(template, instance.getHost(), instance.getPort());

        RestTemplate restTemplate = new RestTemplate();
        String msg = restTemplate.getForObject(url, String.class);
        log.info("======= msg is {} ============", msg);
    }

    /**
     * 注入RestTemplate 并标记LoadBalanced 可以直接调用服务了
     */
    @Resource
    private RestTemplate restTemplate;

    @Test
    public void test3(){
        String msg = restTemplate.getForObject("http://product/msg", String.class);
        log.info("======= msg is {} ============", msg);
    }


}