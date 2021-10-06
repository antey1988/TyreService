package ru.tyreservice.aggregator.services;

import ru.tyreservice.aggregator.domain.enums.StateCarType;
import ru.tyreservice.aggregator.dto.PartnerResponseDTO;

import java.util.List;

public interface PartnerService {
    List<PartnerResponseDTO> getPartners(String nameService, Integer page, StateCarType type);
}
