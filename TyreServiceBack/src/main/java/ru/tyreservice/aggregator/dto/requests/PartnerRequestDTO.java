package ru.tyreservice.aggregator.dto.requests;

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
    private String schedule;
    private String phone;
    private String password;
    private String address;
    private Double latitude;
    private Double longitude;
    private StateCarType carType;
    private Set<CostWorkRequestDTO> works;

    public static PartnerNew onCreate(PartnerRequestDTO partner) {
        PartnerNew partnerNew = new PartnerNew();
        partnerNew.setName(partner.name);
        partnerNew.setAddress(partner.address);
        partnerNew.setEmail(partner.email);
        partnerNew.setPhone(partner.phone);
        partnerNew.setSchedule(partner.schedule);
        partnerNew.setPassword(partner.password);
        partnerNew.setCarType(partner.carType);
        return partnerNew;
    }
}
