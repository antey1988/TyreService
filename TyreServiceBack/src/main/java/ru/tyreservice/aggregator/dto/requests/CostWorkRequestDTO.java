package ru.tyreservice.aggregator.dto.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.tyreservice.aggregator.entities.CostWork;
import ru.tyreservice.aggregator.entities.PartnerNew;
import ru.tyreservice.aggregator.entities.Work;

@Getter
@Setter
@Slf4j
public class CostWorkRequestDTO {
    //id услуги из общего справочника услуг
    private Long id;
    //стоимость услуги, которую устанавливает партнер
    private Long price;

    public static CostWork toEntity(Long partnerId, CostWorkRequestDTO costWorkRequestDTO) {
        Work work = new Work();
        work.setId(costWorkRequestDTO.id);
        PartnerNew partner = new PartnerNew();
        partner.setId(partnerId);
        CostWork costWork = new CostWork(partner, work, costWorkRequestDTO.getPrice());
        costWork.setPrice(costWorkRequestDTO.getPrice());
        return costWork;
    }
}
