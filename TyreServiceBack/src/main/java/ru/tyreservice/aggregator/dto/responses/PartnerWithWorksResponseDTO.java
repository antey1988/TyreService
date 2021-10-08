package ru.tyreservice.aggregator.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.tyreservice.aggregator.domain.enums.StateCarType;
import ru.tyreservice.aggregator.entities.PartnerNew;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
@Slf4j
public class PartnerWithWorksResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String email;
    private String date_work;
    private String phone_number;
    private Double rank;
    private String address;
    private Double latitude;
    private Double longitude;
    private StateCarType carType;
    private Set<CostWorkResponseDTO> works;

    public static PartnerWithWorksResponseDTO of(PartnerNew partner) {
        return new PartnerWithWorksResponseDTO(
                partner.getId(),
                partner.getName(),
                partner.getDescription(),
                partner.getEmail(),
                partner.getSchedule(),
                partner.getPhone(),
                partner.getRank(),
                partner.getAddress(),
                partner.getLatitude(),
                partner.getLongitude(),
                partner.getCarType(),
                partner.getCostsWorks().stream().map(CostWorkResponseDTO::fromEntity).collect(Collectors.toSet())
        );
    }
}