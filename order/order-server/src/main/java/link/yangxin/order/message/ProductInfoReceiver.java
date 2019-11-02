package link.yangxin.order.message;

import com.alibaba.fastjson.JSON;
import link.yangxin.product.common.vo.ProductInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author yangxin
 * @date 2019/11/2
 */
@Component
@Slf4j
public class ProductInfoReceiver {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    // 这种方式可以自动创建queue
    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process2(String message) {
        ProductInfoVO productInfoVO = JSON.parseObject(message, ProductInfoVO.class);
        log.info("productInfo ，message is :{}", productInfoVO);
        stringRedisTemplate.opsForValue().set("product_stock_" + productInfoVO.getProductId(), String.valueOf(productInfoVO.getProductStock()));
    }

}