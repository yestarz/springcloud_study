package link.yangxin.productserver.controller;

import link.yangxin.my.commons.R;
import link.yangxin.my.commons.controller.BaseController;
import link.yangxin.product.common.vo.ProductVO;
import link.yangxin.productserver.service.ProductInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangxin
 * @date 2019/10/20
 */
@RequestMapping("/product")
@RestController
public class ProductController extends BaseController {

    @Resource
    private ProductInfoService productInfoService;

    @GetMapping("/list")
    public R<List<ProductVO>> list() {
        return success(productInfoService.listProduct());
    }

}