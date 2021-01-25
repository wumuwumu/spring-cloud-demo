package cn.overhill.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ElapsedFilter implements GatewayFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest req = exchange.getRequest();
        MultiValueMap<String, HttpCookie> cookieMultiValueMap =  req.getCookies();
        System.out.println(cookieMultiValueMap);
        HttpHeaders httpHeaders =  req.getHeaders();
        List<String> name = Optional.ofNullable(httpHeaders.get("token")).orElse(new ArrayList<>());
        System.out.println(name);
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    System.out.println("完成");
//                    exchange.getResponse().setComplete();
                })
        );
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
