package ru.tyreservice.aggregator.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.tyreservice.aggregator.domain.enums.StateCarType;
import ru.tyreservice.aggregator.dto.PartnerResponseDTO;
import ru.tyreservice.aggregator.services.PartnerService;
import ru.tyreservice.aggregator.utils.PartnerRepositoryStub;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "api/partners")
public class PartnerController {
    private static final String REQUEST = "Request: %s http://localhost:8080/api/partners%s";
    private static final String GET = "GET";
    private static final String POST = "POST";

    private final PartnerService partnerService;

    @GetMapping
    public List<PartnerResponseDTO> getPartnersByFilter(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) StateCarType type,
            @RequestParam(required = false) Integer page) {
        String params = name != null ? "?name="+name : "";
        log.info(String.format(REQUEST, GET, params));
        return partnerService.getPartners(name, page, type);
    }
}
