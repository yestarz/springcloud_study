package link.yangxin.productserver.config;

import link.yangxin.my.commons.IdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author yangxin
 * @date 2019/10/21
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