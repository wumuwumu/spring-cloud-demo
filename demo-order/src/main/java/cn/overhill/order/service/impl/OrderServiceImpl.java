package cn.overhill.order.service.impl;

import cn.overhill.order.infra.feign.AccountService;
import cn.overhill.order.infra.feign.ProductService;
import cn.overhill.order.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    ProductService productService;

    @Autowired
    AccountService accountService;


    @GlobalTransactional
    @Override
    public void add(String productName) {
        log.info("购买产品{}",productName);
        productService.reduce(1);

        accountService.reduce(100);
        log.info("购买成功");
    }
}
