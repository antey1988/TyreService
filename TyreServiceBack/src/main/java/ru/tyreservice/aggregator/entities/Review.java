package ru.tyreservice.aggregator.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "partner_id")
    private Long partnerId;
    @Column(nullable = false)
    private Byte ball;
    @Column(nullable = false)
    private String message;
}
