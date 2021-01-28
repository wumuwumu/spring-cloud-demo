package cn.overhill.swagger.api;

import cn.overhill.swagger.app.SwaggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger.web.UiConfiguration;

import java.util.List;

@RestController
public class SwaggerController {

    @Autowired
    private SwaggerService swaggerService;


    @ApiIgnore
    @RequestMapping(value = "/swagger-resources/configuration/security")
    ResponseEntity<SecurityConfiguration> securityConfiguration() {
        return new ResponseEntity<>(swaggerService.getSecurityConfiguration(), HttpStatus.OK);
    }

    @ApiIgnore
    @RequestMapping(value = "/swagger-resources/configuration/ui")
    ResponseEntity<UiConfiguration> uiConfiguration() {
        return new ResponseEntity<UiConfiguration>(swaggerService.getUiConfiguration(), HttpStatus.OK);
    }

    /**
     * 获取swagger服务列表，swagger页面自动请求
     *
     * @return list
     */
    @ApiIgnore
    @RequestMapping(value = "/swagger-resources")
    ResponseEntity<List<SwaggerResource>> swaggerResources() {
        return new ResponseEntity<>(swaggerService.getSwaggerResource(), HttpStatus.OK);
    }

    /**
     * 查询不包含跳过的服务的路由列表
     */
    @ApiIgnore
    @GetMapping("/v1/swaggers/resources")
    public ResponseEntity<List<SwaggerResource>> resources() {
        return new ResponseEntity<>(swaggerService.getSwaggerResource(), HttpStatus.OK);
    }


}
