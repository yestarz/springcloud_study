package link.yangxin.user.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yangxin
 * @date 2019/11/3
 */
@Data
public class UserInfoVO implements Serializable {

    private String id;

    private String username;

    private String password;

    private String openid;

    private Integer role;

}