package ru.tyreservice.aggregator.services;

import ru.tyreservice.aggregator.domain.enums.StateStatus;
import ru.tyreservice.aggregator.dto.requests.OrderRequestDTO;
import ru.tyreservice.aggregator.dto.responses.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    List<OrderResponseDTO> readListOrders(Long id);
    void changeStatus(Long id, StateStatus status);
    void createOrder(OrderRequestDTO orderRequest);
}
