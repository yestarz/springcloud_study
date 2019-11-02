package link.yangxin.order.server.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * @author yangxin
 * @date 2019/11/2
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class RemoteApiTest {

    // 微服务调用的几种方式

    @Test
    public void method1() {
        RestTemplate restTemplate = new RestTemplate();
        String msg = restTemplate.getForObject("http://localhost:8080/msg", String.class);
        log.info("msg is {}", msg);
    }


}