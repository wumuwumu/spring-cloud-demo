package cn.overhill.gateway.filter;

import cn.overhill.gateway.infra.auth.Authenticator;
import cn.overhill.gateway.infra.auth.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class AuthHelperFilter  implements GlobalFilter, Ordered {


    @Autowired
    private Authenticator authenticator;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        RequestContext requestContext = authenticator.auth(exchange);
        if(requestContext.getCode() != 1){
            DataBufferFactory dataBufferFactory = exchange.getResponse().bufferFactory();
            DataBuffer dataBuffer = dataBufferFactory.wrap(requestContext.getResult().getBytes(StandardCharsets.UTF_8));
            return exchange.getResponse().writeAndFlushWith(Flux.just(dataBuffer).map(Flux::just));
        }else {
            return chain.filter(exchange);

        }
    }

    @Override
    public int getOrder() {
        return 12;
    }
}
