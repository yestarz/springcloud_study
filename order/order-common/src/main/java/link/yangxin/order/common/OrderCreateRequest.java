package link.yangxin.order.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yangxin
 * @date 2019/10/28
 */
@Data
public class OrderCreateRequest implements Serializable {

    private String name;

    private String phone;

    private String openid;

    private String address;

    private List<OrderItems> items;

}