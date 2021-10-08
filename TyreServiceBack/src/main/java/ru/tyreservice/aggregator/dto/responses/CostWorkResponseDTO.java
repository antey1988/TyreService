package ru.tyreservice.aggregator.dto.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.tyreservice.aggregator.entities.CostWork;

@Getter
@Setter
@Slf4j
public class CostWorkResponseDTO {
    //id и наименование услуги из общего справочника услуг
    private Long id;
    private String name;
    //стоимость услуги, которую устанавливает партнер
    private Long price;

    public static CostWorkResponseDTO fromEntity(CostWork costWork) {
        CostWorkResponseDTO costWorkResponse = new CostWorkResponseDTO();
        costWorkResponse.setId(costWork.getWork().getId());
        costWorkResponse.setName(costWork.getWork().getName());
        costWorkResponse.setPrice(costWork.getPrice());
        return costWorkResponse;
    }
}
