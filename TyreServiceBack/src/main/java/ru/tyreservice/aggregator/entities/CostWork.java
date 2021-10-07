package ru.tyreservice.aggregator.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.tyreservice.aggregator.domain.enums.StateWheelType;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "costs_works"
        ,uniqueConstraints = @UniqueConstraint(columnNames = {"partner_id", "work_id"}))
public class CostWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "partner_id", nullable = false)
    private PartnerNew partner;
    @ManyToOne
    @JoinColumn(name = "work_id", nullable = false)
    private Work work;
    private Long price;

}
