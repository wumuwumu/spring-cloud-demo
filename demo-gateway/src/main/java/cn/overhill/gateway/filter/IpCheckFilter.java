package cn.overhill.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

//@Component
public class IpCheckFilter implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        InetSocketAddress inetSocketAddress = exchange.getRequest().getRemoteAddress();
        String ip = inetSocketAddress.getHostName();
        System.out.println("获取到地址：" + ip);
        if (ip.equals("127.0.0.1")) {
            return chain.filter(exchange.mutate().request(builder -> {
                builder.header("x-forwarded-for", ip);
            }).build());
        } else {
            exchange.getResponse().getHeaders().put(HttpHeaders.CONTENT_TYPE,
                    Collections.singletonList(MediaType.APPLICATION_JSON_UTF8_VALUE));
            DataBufferFactory dataBufferFactory = exchange.getResponse().bufferFactory();
            DataBuffer dataBuffer = dataBufferFactory.wrap("ip不能访问".getBytes(StandardCharsets.UTF_8));
            return exchange.getResponse().writeAndFlushWith(Flux.just(dataBuffer).map(Flux::just));
        }
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
