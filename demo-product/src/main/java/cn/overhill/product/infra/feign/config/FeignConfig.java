package cn.overhill.product.infra.feign.config;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    IRule iRule(){
        return new GrayMetadataRule();
    }
}
