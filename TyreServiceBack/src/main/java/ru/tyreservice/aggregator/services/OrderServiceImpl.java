package ru.tyreservice.aggregator.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tyreservice.aggregator.dto.responses.OrderResponseDTO;
import ru.tyreservice.aggregator.entities.Order;
import ru.tyreservice.aggregator.repositories.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<OrderResponseDTO> readListOrders(Long id) {
        List<Order> listEntities = orderRepository.findAllByPartnerId(id);
        List<OrderResponseDTO> listResponseDTO = listEntities.stream().map(OrderResponseDTO::fromEntity)
                .collect(Collectors.toList());
        return listResponseDTO;
    }
}
