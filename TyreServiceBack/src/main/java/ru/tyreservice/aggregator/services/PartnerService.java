package ru.tyreservice.aggregator.services;

import ru.tyreservice.aggregator.domain.enums.StateCarType;
import ru.tyreservice.aggregator.dto.requests.PartnerRequestDTO;
import ru.tyreservice.aggregator.dto.responses.PartnerResponseDTO;
import ru.tyreservice.aggregator.dto.responses.PartnerWithWorksResponseDTO;

import java.util.List;

public interface PartnerService {
    List<PartnerResponseDTO> getPartners(String nameService, Integer page, StateCarType type);
    List<PartnerResponseDTO> readListPartners(StateCarType type, String name, Long id, Integer page);
    PartnerWithWorksResponseDTO readPartnerWithWorks(Long id);
    PartnerWithWorksResponseDTO createPartnerWithWorks(PartnerRequestDTO partnerRequestDTO);
    PartnerWithWorksResponseDTO updatePartnerWithWorks(Long id, PartnerRequestDTO partnerRequestDTO);
}
