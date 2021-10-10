package ru.tyreservice.aggregator.repositories;

import org.springframework.data.jpa.domain.Specification;
import ru.tyreservice.aggregator.domain.enums.StateCarType;
import ru.tyreservice.aggregator.entities.CostWork;
import ru.tyreservice.aggregator.entities.PartnerNew;

import javax.persistence.criteria.Join;

public class PartnerSpecification {
    private static Specification<PartnerNew> readPartnersByType(StateCarType type) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (type == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            } else {
                return criteriaBuilder.equal(root.get("carType"), type);
            }
        };
    }

    private static Specification<PartnerNew> readPartnersByName(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (name == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            } else {
                return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
            }
        };
    }

    private static Specification<PartnerNew> readPartnersByWorkId(Long id) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (id == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            } else {
                Join<PartnerNew, CostWork> partnerNewCostWorkJoin = root.join("costsWorks");
                criteriaQuery.distinct(true);
                return criteriaBuilder.equal(partnerNewCostWorkJoin.get("work").get("id"), id);
            }
        };
    }

    public static Specification<PartnerNew> readPartnersByFilter(StateCarType type, String name, Long id) {
        return readPartnersByType(type).and(readPartnersByName(name)).and(readPartnersByWorkId(id));
    }
}
