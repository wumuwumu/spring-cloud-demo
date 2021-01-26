package cn.overhill.gateway.service;

import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.cloud.nacos.discovery.NacosServiceDiscovery;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingMaintainService;
import com.alibaba.nacos.api.naming.NamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class NacosService {

    @Autowired
    private NacosServiceDiscovery nacosServiceDiscovery;

    @PostConstruct
    public void post(){
        try {
            getServices();
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }

    Flux<Service> getServices() throws NacosException {
        List<String> services =  nacosServiceDiscovery.getServices();
        System.out.println("获取发现的服务："+services);
        for(String service : services){
            List<ServiceInstance> serviceInstances = nacosServiceDiscovery.getInstances(service);
            for (ServiceInstance serviceInstance : serviceInstances) {
                System.out.println(serviceInstance.getHost());
            }
        }
        return Flux.empty();
    }
}
