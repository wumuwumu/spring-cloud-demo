package cn.overhill.product.infra.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.concurrent.TimeoutException;

@Component
@FeignClient(value = "demo-order" ,fallback = OrderServiceCallback.class)
public interface OrderService {

    @PostMapping(value = "/order")
    void addOrder(@RequestParam int money) ;
}
