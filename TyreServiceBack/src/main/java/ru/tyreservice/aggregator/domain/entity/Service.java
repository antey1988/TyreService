package ru.tyreservice.aggregator.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tyreservice.aggregator.domain.enums.StateCarType;
import ru.tyreservice.aggregator.domain.enums.StateWheelType;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToMany(mappedBy = "services")
    private List<Orders> orders;

    @OneToOne(mappedBy = "servicePhoto", fetch = FetchType.EAGER)
    private FileInfo fileInfo;

    @Enumerated(value = EnumType.STRING)
    private StateCarType carType;

    @Enumerated(value = EnumType.STRING)
    private StateWheelType stateWheelType;

}
