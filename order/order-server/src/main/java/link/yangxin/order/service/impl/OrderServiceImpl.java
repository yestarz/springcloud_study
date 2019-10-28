package link.yangxin.order.service.impl;

import link.yangxin.my.commons.IdWorker;
import link.yangxin.my.commons.R;
import link.yangxin.order.common.OrderCreateRequest;
import link.yangxin.order.common.OrderItems;
import link.yangxin.order.common.enums.OrderStatusEnum;
import link.yangxin.order.common.enums.PayStatusEnum;
import link.yangxin.order.dao.OrderDetailDao;
import link.yangxin.order.dao.OrderMasterDao;
import link.yangxin.order.entity.OrderDetail;
import link.yangxin.order.entity.OrderMaster;
import link.yangxin.order.service.OrderService;
import link.yangxin.product.api.ProductApi;
import link.yangxin.product.common.DecreaseStockInput;
import link.yangxin.product.common.vo.ProductInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yangxin
 * @date 2019/10/28
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDetailDao orderDetailDao;

    @Resource
    private OrderMasterDao orderMasterDao;

    @Resource
    private ProductApi productApi;

    @Resource
    private IdWorker idWorker;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String create(OrderCreateRequest request) {
        String orderId = "O" + idWorker.nextId();

        // 查询商品信息
        R<List<ProductInfoVO>> r = productApi.listForProductId(request.getItems().stream().map(OrderItems::getProductId).collect(Collectors.toList()));

        if (!r.isSuccess()) {
            throw new RuntimeException(r.getMessage());
        }

        List<ProductInfoVO> productList = r.getData();

        // 计算总价
        BigDecimal total = new BigDecimal("0");

        List<OrderItems> items = request.getItems();

        for (OrderItems item : items) {
            for (ProductInfoVO vo : productList) {
                if (!item.getProductId().equals(vo.getProductId())) {
                   continue;
                }
                total = total.add(vo.getProductPrice().multiply(new BigDecimal(String.valueOf(item.getProductQuantity()))));

                OrderDetail orderDetail = new OrderDetail();
                BeanUtils.copyProperties(vo, orderDetail);
                orderDetail.setDetailId("OD" + idWorker.nextId());
                orderDetail.setOrderId(orderId);
                orderDetail.setProductQuantity(item.getProductQuantity());
                orderDetailDao.save(orderDetail);
            }
        }

        // 扣库存

        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderId);
        orderMaster.setBuyerName(request.getName());
        orderMaster.setBuyerPhone(request.getPhone());
        orderMaster.setBuyerAddress(request.getAddress());
        orderMaster.setBuyerOpenid(request.getOpenid());
        orderMaster.setOrderAmount(total);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMaster.setCreateTime(new Date());
        orderMaster.setUpdateTime(new Date());

        orderMasterDao.save(orderMaster);

        List<DecreaseStockInput> inputList = request.getItems().stream().map(s -> new DecreaseStockInput(s.getProductId(), s.getProductQuantity())).collect(Collectors.toList());

        R<Void> decreaseStockResult = productApi.decreaseStock(inputList);
        if (!decreaseStockResult.isSuccess()) {
            throw new RuntimeException("库存扣除失败：" + decreaseStockResult.getMessage());
        }

        return orderId;
    }
}