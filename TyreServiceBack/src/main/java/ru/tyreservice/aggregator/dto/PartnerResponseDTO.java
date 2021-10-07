package ru.tyreservice.aggregator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.tyreservice.aggregator.domain.entity.Partner;
import ru.tyreservice.aggregator.domain.enums.StateCarType;

@AllArgsConstructor
@Getter
@Setter
@Slf4j
public class PartnerResponseDTO {
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

    public static PartnerResponseDTO of(Partner partner) {
        return new PartnerResponseDTO(
                partner.getId(),
                partner.getName(),
                partner.getDescription(),
                partner.getEmail(),
                partner.getDateWork(),
                partner.getPhoneNumber(),
                partner.getRank(),
                partner.getAddress(),
                partner.getLatitude(),
                partner.getLongitude(),
                partner.getCarType()
        );
    }
}