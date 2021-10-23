package ru.tyreservice.aggregator.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Embeddable
public class Review {
    @Column(nullable = false)
    private Integer ball;
    @Column(nullable = false)
    private String message;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Date date;
}
