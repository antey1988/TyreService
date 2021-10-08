package ru.tyreservice.aggregator.entities;

import lombok.Getter;
import lombok.Setter;
import ru.tyreservice.aggregator.domain.enums.StateStatus;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "orderss")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "partner_id")
    private PartnerNew partner;

    @Column(nullable = false, name = "create_date")
    private Date createDate;
    @Column(nullable = false, name = "booking_date")
    private Date bookingDate;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private StateStatus status;


    @Column(name = "client")
    private String clientName;
    @Column(nullable = false, name = "phone")
    private String clientPhone;
    @Column(name = "auto")
    private String auto;

    @Column(name = "price")
    private int fullPrice;
    @ElementCollection
    @CollectionTable(name = "lines_order",
            joinColumns = @JoinColumn(name = "order_id", nullable = false))
    Set<LineOrder> lines;
}
