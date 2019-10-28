package link.yangxin.order.service.impl;

import link.yangxin.order.common.OrderCreateRequest;
import link.yangxin.order.dao.OrderDao;
import link.yangxin.order.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yangxin
 * @date 2019/10/28
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Override
    public String create(OrderCreateRequest request) {

        return null;
    }
}