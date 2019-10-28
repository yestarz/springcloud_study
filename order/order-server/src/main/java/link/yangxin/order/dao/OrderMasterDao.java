package link.yangxin.order.dao;

import link.yangxin.order.entity.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yangxin
 * @date 2019/10/28
 */
@Repository
public interface OrderMasterDao extends JpaRepository<OrderMaster, String> {
}