package ru.tyreservice.aggregator.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.tyreservice.aggregator.dto.requests.CostWorkRequestDTO;
import ru.tyreservice.aggregator.dto.requests.WorkRequestDTO;
import ru.tyreservice.aggregator.dto.responses.CostWorkResponseDTO;
import ru.tyreservice.aggregator.dto.responses.WorkResponseDTO;
import ru.tyreservice.aggregator.services.CostWorkService;
import ru.tyreservice.aggregator.services.WorkService;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "api/works")
@Tag(name = "Обработка запросов по справочнику услуг",
        description = "Общий справочник. Создание и получени списка услуг")
public class WorkController {
    private static final String REQUEST = "Request: %s http://localhost:8080/api/works";
    private static final String GET = "GET";
    private static final String POST = "POST";

    private final WorkService workService;

    @PostMapping()
    @Operation(summary = "Создание услуги", description = "Создание услуги с подробным описанием")
    public WorkResponseDTO createWork(
            @RequestBody WorkRequestDTO work) {
        log.info(String.format(REQUEST, POST));
        return workService.createWork(work);
    }

    @GetMapping()
    @Operation(summary = "Получение списка услуг", description = "Получение списка услуг из общего справочника")
    public List<WorkResponseDTO> readListWorks() {
        log.info(String.format(REQUEST, GET));
        return workService.readListWorks();
    }
}
