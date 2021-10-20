package ru.tyreservice.aggregator.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.tyreservice.aggregator.dto.requests.RegDataRequest;
import ru.tyreservice.aggregator.dto.responses.StatusResponse;
import ru.tyreservice.aggregator.services.RegistrationService;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "api")
@Tag(name = "Запросы, не требующие авторизации")
public class RegistrationController {
    private RegistrationService registerService;

    @Operation(summary = "Регистрация нового партнера или клиента",
            description = "4 поля обязательные к заполнению при регистрации: логин, пароль, наименование организации или имя клиента, телефон " +
                    "Если будут не заполнено хотя бы одно поле, регистрация будет отклонена с указанием причины). " +
                    "Тип личного кабинета(PARTNER/CLIENT, пока ничего не присылаем, по умолчанию партнер). ")
    @PostMapping(value = "/registration")
    public StatusResponse register(@RequestBody RegDataRequest regData) {
        return registerService.register(regData);
    }
}
