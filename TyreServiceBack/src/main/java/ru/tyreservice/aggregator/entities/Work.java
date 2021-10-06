package ru.tyreservice.aggregator.entities;

import ru.tyreservice.aggregator.domain.enums.StateWheelType;

import javax.persistence.*;

@Entity
@Table(name = "works")
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false, name = "type_wheel")
    @Enumerated(EnumType.STRING)
    private StateWheelType type_wheel;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public StateWheelType getType_wheel() {
        return this.type_wheel;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType_wheel(StateWheelType type_wheel) {
        this.type_wheel = type_wheel;
    }
}
