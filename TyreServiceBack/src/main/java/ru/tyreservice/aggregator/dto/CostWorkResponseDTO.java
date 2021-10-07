package ru.tyreservice.aggregator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.tyreservice.aggregator.entities.CostWork;
import ru.tyreservice.aggregator.entities.Work;

@AllArgsConstructor
@Getter
@Setter
@Slf4j
public class CostWorkResponseDTO {
    private Work work;
    private Long price;

    public static CostWorkResponseDTO of (CostWork costWork) {
        return new CostWorkResponseDTO(costWork.getWork(), costWork.getPrice());
    }
}
