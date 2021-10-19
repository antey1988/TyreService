package ru.tyreservice.aggregator.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.tyreservice.aggregator.dto.responses.PartnerResponseDTO;
import ru.tyreservice.aggregator.dto.responses.PartnerWithWorksResponseDTO;
import ru.tyreservice.aggregator.dto.responses.StartInfoDTO;
import ru.tyreservice.aggregator.enums.StateCarType;
import ru.tyreservice.aggregator.services.PartnerService;
import ru.tyreservice.aggregator.services.StartService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "api")
@Tag(name = "Запросы, не требующие авторизации")
public class StartController {

    private final StartService startService;

    @GetMapping
    @Operation(summary = "Стартовая стракица", description = "Первичный запрос при запуске приложения. " +
            "Получение списка партнеров, списка всевозможных услуг, типов сервисов и типов учетных записей")
    public StartInfoDTO readStartInfo() {
        log.info("Request: GET http://localhost:8080/api");
        return startService.readStartInfo();
    }
}
