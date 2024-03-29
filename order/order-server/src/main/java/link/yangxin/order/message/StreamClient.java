package link.yangxin.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author yangxin
 * @date 2019/11/2
 */
public interface StreamClient {

    String INPUT = "myMessage";
    String INPUT2 = "myMessage2";

    @Input(StreamClient.INPUT)
    SubscribableChannel input();

    @Output(StreamClient.INPUT2)
    MessageChannel output();

 /*   @Input(StreamClient.INPUT2)
    SubscribableChannel input2();

    @Output(StreamClient.INPUT2)
    MessageChannel output2();*/

}