package ru.tyreservice.aggregator.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.tyreservice.aggregator.dto.responses.PartnerResponseDTO;
import ru.tyreservice.aggregator.dto.responses.PartnerWithWorksResponseDTO;
import ru.tyreservice.aggregator.enums.StateCarType;
import ru.tyreservice.aggregator.services.PartnerService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "api")
@Tag(name = "Запросы, не требующие авторизации")
public class PartnerController {
    private static final String REQUEST = "Request: GET http://localhost:8080/api/partners%s";

    private final PartnerService partnerService;

    @GetMapping(value = "/partners")
    @Operation(summary = "Список партнеров", description = "Получение списка партнеров с возможностью фильтрации по типу сервиса, наименованию сервиса и типу оказываемой услуги")
    public List<PartnerResponseDTO> readListPartnersByFilter(
            @RequestParam(required = false) StateCarType type,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) List<Long> id,
            @RequestParam(required = false) Integer page,
            @RequestParam Double latitude,
            @RequestParam Double longitude) {
        List<String> params = new ArrayList<>(6);
        params.add("latitude="+latitude);
        params.add("longitude="+longitude);
        if (type != null) params.add("type="+type);
        if (name != null) params.add("name="+name);
        if (id != null) params.add(id.stream().map(i ->"id="+i).collect(Collectors.joining("&", "", "")));
        if (page != null) params.add("page="+page);
        log.info(String.format(REQUEST, params.stream().collect(Collectors.joining("&", "?", ""))));
        return partnerService.readListPartners(type, name, id , page, latitude, longitude);
    }

    @GetMapping(value = "/partners/{id}")
    @Operation(summary = "Информация о партнере", description = "Подробная информация об одном партнере, в том числе со списком информации об оказываемых услугах с их стоимостью")
    public PartnerWithWorksResponseDTO readPartnerWithWorks(
            @PathVariable(name = "id") Long id) {
        log.info(String.format(REQUEST, "/" + id));
        PartnerWithWorksResponseDTO partner = partnerService.readPartnerWithWorks(id);
        return partner;
    }
}
