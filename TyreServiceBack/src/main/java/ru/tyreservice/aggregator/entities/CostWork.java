package ru.tyreservice.aggregator.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.tyreservice.aggregator.domain.enums.StateWheelType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "costs_works")
//        ,uniqueConstraints = @UniqueConstraint(columnNames = {"partner_id", "work_id"}))
public class CostWork {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    @EmbeddedId
    private Id id = new Id();
    @ManyToOne
    @JoinColumn(name = "partner_id", nullable = false, insertable = false, updatable = false)
    private PartnerNew partner;
    @ManyToOne
    @JoinColumn(name = "work_id", nullable = false, insertable = false, updatable = false)
    private Work work;
    private Long price;

    public CostWork(PartnerNew partner, Work work, Long price) {
        this.id.partnerId = partner.getId();
        this.id.workId = work.getId();
        this.partner = partner;
        this.work = work;
        this.price = price;
    }

    public CostWork() {
    }

    @Getter@Setter
    @Embeddable
    public static class Id implements Serializable{
        @Column(name = "partner_id")
        private Long partnerId;
        @Column(name = "work_id")
        private Long workId;

        public Id(Long partnerId, Long workId) {
            this.partnerId = partnerId;
            this.workId = workId;
        }

        public Id() {
        }
    }
}
