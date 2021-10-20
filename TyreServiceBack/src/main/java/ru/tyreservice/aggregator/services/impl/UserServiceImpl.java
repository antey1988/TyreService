package ru.tyreservice.aggregator.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.tyreservice.aggregator.enums.Role;
import ru.tyreservice.aggregator.entities.User;
import ru.tyreservice.aggregator.repositories.UserRepository;
import ru.tyreservice.aggregator.services.UserService;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final String adminLogin;
    private final String adminPassword;
    private final String adminEncodePassword;
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository,
                           PasswordEncoder passwordEncoder,
                           @Value(value = "${authentication.admin.login}") String login,
                           @Value(value = "${authentication.admin.password}") String password) {
        this.repository = repository;
        this.adminLogin = login;
        this.adminPassword = password;
        this.adminEncodePassword = passwordEncoder.encode(password);
    }

    @Override
    public User findUserByLogin(String login) {
        if (login.equals(adminLogin)) {
            User user = new User();
            user.setLogin(adminLogin);
            user.setPassword(adminEncodePassword);
            user.setRole(Role.ADMIN);
            return user;
        }
        return repository.findByLogin(login).orElseThrow(RuntimeException::new);
    }

    @Override
    public void createUser(String login, String password, Role role, Long accountId) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        user.setAccountId(accountId);
        repository.save(user);
    }
}
