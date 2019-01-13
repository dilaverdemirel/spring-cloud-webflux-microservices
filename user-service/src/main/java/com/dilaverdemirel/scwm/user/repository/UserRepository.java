package com.dilaverdemirel.scwm.user.repository;

import com.dilaverdemirel.scwm.user.domain.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @author dilaverd
 */
public interface UserRepository extends ReactiveCrudRepository<User, String> {
}
