package com.spring.rest.domain;

import com.spring.rest.account.enums.AccountRole;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Users")
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;
    private String password;

    @ElementCollection(fetch =  FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<AccountRole> roles;
}
