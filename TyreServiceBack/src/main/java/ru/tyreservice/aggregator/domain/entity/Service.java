package ru.tyreservice.aggregator.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.tyreservice.aggregator.domain.enums.StateCarType;
import ru.tyreservice.aggregator.domain.enums.StateWheelType;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Long price;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Partner partnerName;

    @Enumerated(value = EnumType.STRING)
    private StateWheelType stateWheelType;

}
