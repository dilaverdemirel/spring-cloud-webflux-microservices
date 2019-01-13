package com.dilaverdemirel.scwm.order.service;

import com.dilaverdemirel.scwm.order.common.EvenType;
import com.dilaverdemirel.scwm.order.domain.Order;
import com.dilaverdemirel.scwm.order.dto.EmailEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author dilaverd
 */
@Service
public class EMailService {
    private static final String NEW_LINE = "\r\n";

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Mono<Void> sendEmail(Order order, EvenType evenType) {
        Mono<ClientResponse> clientResponseMono = webClientBuilder.build().post()
                .uri("http://email-service/api/email/send")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(prepare(order, evenType)))
                .exchange();

        clientResponseMono.flatMap(clientResponse -> clientResponse.bodyToMono(Void.class))
                .subscribe();

        return null;
    }

    private EmailEventDTO prepare(Order order, EvenType evenType) {
        EmailEventDTO emailEventDTO = new EmailEventDTO();
        emailEventDTO.subject = new StringBuilder().append("Order ").append(evenType.getLabel()).toString();

        emailEventDTO.content = new StringBuilder()
                .append("Order Numner :")
                .append(order.getId())
                .append(NEW_LINE)
                .append("Status :")
                .append(order.getStatus())
                .append(NEW_LINE)
                .append("Customer Name :")
                .append(order.getCustomerName())
                .append(NEW_LINE)
                .append("Product Name :")
                .append(order.getProductName())
                .append(NEW_LINE)
                .append("Address :")
                .append(order.getDeliveryAddress())
                .append(NEW_LINE)
                .append("Amount :")
                .append(order.getAmount())
                .append(NEW_LINE)
                .toString();
        return emailEventDTO;
    }
}
