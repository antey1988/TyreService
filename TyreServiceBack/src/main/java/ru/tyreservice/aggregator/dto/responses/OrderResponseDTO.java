package ru.tyreservice.aggregator.dto.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.tyreservice.aggregator.domain.enums.StateStatus;
import ru.tyreservice.aggregator.entities.Order;

import java.sql.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Slf4j
public class OrderResponseDTO {
    //Основная информация о заказе
    private Long id;
    private Long partnerId;
    private String partner_name;
    private Date createDate;
    private Date bookingDate;
    private StateStatus status;
    //инфомация о клиенте
    private String clientName;
    private String clientPhone;
    private String auto;
    //полная стоимость заказа
    private Integer fullPrice;
    //табличная часть заказа, строки с наименованием услуги, стоимостью и количеством
    Set<LineOrderResponseDTO> lines;

    public static OrderResponseDTO fromEntity(Order order) {
        OrderResponseDTO orderResponse = new OrderResponseDTO();
        orderResponse.setId(order.getId());
        orderResponse.setPartnerId(order.getPartner().getId());
        orderResponse.setPartner_name(order.getPartner().getName());
        orderResponse.setCreateDate(order.getCreateDate());
        orderResponse.setBookingDate((order.getBookingDate()));
        orderResponse.setStatus(order.getStatus());
        orderResponse.setClientName(order.getClientName());
        orderResponse.setClientPhone(order.getClientPhone());
        orderResponse.setAuto(order.getAuto());
        orderResponse.setFullPrice(orderResponse.getFullPrice());
        orderResponse.setLines(order.getLines().stream().map(LineOrderResponseDTO::fromEntity).collect(Collectors.toSet()));
        return orderResponse;
    }
}
