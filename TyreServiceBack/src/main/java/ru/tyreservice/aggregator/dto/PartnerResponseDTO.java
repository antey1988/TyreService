package ru.tyreservice.aggregator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.tyreservice.aggregator.domain.entity.Partner;

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
    private Integer rank;
    private String address;
    private Double latitude;
    private Double longitude;

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
                partner.getLongitude()
        );
    }
}