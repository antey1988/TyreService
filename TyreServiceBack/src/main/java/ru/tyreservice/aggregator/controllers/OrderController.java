package ru.tyreservice.aggregator.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.tyreservice.aggregator.enums.StateStatus;
import ru.tyreservice.aggregator.dto.requests.OrderRequestDTO;
import ru.tyreservice.aggregator.dto.responses.OrderResponseDTO;
import ru.tyreservice.aggregator.services.OrderService;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "api")
@Tag(name = "Обработка запросов по заказам", description = "Создание, изменение статуса и просмотр информации о заказах")
public class OrderController {
    private static final String REQUEST = "Request: %s http://localhost:8080/api%s";
    private static final String GET = "GET";
    private static final String POST = "POST";

    private final OrderService orderService;

    @GetMapping(value = "/partners/{id}/orders")
    @Operation(summary = "Список заказов", description = "Список всех заказов конкретного партнера, доступны для просмотра из кабинета партнера")
    public List<OrderResponseDTO> readListOrders(
            @PathVariable(name = "id") Long idPartner,
            @RequestParam(required = false) StateStatus status) {
        String param1 = String.format("/partners/%d/orders", idPartner);
        String params = status != null ? param1+"?status="+status : param1;
        log.info(String.format(REQUEST, GET, params));
        return orderService.readListOrders(idPartner);
    }

    @PostMapping(value = "/orders")
    @Operation(summary = "Создание заказа", description = "Создание заказа на выполение услуг, доступно всем пользователям")
    public void createOrder(
            @RequestBody OrderRequestDTO orderRequest) {
        String params = String.format("/orders");
        log.info(String.format(REQUEST, POST, params));
        orderService.createOrder(orderRequest);
    }

    @PostMapping(value = "/orders/{id}")
    @Operation(summary = "Изменение статуса", description = "Изменение статуса заказа, производится из кабинета партнера")
    public void updateStatusOrder(
            @RequestBody StateStatus status,
            @PathVariable(name = "id") Long id) {
        String params = String.format("/orders/%d", id);
        log.info(String.format(REQUEST, POST, params));
        orderService.changeStatus(id, status);
    }
}
