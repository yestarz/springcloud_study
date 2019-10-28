package link.yangxin.order.dao;

import link.yangxin.order.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yangxin
 * @date 2019/10/28
 */
@Repository
public interface OrderDetailDao extends JpaRepository<OrderDetail, String> {



}
