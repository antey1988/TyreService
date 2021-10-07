package ru.tyreservice.aggregator.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tyreservice.aggregator.dto.CostWorkRequestDTO;
import ru.tyreservice.aggregator.dto.CostWorkResponseDTO;
import ru.tyreservice.aggregator.entities.CostWork;
import ru.tyreservice.aggregator.entities.PartnerNew;
import ru.tyreservice.aggregator.entities.Work;
import ru.tyreservice.aggregator.repositories.CostWorkRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class CostWorkServiceImpl implements CostWorkService {

    private final CostWorkRepository costWorkRepository;

    @Override
    public Set<CostWorkResponseDTO> createCostsWorks(Long id, List<CostWorkRequestDTO> costWorkRequestDTOs) {
        PartnerNew partner = new PartnerNew();
        partner.setId(id);
        partner.setId(id);
        Set<CostWork> costsWorks = costWorkRequestDTOs.stream().map(w -> {
            Work work = CostWorkRequestDTO.of(w);
            CostWork costWork = new CostWork();
            costWork.setPartner(partner);
            costWork.setWork(work);
            costWork.setPrice(w.getPrice());
            return costWork;
        }).collect(Collectors.toSet());
        return costWorkRepository.saveAllAndFlush(costsWorks).stream().map(CostWorkResponseDTO::of).collect(Collectors.toSet());
    }
}
