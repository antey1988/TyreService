package ru.tyreservice.aggregator.dto.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.tyreservice.aggregator.enums.EnumUtil;
import ru.tyreservice.aggregator.enums.StateCarType;
import ru.tyreservice.aggregator.enums.StateStatus;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Getter
@Schema
public class StartInfoDTO {
    private List<EnumUtil.KeyValue> types;
    private List<EnumUtil.KeyValue> statuses;
    private List<EnumUtil.KeyValue> lks;
    private List<WorkResponseDTO> works;
    private List<PartnerResponseDTO> partners;
}
