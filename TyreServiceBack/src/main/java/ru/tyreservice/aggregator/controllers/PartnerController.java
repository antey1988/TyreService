package ru.tyreservice.aggregator.controllers;

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
public class PartnerController {
    private static final String REQUEST = "Request: %s http://localhost:8080/api/partners%s";
    private static final String GET = "GET";
    private static final String POST = "POST";

    private final PartnerService partnerService;

    @GetMapping(value = "/partners")
    public List<PartnerResponseDTO> readListPartnersByFilter(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) StateCarType type,
            @RequestParam(required = false) Integer page) {
        String params = name != null ? "?name="+name : "";
        log.info(String.format(REQUEST, GET, params));
        return partnerService.readListPartners(name, page, type);
    }

    @GetMapping(value = "/partners/{id}")
    public PartnerWithWorksResponseDTO readPartnerWithWorks(
            @PathVariable(name = "id") Long id) {
        String params = "/"+id;
        log.info(String.format(REQUEST, GET, params));
        return partnerService.readPartnerWithWorks(id);
    }

    @PostMapping(value = "/partners")
    public PartnerWithWorksResponseDTO createPartner(
            @RequestBody PartnerRequestDTO requestDTO) {
        String params = "";
        log.info(String.format(REQUEST, POST, params));
        return partnerService.createPartnerWithWorks(requestDTO);
    }
}
