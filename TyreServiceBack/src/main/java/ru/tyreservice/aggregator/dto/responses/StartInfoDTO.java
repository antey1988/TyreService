package ru.tyreservice.aggregator.dto.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.tyreservice.aggregator.enums.EnumUtil;

import java.util.List;

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
