package ru.tyreservice.aggregator.repositories;

import org.springframework.data.jpa.domain.Specification;
import ru.tyreservice.aggregator.enums.StateCarType;
import ru.tyreservice.aggregator.entities.CostWork;
import ru.tyreservice.aggregator.entities.Partner;

import javax.persistence.criteria.Join;

public class PartnerSpecification {
    private static Specification<Partner> readPartnersByType(StateCarType type) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (type == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            } else {
                return criteriaBuilder.equal(root.get("carType"), type);
            }
        };
    }

    private static Specification<Partner> readPartnersByName(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (name == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            } else {
                return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
            }
        };
    }

    private static Specification<Partner> readPartnersByWorkId(Long id) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (id == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            } else {
                Join<Partner, CostWork> partnerNewCostWorkJoin = root.join("costsWorks");
                criteriaQuery.distinct(true);
                return criteriaBuilder.equal(partnerNewCostWorkJoin.get("work").get("id"), id);
            }
        };
    }

    public static Specification<Partner> readPartnersByFilter(StateCarType type, String name, Long id) {
        return readPartnersByType(type).and(readPartnersByName(name)).and(readPartnersByWorkId(id));
    }
}
