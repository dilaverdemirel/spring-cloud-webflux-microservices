package com.dilaverdemirel.scwm.order.repository;

import com.dilaverdemirel.scwm.order.domain.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @author dilaverd
 */
public interface OrderRepository extends ReactiveCrudRepository<Order, String> {
}
