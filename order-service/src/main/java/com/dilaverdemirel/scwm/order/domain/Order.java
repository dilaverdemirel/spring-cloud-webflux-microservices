package com.dilaverdemirel.scwm.order.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author dilaverd
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
@Document
public class Order {
    @Id
    private String id;
    private String customerName;
    private String productName;
    private String deliveryAddress;
    private Double amount;
    private Status status;

    public enum Status {
        NEW,CANCEL,APPROVE
    }
}
