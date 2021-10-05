package ru.tyreservice.aggregator.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.tyreservice.aggregator.utils.PartnerResponseDTO;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "api/partners")
public class PartnerController {
    private static final String REQUEST = "Request: %s http://localhost:8080/api/partners%s";
    private static final String GET = "GET";
    private static final String POST = "POST";

    @GetMapping
    public List<PartnerResponseDTO> getPartnersByFilter(
            @RequestParam(required = false) String name) {
        String params = name != null ? "?name="+name : "";
        log.info(String.format(REQUEST, GET, params));
        return PartnerResponseDTO.getPartnersByFilter(name);
    }
}
