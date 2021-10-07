package ru.tyreservice.aggregator.services;

import ru.tyreservice.aggregator.dto.CostWorkRequestDTO;
import ru.tyreservice.aggregator.dto.CostWorkResponseDTO;

import java.util.List;
import java.util.Set;

public interface CostWorkService {
    Set<CostWorkResponseDTO> createCostsWorks(Long id, List<CostWorkRequestDTO> costWorkRequestDTOs);
}
