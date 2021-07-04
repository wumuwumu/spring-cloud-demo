package cn.overhill.product.infra.feign;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeoutException;


@Component
public class OrderServiceCallback implements OrderService{
    @Override
    public void addOrder(int money)  {
        System.out.println("熔断");
        return;
    }
}
