package ru.tyreservice.aggregator.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tyreservice.aggregator.domain.entity.Partner;
import ru.tyreservice.aggregator.dto.PartnerResponseDTO;
import ru.tyreservice.aggregator.repositories.PartnerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class PartnerServiceImpl implements PartnerService {

    private final PartnerRepository partnerRepository;

    @Override
    public List<PartnerResponseDTO> getPartners(String name) {
        List<Partner> partnersEntity = partnerRepository.findAll();
        List<PartnerResponseDTO> dtoEntity = partnersEntity.stream().map(PartnerResponseDTO::of).collect(Collectors.toList());
        return dtoEntity;
    }
}
