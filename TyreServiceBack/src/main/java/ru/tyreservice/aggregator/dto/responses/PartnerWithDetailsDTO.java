package ru.tyreservice.aggregator.dto.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.tyreservice.aggregator.entities.Partner;
import ru.tyreservice.aggregator.entities.Review;
import ru.tyreservice.aggregator.enums.StateCarType;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Schema(description = "ПОдробная информация о партнере с услугами")
public class PartnerWithDetailsDTO {
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
    @Schema(description = "Отзывы")
    private List<Review> reviews;
    @Schema(description = "Имя файла с изображением сервиса")
    private String imageName;

    public static PartnerWithDetailsDTO fromEntity(Partner partner) {
        return new PartnerWithDetailsDTO(
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
                partner.getCostsWorks().stream().map(CostWorkResponseDTO::fromEntity).collect(Collectors.toSet()),
                partner.getReviews(),
                partner.getImageName()
        );
    }
}