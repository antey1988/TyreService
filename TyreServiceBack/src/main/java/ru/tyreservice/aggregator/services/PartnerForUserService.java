package ru.tyreservice.aggregator.services;

import ru.tyreservice.aggregator.dto.PartnerForUserDTO;

public interface PartnerForUserService {
    PartnerForUserDTO getPartnerForUser(String name);
}
