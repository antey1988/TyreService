package ru.tyreservice.aggregator.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.tyreservice.aggregator.dto.PartnerForUserDTO;
import ru.tyreservice.aggregator.dto.PartnerResponseDTO;
import ru.tyreservice.aggregator.repositories.PartnerRepository;
import ru.tyreservice.aggregator.utils.PartnerRepositoryStub;

import java.util.Collections;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class PartnerServiceImpl implements PartnerService {

    private final PartnerRepositoryStub partnerRepositoryStub;

    @Override
    public List<PartnerResponseDTO> getPartners(String name) {
        return partnerRepositoryStub.getPartners(name);
    }
}
