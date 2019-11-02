package link.yangxin.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * stream的接收端
 * @author yangxin
 * @date 2019/11/2
 */
@Component
@Slf4j
@EnableBinding(StreamClient.class) // 指定client
public class StreamReceiver {

    @StreamListener(StreamClient.INPUT)
    @SendTo(StreamClient.INPUT2) // 通过指定sendTo，可以在消费此消息后，再往指定的队列中发送一条消息，消息内容就是return的返回值
    public String process(Object message) {
        log.info("=======StreamReceiver :{} ==========", message);
        return "ok";
    }

    @StreamListener(StreamClient.INPUT2)
    public void process2(String message) {
        log.info("=======StreamReceiver INPUT2  :{} ==========", message);
    }

}