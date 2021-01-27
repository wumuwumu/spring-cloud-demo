package cn.overhill.gateway.infra.auth;

import lombok.Data;
import org.springframework.web.server.ServerWebExchange;

@Data
public class RequestContext {

    ServerWebExchange exchange;

    String result;

    int code = 1;
}
