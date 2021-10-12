package ru.tyreservice.aggregator.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.tyreservice.aggregator.entities.CostWork;
import ru.tyreservice.aggregator.entities.Partner;
import ru.tyreservice.aggregator.enums.StateCarType;

import java.util.Set;

@Getter
@Setter
@Slf4j
@Schema(description = "Информация о партнере")
public class PartnerRequestDTO {
    @Schema(description = "Наименование партнера")
    private String name;
    @Schema(description = "Описание о партнере")
    private String description;
    @Schema(description = "Электронная почта")
    private String email;
    @Schema(description = "Режим работы")
    private String schedule;
    @Schema(description = "Номер телефона")
    private String phone;
    @Schema(description = "Пароль")
    private String password;
    @Schema(description = "Адрес")
    private String address;
    @Schema(description = "Координаты. Широта")
    private Double latitude;
    @Schema(description = "Координаты. Долгота")
    private Double longitude;
    @Schema(description = "Тип сервиса")
    private StateCarType carType;
    @Schema(description = "Списк оказываемых услуг")
    private Set<CostWorkRequestDTO> works;

    public static Partner onCreate(PartnerRequestDTO partnerRequest) {
        Partner partnerNew = new Partner();
        partnerNew.setName(partnerRequest.name);
        partnerNew.setAddress(partnerRequest.address);
        partnerNew.setEmail(partnerRequest.email);
        partnerNew.setPhone(partnerRequest.phone);
        partnerNew.setSchedule(partnerRequest.schedule);
        partnerNew.setPassword(partnerRequest.password);
        partnerNew.setCarType(partnerRequest.carType);
        return partnerNew;
    }

    public static Partner onUpdate(Partner partner, PartnerRequestDTO partnerRequest) {
        partner.setName(partnerRequest.name);
        partner.setAddress(partnerRequest.address);
        partner.setEmail(partnerRequest.email);
        partner.setPhone(partnerRequest.phone);
        partner.setSchedule(partnerRequest.schedule);
        partner.setPassword(partnerRequest.password);
        partner.setCarType(partnerRequest.carType);
        Long id = partner.getId();
        Set<CostWork> costWorks = partner.getCostsWorks();
        costWorks.clear();
        partnerRequest.getWorks().forEach(cw -> costWorks.add(CostWorkRequestDTO.toEntity(id, cw)));
        return partner;
    }
}
