package ru.tyreservice.aggregator.dto.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
@Setter
@Builder
public class StatusResponse {
    private boolean success;
    private List<String> errors;
}
