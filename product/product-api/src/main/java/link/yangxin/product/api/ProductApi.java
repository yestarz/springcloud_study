package link.yangxin.product.api;

import link.yangxin.my.commons.R;
import link.yangxin.product.common.vo.ProductInfoVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author yangxin
 * @date 2019/10/28
 */
@FeignClient(name = "product")
public interface ProductApi {

    @PostMapping("/product/listForProductId")
    R<List<ProductInfoVO>> listForProductId(@RequestBody List<String> list);


}
