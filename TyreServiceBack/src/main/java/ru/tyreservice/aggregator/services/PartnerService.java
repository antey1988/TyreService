package ru.tyreservice.aggregator.services;

import ru.tyreservice.aggregator.enums.StateCarType;
import ru.tyreservice.aggregator.dto.requests.PartnerRequestDTO;
import ru.tyreservice.aggregator.dto.responses.PartnerResponseDTO;
import ru.tyreservice.aggregator.dto.responses.PartnerWithWorksResponseDTO;

import java.util.List;

public interface PartnerService {
    List<PartnerResponseDTO> readListPartners(StateCarType type, String name, Long id, Integer page);
    PartnerWithWorksResponseDTO readPartnerWithWorks(Long id);
    PartnerWithWorksResponseDTO createPartner(PartnerRequestDTO partnerRequestDTO);
    PartnerWithWorksResponseDTO updatePartner(Long id, PartnerRequestDTO partnerRequestDTO);
}
