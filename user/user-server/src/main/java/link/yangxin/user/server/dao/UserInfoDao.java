package link.yangxin.user.server.dao;


import link.yangxin.user.server.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 廖师兄
 * 2018-03-04 21:42
 */
@Repository
public interface UserInfoDao extends JpaRepository<UserInfo, String> {

    UserInfo findByOpenid(String openid);
}
