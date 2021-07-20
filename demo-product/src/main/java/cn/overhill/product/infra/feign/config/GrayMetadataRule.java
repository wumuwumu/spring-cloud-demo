package cn.overhill.product.infra.feign.config;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.netflix.client.config.IClientConfig;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableDefault;
import com.netflix.loadbalancer.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;
import java.util.Map;

public class GrayMetadataRule extends AbstractLoadBalancerRule {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;
    @Autowired
    private NacosServiceManager nacosServiceManager;


    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object key) {
        String clusterName = this.nacosDiscoveryProperties.getClusterName();
        String group = this.nacosDiscoveryProperties.getGroup();
        DynamicServerListLoadBalancer loadBalancer = (DynamicServerListLoadBalancer)this.getLoadBalancer();
        String name = loadBalancer.getName();
        NamingService namingService = this.nacosServiceManager.getNamingService(this.nacosDiscoveryProperties.getNacosProperties());
        List<Instance> instances = null;
        try {
            instances = namingService.selectInstances(name, group, true);
        } catch (NacosException e) {
            e.printStackTrace();
        }
        if(instances == null || instances.size() == 0){
            logger.warn("没有相关服务 {}",name);
            return null;
        }
        System.out.println("找到相关服务");
        return new NacosServer(instances.get(0));
    }
}