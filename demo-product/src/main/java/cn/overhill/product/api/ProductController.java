package cn.overhill.product.api;

import cn.overhill.product.infra.feign.OrderService;
import cn.overhill.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "product")
public class ProductController {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @PostMapping("")
    public ResponseEntity reduce (int number){
        productService.reduce(number);
        return ResponseEntity.ok().build();
    }


    @GetMapping("")
    public int get(){
        return productService.get();
    }
}
