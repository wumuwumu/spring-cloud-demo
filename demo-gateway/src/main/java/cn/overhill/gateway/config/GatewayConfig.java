package cn.overhill.gateway.config;


import cn.overhill.gateway.filter.ElapsedFilter;
import cn.overhill.gateway.predicate.TokenRoutePredicateFactory;
import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.factory.StripPrefixGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author wumu
 */
@Configuration
public class GatewayConfig {

    @Autowired
    private TokenRoutePredicateFactory tokenRoutePredicateFactory;
    @Bean
    public CorsWebFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsWebFilter(source);
    }

    private CorsConfiguration buildConfig(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("content-type");
        corsConfiguration.addAllowedMethod("POST,GET,OPTIONS,DELETE,PUT");
        corsConfiguration.setMaxAge(3628800L);

        return corsConfiguration;
    }


    @Bean
    RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes().route("12",predicateSpec -> {
            /**
             * route：
             * 参数一类似与数据库主键，非重复得标识位
             * 参数二对应断言得请求路径，可以有多个
             * 参数三对应断言结果，如果为true则跳转映射uri中
             */
            return predicateSpec.path("/demo")
                    .and().after(ZonedDateTime.now())
                    .and().asyncPredicate(tokenRoutePredicateFactory.applyAsync(config -> config.setHeaderName("token")))
                    .filters(f->f.filter(new ElapsedFilter()).stripPrefix(1))
                    .uri("https://wumuwumu.github.io");

        }).build();
    }
}
