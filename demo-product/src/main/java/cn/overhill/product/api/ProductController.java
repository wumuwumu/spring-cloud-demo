package cn.overhill.product.api;

import cn.overhill.product.infra.feign.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "product")
public class ProductController {

    @Autowired
    OrderService orderService;

    @PostMapping("/")
    public ResponseEntity add (String name){
        System.out.println("购买产品："+name);
        orderService.addOrder(12);
        return ResponseEntity.ok("购买成功");
    }
}
