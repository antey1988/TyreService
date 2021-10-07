package ru.tyreservice.aggregator.services;

import ru.tyreservice.aggregator.domain.enums.StateCarType;
import ru.tyreservice.aggregator.dto.PartnerRequestDTO;
import ru.tyreservice.aggregator.dto.PartnerResponseDTO;
import ru.tyreservice.aggregator.dto.PartnerWithWorksResponseDTO;

import java.util.List;

public interface PartnerService {
    List<PartnerResponseDTO> getPartners(String nameService, Integer page, StateCarType type);
    List<PartnerResponseDTO> readListPartners(String nameService, Integer page, StateCarType type);
    PartnerWithWorksResponseDTO readPartnerWithWorks(Long id);
    PartnerWithWorksResponseDTO createPartnerWithWorks(PartnerRequestDTO partnerRequestDTO);
    PartnerWithWorksResponseDTO updatePartnerWithWorks(Long id, PartnerRequestDTO partnerRequestDTO);
}
