package ru.tyreservice.aggregator.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum StateCarType {
    PASSENGER( "Легковые"),
    OFFROAD("Внедорожник"),
    TRUCK("Грузовой");

    private String name;

    StateCarType(String name) {
        this.name = name;
    }


    public static List<CarType> getListType() {
        return Arrays.stream(values()).map(StateCarType::getType).collect(Collectors.toList());
    }

    private CarType getType() {
        return new CarType(this.toString(), this.name);
    }

    @AllArgsConstructor
    @Getter
    public static class CarType {
        private String key;
        private String name;
    }
}
