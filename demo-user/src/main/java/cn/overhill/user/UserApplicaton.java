package cn.overhill.user;


import cn.overhill.starter.aop.EnableAOP;
import org.apache.catalina.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("cn.overhill.user.infra.mapper")
@EnableAOP
@EnableDiscoveryClient
@SpringBootApplication
public class UserApplicaton {
    public static void main(String[] args){
        SpringApplication.run(UserApplicaton.class,args);
    }
}
