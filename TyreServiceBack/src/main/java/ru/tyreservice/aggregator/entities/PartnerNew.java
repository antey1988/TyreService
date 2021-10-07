package ru.tyreservice.aggregator.entities;

import lombok.Getter;
import lombok.Setter;
import ru.tyreservice.aggregator.domain.enums.StateCarType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "partners")
public class PartnerNew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String schedule;
    private Double rank;
    private Double latitude;
    private Double longitude;
    @Column(nullable = false, name = "type")
    @Enumerated(value = EnumType.STRING)
    private StateCarType carType;
    @OneToMany(mappedBy = "partner")
    private Set<CostWork> costsWorks = new HashSet<>();
}
