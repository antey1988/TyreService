package ru.tyreservice.aggregator.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter@Setter
@Embeddable
@Schema(description = "Отзыв")
public class Review {
    @Column(nullable = false)
    @Schema(description = "Оценка")
    private Integer ball;
    @Column(nullable = false)
    @Schema(description = "Сообщение")
    private String message;
    @Column(nullable = false)
    @Schema(description = "Имя клиента")
    private String name;
    @Column(nullable = false)
    @Schema(description = "Дата отзыва")
    private Date date;
}
