package cn.overhill.gateway.infra.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

//@Component
public class SkipAuthHelper implements AuthHelper, Ordered {


    @Override
    public boolean shouldFilter(ServerWebExchange exchange) {
        return true;
    }

    @Override
    public boolean filter(RequestContext requestContext) {
        MultiValueMap<String, String> query =  requestContext.getExchange().getRequest().getQueryParams();
        String publicStr = query.getFirst("public");
        boolean isPublic = Boolean.parseBoolean(publicStr);
        if(isPublic){
            return true;
        }else {
            System.out.println("uri需要认证");
            requestContext.setResult("该请求需要认证");
            requestContext.setCode(403);
            return true;
        }

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
