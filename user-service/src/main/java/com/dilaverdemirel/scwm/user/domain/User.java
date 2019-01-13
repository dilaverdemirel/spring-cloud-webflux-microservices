package com.dilaverdemirel.scwm.user.domain;

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
public class User {
    @Id
    private String id;
    private String nameTitle;
    private String email;
}
