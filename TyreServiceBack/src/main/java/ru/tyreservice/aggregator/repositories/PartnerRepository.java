package ru.tyreservice.aggregator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tyreservice.aggregator.entities.Partner;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
}
