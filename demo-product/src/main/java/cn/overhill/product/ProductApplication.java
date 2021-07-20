package cn.overhill.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wumu
 */
@MapperScan("cn.overhill.product.infra.mapper")

@EnableFeignClients
@EnableHystrix
@SpringBootApplication
public class ProductApplication {

    public static void main(String[] args){
        SpringApplication.run(ProductApplication.class);
    }
}
