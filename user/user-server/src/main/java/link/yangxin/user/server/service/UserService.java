package link.yangxin.user.server.service;

import link.yangxin.user.server.entity.UserInfo;

/**
 * @author yangxin
 * @date 2019/11/3
 */
public interface UserService {

    /**
     * 通过openid来查询用户信息
     * @param openid
     * @return
     */
    UserInfo findByOpenid(String openid);

}
