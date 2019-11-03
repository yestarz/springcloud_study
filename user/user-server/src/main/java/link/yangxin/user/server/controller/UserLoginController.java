package link.yangxin.user.server.controller;

import link.yangxin.my.commons.R;
import link.yangxin.my.commons.controller.BaseController;
import link.yangxin.user.common.constant.CookieConstant;
import link.yangxin.user.common.enums.ResultEnum;
import link.yangxin.user.common.enums.RoleEnum;
import link.yangxin.user.common.vo.UserInfoVO;
import link.yangxin.user.server.entity.UserInfo;
import link.yangxin.user.server.service.UserService;
import link.yangxin.user.server.utils.CookieUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author yangxin
 * @date 2019/11/3
 */
@RestController
@RequestMapping("/user/login")
public class UserLoginController extends BaseController {

    @Resource
    private UserService userService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 买家登陆
     * cookie 里面设置  openId=xxx
     * @param openId
     * @param response
     * @return
     */
    @GetMapping("/buyerLogin")
    public R<UserInfoVO> buyerLogin(String openId, HttpServletResponse response) {
        UserInfo userInfo = userService.findByOpenid(openId);
        if (userInfo == null) {
            return fail(ResultEnum.LOGIN_FAIL.getCode(),ResultEnum.LOGIN_FAIL.getMessage());
        }
        if (!userInfo.getRole().equals(RoleEnum.BUYER.getCode())) {
            return fail(ResultEnum.ROLE_ERROR.getCode(), ResultEnum.ROLE_ERROR.getMessage());
        }

        // 设置cookie
        CookieUtil.set(response, CookieConstant.OPENID, openId, CookieConstant.expire);

        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(userInfo, userInfoVO);

        return success(userInfoVO);
    }

    /**
     * 卖家登陆
     *
     * cookie里面设置 token=uuid ，redis里面设置 为 uuid ,value=openid
     *
     * @param openId
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/sellerLogin")
    public R<UserInfoVO> sellerLogin(String openId, HttpServletRequest request, HttpServletResponse response) {

        // 判断是否已登录

        UserInfo userInfo = userService.findByOpenid(openId);

        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            String value = cookie.getValue();
            String id = stringRedisTemplate.opsForValue().get(value);
            if (StringUtils.isNotBlank(id) && StringUtils.equals(id, openId)) {
                UserInfoVO userInfoVO = new UserInfoVO();
                BeanUtils.copyProperties(userInfo, userInfoVO);
                return success(userInfoVO);
            }
        }


        if (userInfo == null) {
            return fail(ResultEnum.LOGIN_FAIL.getCode(),ResultEnum.LOGIN_FAIL.getMessage());
        }
        if (!userInfo.getRole().equals(RoleEnum.SELLER.getCode())) {
            return fail(ResultEnum.ROLE_ERROR.getCode(), ResultEnum.ROLE_ERROR.getMessage());
        }
        String uuid = UUID.randomUUID().toString();

        // 设置cookie
        CookieUtil.set(response, CookieConstant.TOKEN, uuid, CookieConstant.expire);

        stringRedisTemplate.opsForValue().set(uuid, openId,CookieConstant.expire, TimeUnit.SECONDS);

        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(userInfo, userInfoVO);

        return success(userInfoVO);

    }


}