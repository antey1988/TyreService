package ru.tyreservice.aggregator.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.tyreservice.aggregator.dto.PartnerResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PartnerRepositoryStub {

    private static List<PartnerResponseDTO> allPartners;
    static {
        allPartners = List.of(
                new PartnerResponseDTO(1L, "Рога и копыта", "Занимаемся подрезкой копыт и шлифовкой рогов",
                        "rog@gmail.com","08:00 - 22:00", "8-910-234-56-78", 3, "Адрес 1", 10.343, 12.435),
                new PartnerResponseDTO(2L, "Хвост и усы", "Занимаемся закручиванием усов и стрижкой хвостов",
                        "hvost@gmail.com", "08:00 - 22:00", "8-911-234-56-78", 3, "Адрес 2", 15.343, 17.435));
    }

    public List<PartnerResponseDTO> getPartners(String name) {
        List<PartnerResponseDTO> partners = new ArrayList<>(allPartners);
        if (name != null) {
            partners = getPartnersByName(partners, name);
        }
        return partners;
    }

    private List<PartnerResponseDTO> getPartnersByName(List<PartnerResponseDTO> allPartner,
                                                                 String name) {
        return allPartner.stream().filter(p -> {
            String partnerName = p.getName().toLowerCase();
            String parameter = ".*"+name.toLowerCase()+".*";
            return partnerName.matches(parameter);
        }).collect(Collectors.toList());
    }
}
