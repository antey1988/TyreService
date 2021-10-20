package ru.tyreservice.aggregator.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tyreservice.aggregator.dto.requests.RegDataRequest;
import ru.tyreservice.aggregator.dto.responses.StatusResponse;
import ru.tyreservice.aggregator.enums.Role;
import ru.tyreservice.aggregator.exceptions.NullValueFieldException;
import ru.tyreservice.aggregator.services.ClientService;
import ru.tyreservice.aggregator.services.PartnerService;
import ru.tyreservice.aggregator.services.RegistrationService;
import ru.tyreservice.aggregator.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final PasswordEncoder  passwordEncoder;
    private final UserService userService;
    private final PartnerService partnerService;
    private final ClientService clientService;

    @Override
    @Transactional
    public StatusResponse register(RegDataRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();
        String phone = request.getPhone();
        String name = request.getName();
        Role role = request.getType() == null ? Role.PARTNER : request.getType();
        try {
            userService.findUserByLogin(email);
            return StatusResponse.builder()
                    .success(false).errors(List.of("Пользователь с указанным именем существует. Выберите другое имя")).build();

        } catch (RuntimeException e) {
            List<String> errors = new ArrayList<>(4);
            if (StringUtils.isBlank(email)) {
                errors.add("Поле ЭЛЕКТРОНАЯ ПОЧТА должно быть заполнено");
            }
            if (StringUtils.isBlank(password)) {
                errors.add("Поле ПАРОЛЬ должно быть заполнено");
            }
            if (StringUtils.isBlank(name)) {
                errors.add(String.format("Поле %s должно быть заполнено", role == Role.PARTNER ? "НАИМЕНОВАНИЕ" : "ИМЯ"));
            }
            if (StringUtils.isBlank(phone)) {
                errors.add("Поле ТЕЛЕФОН должно быть заполнено");
            }
            if (!errors.isEmpty()) {
                throw new NullValueFieldException(errors);
            }
            Long id;
            if (role == Role.PARTNER) {
                id = partnerService.createNewPartner(email, phone, name);
                userService.createUser(email, passwordEncoder.encode(password), Role.PARTNER, id);
            } else {
                id = clientService.createNewClient(email, phone, name);
                userService.createUser(email, passwordEncoder.encode(password), Role.CLIENT, id);
            }
            return StatusResponse.builder()
                    .success(true).build();
        }
    }
}
