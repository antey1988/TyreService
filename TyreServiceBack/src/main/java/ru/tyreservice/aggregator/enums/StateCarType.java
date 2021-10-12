package ru.tyreservice.aggregator.enums;

import java.util.Arrays;
import java.util.List;

public enum StateCarType {
    PASSENGER, OFFROAD, COMMERCIAL;

    public static List<StateCarType> getListStatuses() {
        return Arrays.asList(values());
    }
}
