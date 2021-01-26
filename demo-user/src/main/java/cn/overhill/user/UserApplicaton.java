package cn.overhill.user;


import org.apache.catalina.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class UserApplicaton {
    public static void main(String[] args){
        SpringApplication.run(UserApplicaton.class,args);
    }
}
