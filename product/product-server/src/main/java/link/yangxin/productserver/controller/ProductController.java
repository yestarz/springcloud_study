package link.yangxin.productserver.controller;

import link.yangxin.my.commons.R;
import link.yangxin.my.commons.controller.BaseController;
import link.yangxin.product.common.DecreaseStockInput;
import link.yangxin.product.common.vo.ProductInfoVO;
import link.yangxin.product.common.vo.ProductVO;
import link.yangxin.productserver.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author yangxin
 * @date 2019/10/20
 */
@RequestMapping("/product")
@RestController
@Slf4j
public class ProductController extends BaseController {

    @Resource
    private ProductInfoService productInfoService;

    @Autowired
    private HttpServletRequest servletRequest;

    @GetMapping("/list")
    public R<List<ProductVO>> list() {
        Cookie[] cookies = servletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                log.info("cookie .key is :{} ,value is :{}", cookie.getName(), cookie.getValue());
            }
        }
        return success(productInfoService.listProduct());
    }

    @PostMapping("/listForProductId")
    public R<List<ProductInfoVO>> listForProductId(@RequestBody List<String> list) {
        return success(productInfoService.listForProductId(list));
    }

    @PostMapping("/decreaseStock")
    public R<Void> decreaseStock(@RequestBody List<DecreaseStockInput> inputList) {
        productInfoService.decreaseStock(inputList);
        return success();
    }

}