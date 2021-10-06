package ru.tyreservice.aggregator.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Entity
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
    private Integer rank;
    private Double latitude;
    private Double longitude;

    @Transient
//    @OneToMany(mappedBy = "partnerName", fetch = FetchType.EAGER)
    private List<Service> services;

    @Transient
//    @OneToMany(mappedBy = "partner", fetch = FetchType.EAGER)
    private List<Orders> orders;

//    @OneToOne(mappedBy = "partnerPhoto", fetch = FetchType.EAGER)
    private FileInfo fileInfo;
}
