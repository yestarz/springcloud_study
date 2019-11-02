package link.yangxin.order.controller;

import link.yangxin.order.message.StreamClient;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author yangxin
 * @date 2019/11/2
 */
@RestController
@RequestMapping("/stream")
public class StreamSendController {

    @Resource
    private StreamClient streamClient;

    @GetMapping("/test1")
    public void test1(){
        String message = "now is " + new Date();
        streamClient.output().send(MessageBuilder.withPayload(message).build());
    }


}