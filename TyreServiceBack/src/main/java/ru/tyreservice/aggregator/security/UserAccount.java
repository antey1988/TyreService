package ru.tyreservice.aggregator.security;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Slf4j
@Getter
@Setter
public class UserAccount extends User {
    //идентификатор личного кабинета пользователя
    private Long accountId;

    public UserAccount(String username, String password, Collection<? extends GrantedAuthority> authorities, Long accountId) {
        super(username, password, authorities);
        this.accountId = accountId;
    }

    public GrantedAuthority getRole() {
        return this.getAuthorities().iterator().next();
    }
}
