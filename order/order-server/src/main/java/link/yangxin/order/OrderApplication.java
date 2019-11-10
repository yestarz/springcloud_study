package link.yangxin.order;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yangxin
 * @date 2019/10/28
 */
//@SpringBootApplication
//@EnableEurekaClient
//@EnableCircuitBreaker
@SpringCloudApplication
@ComponentScan(value = "link.yangxin")
@EnableFeignClients(value = "link.yangxin.product.api")
@EnableHystrixDashboard
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}