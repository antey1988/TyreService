package ru.tyreservice.aggregator.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.tyreservice.aggregator.domain.enums.StateCarType;
import ru.tyreservice.aggregator.entities.PartnerNew;

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
