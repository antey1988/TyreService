package ru.tyreservice.aggregator.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tyreservice.aggregator.enums.StateStatus;
import ru.tyreservice.aggregator.dto.requests.OrderRequestDTO;
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
    @Transactional
    public void changeStatus(Long id, StateStatus status) {
        Order order = readById(id);
        order.setStatus(status);
    }

    @Override
    public List<OrderResponseDTO> readListOrders(Long id) {
        List<Order> listEntities = orderRepository.findAllByPartnerId(id);
        return listEntities.stream().map(OrderResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void createOrder(OrderRequestDTO orderRequest) {
        Order order = OrderRequestDTO.toEntity(orderRequest);
        orderRepository.save(order);
    }

    private Order readById(Long id) {
        return orderRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
