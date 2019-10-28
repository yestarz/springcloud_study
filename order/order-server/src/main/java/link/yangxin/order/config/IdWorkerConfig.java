package link.yangxin.order.config;

import link.yangxin.my.commons.IdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangxin
 * @date 2019/10/28
 */
@Configuration
public class IdWorkerConfig {

    @Value("${idworker.workerId}")
    Long workerId;

    @Value("${idworker.datacenterId}")
    Long datacenterId;


    @Bean
    public IdWorker idWorker() {
        return new IdWorker(workerId, datacenterId);
    }

}