package link.yangxin.productserver.service;

import link.yangxin.product.common.vo.ProductVO;
import link.yangxin.productserver.entity.ProductInfo;

import java.util.List;

/**
 * @author yangxin
 * @date 2019/10/20
 */
public interface ProductInfoService {

     List<ProductVO> listProduct();

}