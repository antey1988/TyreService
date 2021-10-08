package ru.tyreservice.aggregator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.tyreservice.aggregator.dto.PartnerForUserDTO;
import ru.tyreservice.aggregator.services.PartnerForUserService;


@Controller
public class PartnerOpenController {

    @Autowired
    private PartnerForUserService partnerService;

    @GetMapping("/service/{name}")
    public ResponseEntity<PartnerForUserDTO> getPartnerPage(@PathVariable("name") String name) {
        PartnerForUserDTO foundPartner = partnerService.getPartnerForUser(name);
        if (foundPartner == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundPartner);
        }
    }
}
