package ru.tyreservice.aggregator.enums;

import java.util.List;

public enum StateStatus {
    CANCEL, DONE, WAITING;

    public static List<StateStatus> getListStatus() {
        return List.of(values());
    }
}
