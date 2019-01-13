package com.dilaverdemirel.scwm.user.config;


import com.dilaverdemirel.scwm.user.handler.UserHandler;
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
    public RouterFunction<ServerResponse> orderRouterFunction(UserHandler userHandler) {
        return route(GET("/api/users").and(accept(MediaType.APPLICATION_JSON)), userHandler::findAll)
                .andRoute(GET("/api/users/{id}").and(accept(MediaType.APPLICATION_JSON)), userHandler::findById)
                .andRoute(POST("/api/users").and(accept(MediaType.APPLICATION_JSON)), userHandler::create)
                .andRoute(PUT("/api/users/{id}").and(accept(MediaType.APPLICATION_JSON)), userHandler::update)
                .andRoute(DELETE("/api/users/{id}").and(accept(MediaType.APPLICATION_JSON)), userHandler::delete);
    }
}
