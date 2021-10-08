package ru.tyreservice.aggregator.services;

import ru.tyreservice.aggregator.dto.requests.CostWorkRequestDTO;
import ru.tyreservice.aggregator.dto.responses.CostWorkResponseDTO;

import java.util.List;
import java.util.Set;

public interface CostWorkService {
    List<CostWorkResponseDTO> createCostsWorks(Long id, List<CostWorkRequestDTO> costWorkRequestDTOs);
    List<CostWorkResponseDTO> readCostsWorks(Long id);
}
