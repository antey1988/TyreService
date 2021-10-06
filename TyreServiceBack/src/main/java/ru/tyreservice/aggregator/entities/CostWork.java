package ru.tyreservice.aggregator.entities;

import javax.persistence.*;

@Entity
@Table(name = "cost_works",
        uniqueConstraints = @UniqueConstraint(name = "partner_work", columnNames = {"partner_id", "work_id"}))
public class CostWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false, name = "partner_id")
    private Partner partner_id;
    @ManyToOne
    @JoinColumn(nullable = false, name = "work_id")
    private Work work_id;
    private Long cost;

    public Long getId() {
        return this.id;
    }

    public Partner getPartner_id() {
        return this.partner_id;
    }

    public Work getWork_id() {
        return this.work_id;
    }

    public Long getCost() {
        return this.cost;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPartner_id(Partner partner_id) {
        this.partner_id = partner_id;
    }

    public void setWork_id(Work work_id) {
        this.work_id = work_id;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }
}
