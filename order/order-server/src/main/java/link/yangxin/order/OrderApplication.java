package link.yangxin.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yangxin
 * @date 2019/10/28
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(value = "link.yangxin")
@EnableFeignClients(value = "link.yangxin.product.api")
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}