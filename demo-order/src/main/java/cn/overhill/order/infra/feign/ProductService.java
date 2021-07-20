package cn.overhill.order.infra.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "demo-product", fallback = ProductServiceCallback.class)
public interface ProductService {

    @PostMapping(value = "/product")
    ResponseEntity reduce (@RequestParam int number);
}
