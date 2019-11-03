package link.yangxin.order.service;

import link.yangxin.order.common.OrderCreateRequest;

/**
 * @author yangxin
 * @date 2019/10/28
 */
public interface OrderService {

    String create(OrderCreateRequest request);

    /**
     * 完结订单，只能卖家操作
     *
     * @param orderId
     */
    void finishOrder(String orderId);

}
