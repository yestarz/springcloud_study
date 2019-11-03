package link.yangxin.user.server.service.impl;

import link.yangxin.user.server.dao.UserInfoDao;
import link.yangxin.user.server.entity.UserInfo;
import link.yangxin.user.server.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yangxin
 * @date 2019/11/3
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo findByOpenid(String openid) {
        return userInfoDao.findByOpenid(openid);
    }
}