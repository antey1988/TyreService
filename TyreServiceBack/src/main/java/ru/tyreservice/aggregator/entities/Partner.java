package ru.tyreservice.aggregator.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "partners")
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    private String description;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, name = "phone")
    private String phoneNumber;
    @Column(nullable = false, name = "date_work")
    private String dateWork;
    private Integer rank;
    private Double latitude;
    private Double longitude;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CarType type;
    @OneToMany(mappedBy = "partner_id", fetch = FetchType.LAZY)
    private List<CostWork> costs = new ArrayList<>();

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getDescription() {
        return this.description;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getDateWork() {
        return this.dateWork;
    }

    public Integer getRank() {
        return this.rank;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public CarType getType() {
        return this.type;
    }

    public List<CostWork> getCosts() {
        return this.costs;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDateWork(String dateWork) {
        this.dateWork = dateWork;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public void setCosts(List<CostWork> costs) {
        this.costs = costs;
    }
}
