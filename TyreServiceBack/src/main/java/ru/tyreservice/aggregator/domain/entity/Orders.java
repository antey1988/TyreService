package ru.tyreservice.aggregator.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tyreservice.aggregator.domain.enums.StateStatus;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date create_date;
    private Date booking_date;
    private String price;
    private String carNumber;
    private String clientPhone;

    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;

    @Enumerated(value = EnumType.STRING)
    private StateStatus status;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_service",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<Service> services;
}
