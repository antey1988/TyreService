package ru.tyreservice.aggregator.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tyreservice.aggregator.dto.requests.CostWorkRequestDTO;
import ru.tyreservice.aggregator.dto.responses.CostWorkResponseDTO;
import ru.tyreservice.aggregator.entities.CostWork;
import ru.tyreservice.aggregator.entities.PartnerNew;
import ru.tyreservice.aggregator.entities.Work;
import ru.tyreservice.aggregator.repositories.CostWorkRepository;
import ru.tyreservice.aggregator.repositories.PartnerNewRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class CostWorkServiceImpl implements CostWorkService {
    private final CostWorkRepository costWorkRepository;

    @Override
    public List<CostWorkResponseDTO> readCostsWorks(Long id) {
        List<CostWork> listEntities = costWorkRepository.findAllByPartnerId(id);
        List<CostWorkResponseDTO> listResponse = listEntities.stream()
                .map(CostWorkResponseDTO::fromEntity).collect(Collectors.toList());
        return listResponse;
    }

    @Override
    public List<CostWorkResponseDTO> createAndUpdateCostsWorks(Long id, List<CostWorkRequestDTO> costWorkRequestDTOs) {
        List<CostWork> costsWorks = costWorkRequestDTOs.stream()
                .map(cw -> CostWorkRequestDTO.toEntity(id, cw)).collect(Collectors.toList());
        return costWorkRepository.saveAll(costsWorks).stream()
                .map(CostWorkResponseDTO::fromEntity).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public void deleteCostsWorks(Long id, List<Long> works) {
        costWorkRepository.deleteAllByPartnerIdAndWorkIdIn(id, works);
    }

}
