package cn.overhill.gateway.infra.auth;

import org.bouncycastle.cert.ocsp.Req;
import org.springframework.web.server.ServerWebExchange;

public interface Authenticator {
    RequestContext auth(ServerWebExchange exchange);
}
