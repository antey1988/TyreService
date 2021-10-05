package ru.tyreservice.aggregator.services;

import ru.tyreservice.aggregator.dto.PartnerResponseDTO;

import java.util.List;

public interface PartnerService {
    List<PartnerResponseDTO> getPartners(String name);
}
