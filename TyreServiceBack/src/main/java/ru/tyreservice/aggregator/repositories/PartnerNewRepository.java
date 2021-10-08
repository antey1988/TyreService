package ru.tyreservice.aggregator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tyreservice.aggregator.entities.PartnerNew;

@Repository
public interface PartnerNewRepository extends JpaRepository<PartnerNew, Long> {
}
