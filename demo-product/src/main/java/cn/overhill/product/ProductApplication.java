package cn.overhill.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wumu
 */

@EnableFeignClients
@EnableHystrix

@SpringBootApplication
public class ProductApplication {

    public static void main(String[] args){
        SpringApplication.run(ProductApplication.class);
    }
}
