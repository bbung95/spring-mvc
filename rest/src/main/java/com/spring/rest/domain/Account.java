package com.spring.rest.domain;

import com.spring.rest.account.enums.AccountRole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "Users")
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;
    private String password;

    @ElementCollection(fetch =  FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<AccountRole> roles;

    public Collection<? extends GrantedAuthority> getAuthorities(){

        return this.roles.stream().map(item ->
                new SimpleGrantedAuthority("ROLE_" + item.name())
        ).collect(Collectors.toSet());
    }
}
