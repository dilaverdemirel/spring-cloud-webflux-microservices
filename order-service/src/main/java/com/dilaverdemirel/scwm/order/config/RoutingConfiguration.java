package com.dilaverdemirel.scwm.order.config;


import com.dilaverdemirel.scwm.order.handler.OrderHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author dilaverd
 */
@Configuration
public class RoutingConfiguration {

    @Bean
    public RouterFunction<ServerResponse> orderRouterFunction(OrderHandler orderHandler) {
        return route(GET("/api/orders").and(accept(MediaType.APPLICATION_JSON)), orderHandler::findAll)
                .andRoute(GET("/api/orders/{id}").and(accept(MediaType.APPLICATION_JSON)), orderHandler::findById)
                .andRoute(POST("/api/orders").and(accept(MediaType.APPLICATION_JSON)), orderHandler::create)
                .andRoute(PUT("/api/orders/{id}").and(accept(MediaType.APPLICATION_JSON)), orderHandler::update)
                .andRoute(DELETE("/api/orders/{id}").and(accept(MediaType.APPLICATION_JSON)), orderHandler::delete);
    }
}
