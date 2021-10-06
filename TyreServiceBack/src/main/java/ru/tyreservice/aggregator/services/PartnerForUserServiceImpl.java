package ru.tyreservice.aggregator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import ru.tyreservice.aggregator.domain.entity.Partner;
import ru.tyreservice.aggregator.dto.PartnerForUserDTO;
import ru.tyreservice.aggregator.dto.PartnerResponseDTO;
import ru.tyreservice.aggregator.repositories.PartnerRepository;

import java.util.List;

@Component
public class PartnerForUserServiceImpl implements PartnerForUserService {

    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public PartnerForUserDTO getPartnerForUser(String name) {
        Partner partner = partnerRepository.getFirstByName(name);
        return PartnerForUserDTO.of(partner);
    }
}
