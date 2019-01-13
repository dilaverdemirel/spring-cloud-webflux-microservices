package com.dilaverdemirel.scwm.order.handler;

import com.dilaverdemirel.scwm.order.common.EvenType;
import com.dilaverdemirel.scwm.order.domain.Order;
import com.dilaverdemirel.scwm.order.repository.OrderRepository;
import com.dilaverdemirel.scwm.order.service.EMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
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
public class OrderHandler {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EMailService eMailService;

    public Mono<ServerResponse> findAll(ServerRequest request) {
        Flux<Order> orders = orderRepository.findAll();
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(orders, Order.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        System.out.println("aaa");
        String id = request.pathVariable("id");
        Mono<Order> orderMono = orderRepository.findById(id);

        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        return orderMono.flatMap(order -> ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(BodyInserters.fromObject(order)))
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(Order.class)
                .flatMap(order -> {
                    Mono<Order> savedOrder = orderRepository.save(order).doOnSuccess(order1 -> {
                        eMailService.sendEmail(order1, EvenType.CREATE);
                    });

                    return ServerResponse.ok().body(savedOrder, Order.class);
                });
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        return request.bodyToMono(Order.class)
                .flatMap(order -> {
                    Mono<Order> savedOrder = orderRepository.save(order)
                            .doOnSuccess(order1 -> {
                                eMailService.sendEmail(order1, EvenType.UPDATE);
                            });
                    return ServerResponse.ok().body(savedOrder, Order.class);
                });
    }

    @Transactional
    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");

        Mono<Order> orderMono = this.orderRepository
                .findById(id)
                .flatMap(order -> {
                    return this.orderRepository.deleteById(order.getId())
                            .doOnSuccess(aVoid -> {
                                eMailService.sendEmail(order, EvenType.DELETE);
                            }).thenReturn(order);
                });

        return ServerResponse.noContent().build(orderMono.then());
    }
}
