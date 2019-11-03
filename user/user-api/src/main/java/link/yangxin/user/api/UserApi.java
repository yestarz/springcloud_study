package link.yangxin.user.api;

import link.yangxin.my.commons.R;
import link.yangxin.user.common.vo.UserInfoVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yangxin
 * @date 2019/11/3
 */
@RequestMapping("/user")
@FeignClient(name = "user")
public interface UserApi {

    @GetMapping("/login/buyerLogin")
    R<UserInfoVO> buyerLogin(String openId);

    @GetMapping("/login/sellerLogin")
    R<UserInfoVO> sellerLogin(String openId);

}
