package ru.tyreservice.aggregator.entities;

public enum CarType {
    CAR("Легковой"), TRUCK("Грузовой"), BOTH("Легковой и грузовой");
    private String type;

    CarType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
