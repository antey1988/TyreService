package ru.tyreservice.aggregator.dto.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.tyreservice.aggregator.entities.LineOrder;
import ru.tyreservice.aggregator.entities.Work;

@Getter
@Setter
@Slf4j
public class LineOrderRequestDTO {
    //id услуги из общего справочника
    private Long id;
    //стоимость услуги из справочника партнера
    private Integer price;
    private Integer count;

    public static LineOrder toEntity(LineOrderRequestDTO lineOrder) {
        LineOrder line = new LineOrder();
        Work work = new Work();
        work.setId(lineOrder.getId());
        line.setWork(work);
        line.setPrice(line.getPrice());
        line.setCount(lineOrder.getCount());
        return line;
    }
}
