package cn.overhill.gateway.controller;

import cn.overhill.gateway.router.DynamicRouterService;
import cn.overhill.gateway.router.MemoryRouteDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class FilterController {

    @Autowired
    MemoryRouteDefinitionRepository repository;

    @Autowired
    DynamicRouterService service;

    Mono<ServerResponse> add(ServerRequest request){
        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId("simple");
        routeDefinition.setUri(URI.create("https://www.baidu.com"));

        PredicateDefinition predicateDefinition = new PredicateDefinition();
        predicateDefinition.setName("Path");
        Map<String, String> predicateParams = new HashMap<>(8);
        predicateParams.put("path", "/baidu");
        predicateDefinition.setArgs(predicateParams);
        FilterDefinition filterDefinition = new FilterDefinition();
        filterDefinition.setName("StripPrefix");
        Map<String, String> filterMap = new HashMap<>(8);
        filterMap.put("_genkey_0", "1");
        filterDefinition.setArgs(filterMap);
        routeDefinition.setPredicates(Collections.singletonList(predicateDefinition));
        routeDefinition.setFilters(Collections.singletonList(filterDefinition));

        repository.save(Mono.just(routeDefinition));
        service.notifyRouter();
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromValue("Hello, Spring!"));
    }

    @Bean
    public RouterFunction<ServerResponse> route(FilterController filterController) {
        return RouterFunctions
                .route(RequestPredicates.POST("/add").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), filterController::add);
    }
}
