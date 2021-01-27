package cn.overhill.gateway.infra.auth;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class UserDetailHelper implements AuthHelper, Ordered {
    @Override
    public boolean shouldFilter(ServerWebExchange exchange) {
        return true;
    }

    @Override
    public boolean filter(RequestContext requestContext) {
        System.out.println("获取用户信息");
        return true;
    }

    @Override
    public int getOrder() {
        return 10;
    }
}
