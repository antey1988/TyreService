package ru.tyreservice.aggregator.dto.requests;

import lombok.Getter;
import lombok.Setter;
import ru.tyreservice.aggregator.enums.Role;

@Getter@Setter
public class RegDataRequest {
    private String email;
    private String password;
    private String name;
    private String phone;
    private Role type;
}
