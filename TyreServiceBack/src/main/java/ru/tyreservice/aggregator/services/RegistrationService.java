package ru.tyreservice.aggregator.services;

import ru.tyreservice.aggregator.dto.requests.RegDataRequest;
import ru.tyreservice.aggregator.dto.responses.StatusResponse;

public interface RegistrationService {
    StatusResponse register(RegDataRequest request);
}
