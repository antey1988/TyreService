package ru.tyreservice.aggregator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ru.tyreservice.aggregator.utils.PartnerRepositoryStub;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Slf4j
public class PartnerResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String email;
    private String date_work;
    private String phone_number;
    private Integer rank;
    private String address;
    private Double latitude;
    private Double longitude;
}

