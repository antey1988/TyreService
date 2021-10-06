package ru.tyreservice.aggregator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tyreservice.aggregator.domain.entity.Partner;
import ru.tyreservice.aggregator.domain.entity.Service;
import ru.tyreservice.aggregator.domain.enums.StateCarType;

import javax.persistence.Entity;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartnerForUserDTO {
    private String name;
    private String address;
    private String description;
    private String email;
    private String phoneNumber;
    private String dateWork;
    private StateCarType carType;
    private Double rank;
    private Double latitude;
    private Double longitude;
    private List<Service> services;

    public static PartnerForUserDTO of(Partner partner) {
        return PartnerForUserDTO.builder()
                .name(partner.getName())
                .services(partner.getServices())
                .address(partner.getAddress())
                .description(partner.getDescription())
                .email(partner.getEmail())
                .phoneNumber(partner.getPhoneNumber())
                .dateWork(partner.getDateWork())
                .rank(partner.getRank())
                .latitude(partner.getLatitude())
                .longitude(partner.getLongitude())
                .services(partner.getServices())
                .carType(partner.getCarType())
                .build();
    }
}
