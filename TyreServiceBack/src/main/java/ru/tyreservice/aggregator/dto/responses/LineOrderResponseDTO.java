package ru.tyreservice.aggregator.dto.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.tyreservice.aggregator.entities.LineOrder;

@Getter
@Setter
@Slf4j
public class LineOrderResponseDTO {
    private Long id;
    //наименование услуги из общего справочника
    private String name;
    //стоимость услуги из справочника партнера
    private Integer price;
    private Integer count;

    public static LineOrderResponseDTO fromEntity(LineOrder lineOrder) {
        LineOrderResponseDTO line = new LineOrderResponseDTO();
        line.setId(lineOrder.getWork().getId());
        line.setName(lineOrder.getWork().getName());
        line.setPrice(lineOrder.getPrice());
        line.setCount(lineOrder.getCount());
        return line;
    }
}
