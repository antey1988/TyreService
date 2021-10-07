package ru.tyreservice.aggregator.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.tyreservice.aggregator.domain.enums.StateCarType;
import ru.tyreservice.aggregator.dto.CostWorkRequestDTO;
import ru.tyreservice.aggregator.dto.PartnerRequestDTO;
import ru.tyreservice.aggregator.dto.PartnerResponseDTO;
import ru.tyreservice.aggregator.dto.PartnerWithWorksResponseDTO;
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

//    @GetMapping(value = "/partners")
//    public List<PartnerResponseDTO> getPartnersByFilter(
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) StateCarType type,
//            @RequestParam(required = false) Integer page) {
//        String params = name != null ? "?name="+name : "";
//        log.info(String.format(REQUEST, GET, params));
//        return partnerService.getPartners(name, page, type);
//    }

    @GetMapping(value = "/partners")
    public List<PartnerResponseDTO> readListPartnersByFilter(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) StateCarType type,
            @RequestParam(required = false) Integer page) {
        String params = name != null ? "?name="+name : "";
        log.info(String.format(REQUEST, GET, params));
        return partnerService.getListPartners(name, page, type);
    }

    @GetMapping(value = "/partners/{id}")
    public PartnerWithWorksResponseDTO readPartnerWithWorks(
            @PathVariable(name = "id") Long id) {
        String params = "/"+id;
        log.info(String.format(REQUEST, GET, params));
        return partnerService.getPartnerWithWorks(id);
    }

    @PostMapping(value = "/partners/{id}/works")
    public PartnerWithWorksResponseDTO addWorks(
            @PathVariable Long id,
            @RequestBody List<CostWorkRequestDTO> works) {
        String params = String.format("/partners/%d/works", id);
        log.info(String.format(REQUEST, POST, params));
        return partnerService.addWorks(id, works);
    }
}
