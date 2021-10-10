package ru.tyreservice.aggregator.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.tyreservice.aggregator.dto.requests.CostWorkRequestDTO;
import ru.tyreservice.aggregator.dto.responses.CostWorkResponseDTO;
import ru.tyreservice.aggregator.services.CostWorkService;

import java.util.List;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "api/partners/{id}/works")
@Tag(name = "Обработка запросов по услугам партнеров", description = "Создание, обновление, получение и удаление услуг партнера с их стоимостью")
public class CostWorkController {
    private static final String REQUEST = "Request: %s http://localhost:8080/api/partners/%d/works";
    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String DELETE = "DELETE";

    private final CostWorkService costWorkService;

    @PostMapping()
    @Operation(summary = "Создание и обновление списка услуг", description = "Создание списка услуг с их стоимость и обновление стоимости ранее созданных услуг")
    public List<CostWorkResponseDTO> createWorks(
            @PathVariable Long id,
            @RequestBody List<CostWorkRequestDTO> works) {
        log.info(String.format(REQUEST, POST, id));
        return costWorkService.createCostsWorks(id, works);
    }

    @GetMapping()
    @Operation(summary = "Получение списка услуг", description = "Получение списка услуг конкретного партнера")
    public List<CostWorkResponseDTO> readWorks(
            @PathVariable Long id) {
        log.info(String.format(REQUEST, GET, id));
        return costWorkService.readCostsWorks(id);
    }

    @DeleteMapping()
    @Operation(summary = "Удаление услуг", description = "Удаление каких-либо ранее указываемых услуг")
    public void deleteWorks(
            @PathVariable Long id,
            @RequestBody List<Long> works) {
        log.info(String.format(REQUEST, DELETE, id));
        costWorkService.deleteCostsWorks(id, works);
    }
}
