package ru.tyreservice.aggregator.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.tyreservice.aggregator.domain.enums.StateCarType;
import ru.tyreservice.aggregator.dto.requests.PartnerRequestDTO;
import ru.tyreservice.aggregator.dto.responses.PartnerResponseDTO;
import ru.tyreservice.aggregator.dto.responses.PartnerWithWorksResponseDTO;
import ru.tyreservice.aggregator.services.PartnerService;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "api")
@Tag(name = "Обработка запросов по партнерам", description = "Создание партнера, обновление и просмотр информации о партнере")
public class PartnerController {
    private static final String REQUEST = "Request: %s http://localhost:8080/api/partners%s";
    private static final String GET = "GET";
    private static final String POST = "POST";

    private final PartnerService partnerService;

    @GetMapping(value = "/partners")
    @Operation(summary = "Список партнеров", description = "Получение списка партнеров с возможностью фильтрации по типу сервиса, наименованию сервиса и типу оказываемой услуги")
    public List<PartnerResponseDTO> readListPartnersByFilter(
            @RequestParam(required = false) StateCarType type,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Integer page) {
        String params = name != null ? "?name="+name : "";
        log.info(String.format(REQUEST, GET, params));
        return partnerService.readListPartners(type, name, id , page);
    }

    @GetMapping(value = "/partners/{id}")
    @Operation(summary = "Информация о партнере", description = "Подробная информация об одном партнере, в том числе со списком информации об оказываемых услугах с их стоимостью")
    public PartnerWithWorksResponseDTO readPartnerWithWorks(
            @PathVariable(name = "id") Long id) {
        String params = "/"+id;
        log.info(String.format(REQUEST, GET, params));
        return partnerService.readPartnerWithWorks(id);
    }

    @PostMapping(value = "/partners")
    @Operation(summary = "Создание партнера", description = "Создание инфомации о партнере")
    public PartnerWithWorksResponseDTO createPartner(
            @RequestBody PartnerRequestDTO requestDTO) {
        String params = "";
        log.info(String.format(REQUEST, POST, params));
        return partnerService.createPartner(requestDTO);
    }

    @PostMapping(value = "/partners/{id}")
    @Operation(summary = "Обновление информации о партнера", description = "Обновление инфомации о партнере со списком оказывавемых услуг")
    public PartnerWithWorksResponseDTO updatePartner(
            @PathVariable Long id,
            @RequestBody PartnerRequestDTO requestDTO) {
        String params = "/"+id;
        log.info(String.format(REQUEST, POST, params));
        return partnerService.updatePartner(id, requestDTO);
    }
}
