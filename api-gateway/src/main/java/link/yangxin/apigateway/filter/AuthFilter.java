package link.yangxin.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import link.yangxin.apigateway.constants.CookieConstant;
import link.yangxin.apigateway.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 权限拦截
 * 区分买家和卖家
 *
 * @author yangxin
 * @date 2019/11/2
 */
@Component
@Slf4j
public class AuthFilter extends ZuulFilter {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

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
        log.info("=======  auth filter ====== ");
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        // 从参数里面获取token，也可以从header、cookie中获取
        String token = request.getParameter("token");

        /*
         * /order/create 只能买家访问(cookie里面有openid)
         * /order/finish 只能卖家访问（cookie里面有token，且redis里面有值）
         * /product/productList 都能访问
         */

        if (request.getRequestURI().equals("/order/order/create")) {
            Cookie cookie = CookieUtil.get(request, CookieConstant.OPENID);
            if (cookie == null || cookie.getValue() == null) {
                currentContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
                currentContext.setSendZuulResponse(false);
            }
        }
        if (request.getRequestURI().equals("/order/order/finish")) {
            Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);

            if (cookie == null || cookie.getValue() == null ||  (stringRedisTemplate.opsForValue().get(cookie.getValue()) == null)) {
                currentContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
                currentContext.setSendZuulResponse(false);
            }
        }
        return null;
    }
}