package cn.overhill.gateway.infra.auth;

import org.springframework.web.server.ServerWebExchange;

public interface AuthHelper {

    boolean shouldFilter(ServerWebExchange exchange);

    boolean filter(RequestContext requestContext);
}
