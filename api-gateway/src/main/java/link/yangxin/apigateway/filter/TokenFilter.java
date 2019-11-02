package link.yangxin.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 通过这个过滤器，访问所有的请求 都要带一个参数：token
 * @author yangxin
 * @date 2019/11/2
 */
@Component
@Slf4j
public class TokenFilter extends ZuulFilter {
    @Override
    public String filterType() {
        // 前置过滤器
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        // 顺序是越小越靠前
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        log.info("=======  pre filter ====== ");
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        // 从参数里面获取token，也可以从header、cookie中获取
        String token = request.getParameter("token");
        if (StringUtils.isBlank(token)) {
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return null;
    }
}