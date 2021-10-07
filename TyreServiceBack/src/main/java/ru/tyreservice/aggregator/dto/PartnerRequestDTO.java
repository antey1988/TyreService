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


//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(nullable = false)
//    private String name;
//    private String description;
//    @Column(nullable = false)
//    private String address;
//    @Column(nullable = false)
//    private String email;
//    @Column(nullable = false)
//    private String phone;
//    @Column(nullable = false)
//    private String password;
//    @Column(nullable = false)
//    private String schedule;
//    private Double rank;
//    private Double latitude;
//    private Double longitude;
//    @Column(nullable = false, name = "type")
//    @Enumerated(value = EnumType.STRING)
//    private StateCarType carType;
//    @JsonIgnore
//    @OneToMany(mappedBy = "partner")
//    private Set<CostWork> costsWorks;
}
