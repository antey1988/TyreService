package ru.tyreservice.aggregator.services;

import ru.tyreservice.aggregator.domain.enums.StateCarType;
import ru.tyreservice.aggregator.dto.CostWorkRequestDTO;
import ru.tyreservice.aggregator.dto.PartnerRequestDTO;
import ru.tyreservice.aggregator.dto.PartnerResponseDTO;
import ru.tyreservice.aggregator.dto.PartnerWithWorksResponseDTO;
import ru.tyreservice.aggregator.entities.CostWork;

import java.util.List;

public interface PartnerService {
    List<PartnerResponseDTO> getPartners(String nameService, Integer page, StateCarType type);
    List<PartnerResponseDTO> getListPartners(String nameService, Integer page, StateCarType type);
    PartnerWithWorksResponseDTO getPartnerWithWorks(Long id);
    PartnerWithWorksResponseDTO addWorks(Long id, List<CostWorkRequestDTO> costWorks);
}
