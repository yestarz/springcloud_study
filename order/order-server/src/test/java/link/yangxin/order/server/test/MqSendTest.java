package link.yangxin.order.server.test;

import link.yangxin.order.message.MqReceiver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author yangxin
 * @date 2019/11/2
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MqSendTest {

    @Resource
    private AmqpTemplate amqpTemplate;

    @Test
    public void test1(){
        amqpTemplate.convertAndSend(MqReceiver.queueName, "now is " + new Date());
        amqpTemplate.convertAndSend(MqReceiver.queueName+"2", "now is " + new Date());
        amqpTemplate.convertAndSend(MqReceiver.queueName+"3", "now is " + new Date());

        amqpTemplate.convertAndSend("myOrderExchange","computer", "now is " + new Date());
        amqpTemplate.convertAndSend("myOrderExchange","fruit", "now is " + new Date());
    }

}