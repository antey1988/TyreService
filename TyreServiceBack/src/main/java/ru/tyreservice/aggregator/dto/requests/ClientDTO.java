package ru.tyreservice.aggregator.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class ClientDTO {
    private String name;
    private String email;
    private String phone;
}
