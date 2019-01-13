package com.dilaverdemirel.scwm.email.config;


import com.dilaverdemirel.scwm.email.handler.EMailHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author dilaverd
 */
@Configuration
public class RoutingConfiguration {

    @Bean
    public RouterFunction<ServerResponse> eMailRouterFunction(EMailHandler eMailHandler) {
        return route(POST("/api/email/send").and(accept(MediaType.APPLICATION_JSON)), eMailHandler::send);
    }
}
