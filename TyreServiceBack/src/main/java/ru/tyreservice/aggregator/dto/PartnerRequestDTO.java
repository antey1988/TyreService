package ru.tyreservice.aggregator.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.tyreservice.aggregator.domain.enums.StateCarType;
import ru.tyreservice.aggregator.entities.PartnerNew;

import java.util.Set;

@Getter
@Setter
@Slf4j
public class PartnerRequestDTO {
    private String name;
    private String description;
    private String email;
    private String date_work;
    private String phone_number;
    private String address;
    private Double latitude;
    private Double longitude;
    private StateCarType carType;
    private Set<CostWorkRequestDTO> works;

}
