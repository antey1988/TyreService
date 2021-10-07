package ru.tyreservice.aggregator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.tyreservice.aggregator.entities.CostWork;
import ru.tyreservice.aggregator.entities.PartnerNew;
import ru.tyreservice.aggregator.entities.Work;

@AllArgsConstructor
@Getter
@Setter
@Slf4j
public class CostWorkRequestDTO {
    @JsonProperty(value = "work")
    private Long work_id;
    private Long price;

    public static Work of(CostWorkRequestDTO costWorkRequestDTO) {
        Work work = new Work();
        work.setId(costWorkRequestDTO.work_id);
        return work;
    }
}
