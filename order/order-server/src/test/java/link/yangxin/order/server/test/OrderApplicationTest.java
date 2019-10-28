package link.yangxin.order.server.test;

import com.alibaba.fastjson.JSON;
import link.yangxin.my.commons.R;
import link.yangxin.product.api.ProductApi;
import link.yangxin.product.common.vo.ProductInfoVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author yangxin
 * @date 2019/10/28
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderApplicationTest {

    @Resource
    private ProductApi productApi;

    @Test
    public void test1(){
        R<List<ProductInfoVO>> r = productApi.listForProductId(Arrays.asList("157875196366160022","157875227953464068"));
        System.out.println(JSON.toJSONString(r));
    }

}