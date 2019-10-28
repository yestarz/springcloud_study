package link.yangxin.order.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yangxin
 * @date 2019/10/28
 */
@Data
public class OrderItems implements Serializable {

    private String productId;

    private Integer productQuantity;

}