package com.dilaverdemirel.scwm.user.handler;

import com.dilaverdemirel.scwm.user.domain.User;
import com.dilaverdemirel.scwm.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * @author dilaverd
 */
@Component
public class UserHandler {
    @Autowired
    private UserRepository userRepository;

    public Mono<ServerResponse> findAll(ServerRequest request) {
        Flux<User> users = userRepository.findAll();
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(users, User.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<User> userMono = userRepository.findById(id);

        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        return userMono.flatMap(order -> ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(BodyInserters.fromObject(order)))
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(User.class)
                .flatMap(user -> ServerResponse.ok().body(userRepository.save(user), User.class));
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        return request.bodyToMono(User.class)
                .flatMap(user -> ServerResponse.ok().body(userRepository.save(user), User.class));
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.noContent().build(userRepository.deleteById(id));
    }
}
