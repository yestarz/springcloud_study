package link.yangxin.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author yangxin
 * @date 2019/11/2
 */
@Slf4j
@Component
public class MqReceiver {

    public static final String queueName = "myQueue";

    // 这种方式 如果不在mq控制台事先新建queue是要报错的
    @RabbitListener(queues = queueName)
    public void process(String message) {
        log.info("myQueue ，message is :{}", message);
    }

    // 这种方式可以自动创建queue
    @RabbitListener(queuesToDeclare = @Queue("myQueue2"))
    public void process2(String message) {
        log.info("myQueue2 ，message is :{}", message);
    }

    //
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myExchange"),
            value = @Queue("myQueue3")
    ))
    public void process3(String message){
        log.info("myQueue3 ，process3 is :{}", message);
    }

    /**
     * 数码服务商接受消息
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrderExchange"),
            key = "computer",
            value = @Queue("computerOrder")
    ))
    public void processComputer(String message){
        log.info("processComputer ， is :{}", message);
    }

    /**
     * 水果服务商接受消息
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrderExchange"),
            key = "fruit",
            value = @Queue("fruitOrder")
    ))
    public void processFruit(String message){
        log.info("processFruit ， is :{}", message);
    }

}