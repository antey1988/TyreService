package ru.tyreservice.aggregator.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.tyreservice.aggregator.domain.enums.StateCarType;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String description;
    private String email;
    private String password;
    private String phoneNumber;
    private String dateWork;
    private Double rank;
    private Double latitude;
    private Double longitude;


    @JsonIgnore
    @OneToMany(mappedBy = "partnerName")
    private List<Service> services;

    @JsonIgnore
    @OneToMany(mappedBy = "partner")
    private List<Orders> orders;

    @Enumerated(value = EnumType.STRING)
    private StateCarType carType;
}
