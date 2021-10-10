package ru.tyreservice.aggregator.dto.responses;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "ПОдробная информация о партнере с услугами")
public class PartnerWithWorksResponseDTO {
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
    @Schema(description = "Список оказываемых услуг со стоимостью")
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