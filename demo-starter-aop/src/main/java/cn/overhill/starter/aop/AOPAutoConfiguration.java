package cn.overhill.starter.aop;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"cn.overhill.starter.aop"})
public class AOPAutoConfiguration {


    @Bean
    ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    AOPProperties aopProperties(){
        return new  AOPProperties();
    }


}
