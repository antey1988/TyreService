package ru.tyreservice.aggregator.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class ClientDTO {
    private String name;
    private String email;
    private String phone;

}
