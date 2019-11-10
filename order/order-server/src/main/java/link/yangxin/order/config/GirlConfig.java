package link.yangxin.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author yangxin
 * @date 2019/11/10
 */
@ConfigurationProperties(prefix = "girl")
@Data
@Component
@RefreshScope
public class GirlConfig {

    private String name;

    private Integer age;

}