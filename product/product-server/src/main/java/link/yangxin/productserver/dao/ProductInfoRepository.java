package link.yangxin.productserver.dao;


import link.yangxin.productserver.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);

    List<ProductInfo> findByProductIdIn(List<String> productIdList);
}
