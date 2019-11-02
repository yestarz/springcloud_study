package link.yangxin.apigateway.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import link.yangxin.apigateway.exception.RateLimitException;
import link.yangxin.my.commons.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 限流拦截器
 *
 * @author yangxin
 * @date 2019/11/2
 */
@Component
@Slf4j
public class RateLimitFilter extends ZuulFilter {

    // 每秒钟放出100个令牌
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(1);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        // 顺序是越小越靠前
        return FilterConstants.SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        log.info(" rate limit  filter");
        // 如果没有拿到令牌，则抛出异常
        if (!RATE_LIMITER.tryAcquire()) {
            log.error("被限流了");
            throw new RateLimitException("被限流了！！！请稍后重试");
        }
        return null;
    }

    /**
     * 限流测试
     *
     * @param args
     */
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        ExecutorService executorService = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                R r = restTemplate.getForObject("http://localhost:9000/myProduct1/product/list?token=123", R.class);
                System.out.println(r);
            });
        }
        executorService.shutdown();

    }

}