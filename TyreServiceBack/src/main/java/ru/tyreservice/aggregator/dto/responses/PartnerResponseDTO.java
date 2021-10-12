package ru.tyreservice.aggregator.dto.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.tyreservice.aggregator.entities.Partner;
import ru.tyreservice.aggregator.enums.StateCarType;

@AllArgsConstructor
@Getter
@Setter
@Slf4j
@Schema(description = "Краткая информация о партнере для списка")
public class PartnerResponseDTO {
    @Schema(description = "Идентификатор партнера")
    private Long id;
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
    @Schema(description = "Ранг")
    private Double rank;
    @Schema(description = "Адрес")
    private String address;
    @Schema(description = "Координаты. Широта")
    private Double latitude;
    @Schema(description = "Координаты. Долгота")
    private Double longitude;
    @Schema(description = "Тип сервиса")
    private StateCarType carType;

    public static PartnerResponseDTO fromEntity(Partner partner) {
        return new PartnerResponseDTO(
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
                partner.getCarType()
        );
    }
}