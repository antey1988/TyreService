package ru.tyreservice.aggregator.services;

import ru.tyreservice.aggregator.dto.responses.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    List<OrderResponseDTO> readListOrders(Long id);
}
