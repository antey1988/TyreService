package ru.tyreservice.aggregator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tyreservice.aggregator.entities.PartnerNew;

public interface PartnerNewRepository extends JpaRepository<PartnerNew, Long> {
}
