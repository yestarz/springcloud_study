package link.yangxin.product.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yangxin
 * @date 2019/10/21
 */
@Data
public class ProductVO implements Serializable {

    private String name;

    private Integer type;

    private List<ProductInfoVO> foods;

}