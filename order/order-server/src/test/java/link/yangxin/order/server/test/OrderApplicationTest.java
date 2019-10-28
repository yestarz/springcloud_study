package link.yangxin.order.server.test;
import com.google.common.collect.Lists;

import com.alibaba.fastjson.JSON;
import link.yangxin.my.commons.R;
import link.yangxin.order.common.OrderCreateRequest;
import link.yangxin.order.common.OrderItems;
import link.yangxin.order.service.OrderService;
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

    @Resource
    private OrderService orderService;

    @Test
    public void test1(){
        R<List<ProductInfoVO>> r = productApi.listForProductId(Arrays.asList("157875196366160022","157875227953464068"));
        System.out.println(JSON.toJSONString(r));
    }

    @Test
    public void test2(){
        OrderCreateRequest request = new OrderCreateRequest();
        request.setName("yangxin");
        request.setPhone("18580090476");
        request.setOpenid("123");
        request.setAddress("重庆市沙坪坝区");

        OrderItems orderItems = new OrderItems();
        orderItems.setProductId("157875196366160022");
        orderItems.setProductQuantity(50);

        request.setItems(Arrays.asList(orderItems));

        String s = orderService.create(request);
        System.out.println("===============================>");
        System.out.println(s);
    }

}