package link.yangxin.product.api;

import link.yangxin.my.commons.R;
import link.yangxin.product.common.DecreaseStockInput;
import link.yangxin.product.common.vo.ProductInfoVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author yangxin
 * @date 2019/10/28
 */
@FeignClient(name = "product", fallback = ProductApi.ProductApiFallBack.class)
public interface ProductApi {

    @PostMapping("/product/listForProductId")
    R<List<ProductInfoVO>> listForProductId(@RequestBody List<String> list);

    @PostMapping("/product/decreaseStock")
    R<Void> decreaseStock(@RequestBody List<DecreaseStockInput> inputList);

    @Component// 一定要加注解！
    class ProductApiFallBack implements ProductApi {

        @Override
        public R<List<ProductInfoVO>> listForProductId(List<String> list) {
            return R.fail("listForProductId接口太拥挤了！");
            //return null;
        }

        @Override
        public R<Void> decreaseStock(List<DecreaseStockInput> inputList) {
            return R.fail("decreaseStock 接口太拥挤了！！！");
            //return null;
        }
    }

}
