package cn.overhill.product.service.impl;

import cn.overhill.product.entity.ProductStock;
import cn.overhill.product.infra.mapper.ProductStockMapper;
import cn.overhill.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private int account = 10;


    @Autowired
    ProductStockMapper productStockMapper;

    @Override
    public void reduce(int number) {
        log.info("准备扣除商品数量{}",number);
        ProductStock productStock = productStockMapper.selectById(1);
        productStock.setQuantity(productStock.getQuantity() -number);
        productStockMapper.updateById(productStock);
        throw  new NullPointerException("自定义一个异常");
//        log.info("剩余库存{}",account);

    }

    @Override
    public int get() {
        ProductStock productStock = productStockMapper.selectById(1);

        return productStock.getQuantity();
    }
}
