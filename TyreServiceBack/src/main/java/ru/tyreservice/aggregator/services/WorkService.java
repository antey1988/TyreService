package ru.tyreservice.aggregator.services;

import ru.tyreservice.aggregator.dto.requests.WorkRequestDTO;
import ru.tyreservice.aggregator.dto.responses.WorkResponseDTO;
import ru.tyreservice.aggregator.entities.Work;

import java.util.List;

public interface WorkService {
    List<WorkResponseDTO> readListWorks();
    WorkResponseDTO createWork(WorkRequestDTO requestDTO);
}
