package cn.overhill.gateway.router;

import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class MemoryRouteDefinitionRepository implements RouteDefinitionRepository {

    private ConcurrentHashMap<String,RouteDefinition> routerMap = new ConcurrentHashMap<>();

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        System.out.println("获取"+routerMap.values());
        return Flux.fromIterable(routerMap.values());
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
         route.subscribe(routeDefinitionSignal -> {
            routerMap.put(routeDefinitionSignal.getId(),routeDefinitionSignal);
        });
        return Mono.empty();
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        routeId.subscribe(routerId -> {
            routerMap.remove(routeId);
        });
        return Mono.empty();
    }
}
