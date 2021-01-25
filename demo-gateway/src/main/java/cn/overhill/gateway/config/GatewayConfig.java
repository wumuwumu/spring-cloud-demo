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
    RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes().route("12",predicateSpec -> {
            return predicateSpec.path("/demo")
                    .and().after(ZonedDateTime.now())
                    .and().asyncPredicate(tokenRoutePredicateFactory.applyAsync(config -> config.setHeaderName("token")))
                    .filters(f->f.filter(new ElapsedFilter()).stripPrefix(1))
                    .uri("https://wumuwumu.github.io");

        }).build();
    }
}
