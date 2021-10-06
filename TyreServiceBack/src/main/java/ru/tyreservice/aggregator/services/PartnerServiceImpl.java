package ru.tyreservice.aggregator.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tyreservice.aggregator.dto.PartnerResponseDTO;
import ru.tyreservice.aggregator.entities.Partner;
import ru.tyreservice.aggregator.repositories.PartnerRepository;
import ru.tyreservice.aggregator.utils.PartnerRepositoryStub;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class PartnerServiceImpl implements PartnerService {
    private final PartnerRepositoryStub partnerRepositoryStub;
    private final PartnerRepository partnerRepository;

    @Override
    public List<PartnerResponseDTO> getPartners(String name) {
        List<Partner> list = partnerRepository.findAll();
        return partnerRepositoryStub.getPartners(name);
    }
}
