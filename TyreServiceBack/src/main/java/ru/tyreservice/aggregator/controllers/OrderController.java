package ru.tyreservice.aggregator.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.tyreservice.aggregator.domain.enums.StateCarType;
import ru.tyreservice.aggregator.domain.enums.StateStatus;
import ru.tyreservice.aggregator.dto.requests.PartnerRequestDTO;
import ru.tyreservice.aggregator.dto.responses.OrderResponseDTO;
import ru.tyreservice.aggregator.dto.responses.PartnerResponseDTO;
import ru.tyreservice.aggregator.dto.responses.PartnerWithWorksResponseDTO;
import ru.tyreservice.aggregator.services.OrderService;
import ru.tyreservice.aggregator.services.PartnerService;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "api")
public class OrderController {
    private static final String REQUEST = "Request: %s http://localhost:8080/api/partners%s";
    private static final String GET = "GET";
    private static final String POST = "POST";

    private final OrderService orderService;

    @GetMapping(value = "/partners/{id}/orders")
    public List<OrderResponseDTO> readListOrders(
            @RequestParam(required = false) StateStatus status,
            @PathVariable(name = "id") Long idPartner) {
        String param1 =String.format("/%d/orders", idPartner);
        String params = status != null ? param1+"?status="+status : param1;
        log.info(String.format(REQUEST, GET, params));
        return orderService.readListOrders(idPartner);
    }
}
