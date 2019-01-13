package com.dilaverdemirel.scwm.email.handler;

import com.dilaverdemirel.scwm.email.dto.EmailEventDTO;
import com.dilaverdemirel.scwm.email.dto.UserDTO;
import com.dilaverdemirel.scwm.email.service.EMailService;
import com.dilaverdemirel.scwm.email.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

/**
 * @author dilaverd
 */
@Component
public class EMailHandler {

    @Autowired
    private EMailService eMailService;

    @Autowired
    private UserService userService;

    public Mono<ServerResponse> send(ServerRequest request) {
        Flux<EmailEventDTO> emailEventDTOFlux = request.bodyToFlux(EmailEventDTO.class)
                .flatMap(emailEvent -> {
                    userService.getUserList()
                            .flatMapMany(response -> response.bodyToFlux(UserDTO.class))
                            .subscribe(userDTO -> {
                                eMailService.send(userDTO.getEmail(), emailEvent);
                            });
                    return Mono.just(emailEvent);
                });

        return ServerResponse.ok()
                .contentType(APPLICATION_JSON_UTF8)
                .body(emailEventDTOFlux, EmailEventDTO.class);
    }
}
