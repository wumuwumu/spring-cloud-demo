package cn.overhill.doc;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DocApplicaton {
    public static void main(String[] args){
        SpringApplication.run(UserApplicaton.class,args);
    }
}
