package link.yangxin.productserver.service.impl;

import link.yangxin.my.commons.exceptions.BusinessException;
import link.yangxin.product.common.DecreaseStockInput;
import link.yangxin.product.common.enums.ProductStatusEnum;
import link.yangxin.product.common.vo.ProductInfoVO;
import link.yangxin.product.common.vo.ProductVO;
import link.yangxin.productserver.dao.ProductCategoryRepository;
import link.yangxin.productserver.dao.ProductInfoRepository;
import link.yangxin.productserver.entity.ProductCategory;
import link.yangxin.productserver.entity.ProductInfo;
import link.yangxin.productserver.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author yangxin
 * @date 2019/10/21
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Resource
    private ProductInfoRepository productInfoRepository;

    @Resource
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductVO> listProduct() {
        List<ProductInfo> infoList = productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
        List<Integer> integerList = infoList.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());
        List<ProductCategory> list = productCategoryRepository.findByCategoryTypeIn(integerList);

        List<ProductVO> voList = new ArrayList<>();
        for (ProductCategory productCategory : list) {
            ProductVO vo = new ProductVO();
            vo.setName(productCategory.getCategoryName());
            vo.setType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOS = new ArrayList<>();
            for (ProductInfo productInfo : infoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOS.add(productInfoVO);
                }
            }
            vo.setFoods(productInfoVOS);
            voList.add(vo);
        }

        return voList;
    }

    @Override
    public List<ProductInfoVO> listForProductId(List<String> list) {
        List<ProductInfoVO> results = new ArrayList<>();
        for (String s : list) {
            Optional<ProductInfo> optional = productInfoRepository.findById(s);
            if (optional.isPresent()) {
                ProductInfo productInfo = optional.get();
                ProductInfoVO productInfoVO = new ProductInfoVO();
                BeanUtils.copyProperties(productInfo, productInfoVO);
                results.add(productInfoVO);
            }
        }
        return results;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void decreaseStock(List<DecreaseStockInput> inputList) {
        for (DecreaseStockInput input : inputList) {
            String productId = input.getProductId();
            Optional<ProductInfo> optional = productInfoRepository.findById(productId);
            if (!optional.isPresent()) {
                throw new BusinessException("商品不存在");
            }
            ProductInfo productInfo = optional.get();
            Integer result = productInfo.getProductStock() - input.getProductQuantity();
            if (result < 0) {
                throw new BusinessException("库存不足");
            }
            productInfo.setProductStock(result);
            productInfo.setUpdateTime(new Date());
            productInfoRepository.save(productInfo);
        }
    }
}