package link.yangxin.order.server.test;

import link.yangxin.order.message.StreamClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author yangxin
 * @date 2019/11/2
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StreamSendTest {

    @Resource
    private StreamClient streamClient;

    @Test
    public void test1(){
        String message = "now is " + new Date();
        streamClient.output().send(MessageBuilder.withPayload(message).build());
    }

    @Test
    public void test2(){

    }
}