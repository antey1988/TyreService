package ru.tyreservice.aggregator.dto.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.tyreservice.aggregator.domain.enums.StateStatus;
import ru.tyreservice.aggregator.entities.Order;
import ru.tyreservice.aggregator.entities.PartnerNew;

import java.sql.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Slf4j
public class OrderRequestDTO {
    //Основная информация о заказе
    private Long id;
    private Long partnerId;
    private Date createDate;
    private Date bookingDate;
    //информация о клиенте
    private String clientName;
    private String clientPhone;
    private String auto;
    //табличная часть заказа, строки с наименованием услуги, стоимостью и количеством
    Set<LineOrderRequestDTO> lines;

    public static Order toEntity(OrderRequestDTO orderRequest) {
        Order order = new Order();
        order.setId(orderRequest.getId());
        PartnerNew partner = new PartnerNew();
        partner.setId(orderRequest.getPartnerId());
        order.setCreateDate(orderRequest.getCreateDate());
        order.setBookingDate((orderRequest.getBookingDate()));
        order.setStatus(StateStatus.WAITING);
        order.setClientName(orderRequest.getClientName());
        order.setClientPhone(orderRequest.getClientPhone());
        order.setAuto(orderRequest.getAuto());
        order.setLines(orderRequest.getLines().stream().map(LineOrderRequestDTO::toEntity).collect(Collectors.toSet()));
        return order;
    }
}