package ru.tyreservice.aggregator.dto.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.tyreservice.aggregator.enums.Role;

@Slf4j
@Getter@Setter
public class RegDataRequest {
    private String email;
    private String password;
    private String name;
    private String phone;
    private Role type;
}
