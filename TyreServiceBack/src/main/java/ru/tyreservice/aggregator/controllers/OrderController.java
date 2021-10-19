package ru.tyreservice.aggregator.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tyreservice.aggregator.dto.requests.OrderRequestDTO;
import ru.tyreservice.aggregator.services.OrderService;

import static ru.tyreservice.aggregator.security.SecurityUtil.getAccount;
import static ru.tyreservice.aggregator.security.SecurityUtil.isAnonymous;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "api")
@Tag(name = "Запросы, не требующие авторизации")
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "/orders")
    @Operation(summary = "Создание заказа", description = "Создание заказа на выполнение услуг, доступно всем пользователям")
    public void createOrder(
            @RequestBody OrderRequestDTO orderRequest) {
        log.info("Request: POST http://localhost:8080/api/orders");
        if (isAnonymous()) {
            orderService.createOrder(orderRequest);
        } else {
            orderService.createOrder(orderRequest, getAccount().getAccountId());
        }
    }
}
