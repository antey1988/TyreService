package ru.tyreservice.aggregator.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.tyreservice.aggregator.dto.CostWorkRequestDTO;
import ru.tyreservice.aggregator.dto.CostWorkResponseDTO;
import ru.tyreservice.aggregator.services.CostWorkService;

import java.util.List;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "api")
public class CostWorkController {
    private static final String REQUEST = "Request: %s http://localhost:8080/api/%s";
    private static final String GET = "GET";
    private static final String POST = "POST";

    private final CostWorkService costWorkService;

    @PostMapping(value = "/partners/{id}/works")
    public Set<CostWorkResponseDTO> addWorks(
            @PathVariable Long id,
            @RequestBody List<CostWorkRequestDTO> works) {
        String params = String.format("/partners/%d/works", id);
        log.info(String.format(REQUEST, POST, params));
        return costWorkService.createCostsWorks(id, works);
    }
}
