package cn.overhill.swagger.app.impl;

import cn.overhill.swagger.app.SwaggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.UiConfiguration;

import java.util.LinkedList;
import java.util.List;

@Component
public class SwaggerServiceImpl implements SwaggerService {

    @Autowired
    DiscoveryClient discoveryClient;

    @Override
    public List<SwaggerResource> getSwaggerResource() {
        List<SwaggerResource> resources = new LinkedList<>();
        SwaggerResource resource = new SwaggerResource();
        resource.setName("demo-user");
        resource.setSwaggerVersion("2.0");
        resource.setLocation("http://127.0.0.1:9000/user/v2/api-docs" );
        resources.add(resource);

        return resources;
    }

    @Override
    public UiConfiguration getUiConfiguration() {
        return new UiConfiguration(null);
    }

    @Override
    public SecurityConfiguration getSecurityConfiguration() {
        return new SecurityConfiguration(
                "", "unknown", "default",
                "default", "token",
                ApiKeyVehicle.HEADER, "token", ",");
    }




}
