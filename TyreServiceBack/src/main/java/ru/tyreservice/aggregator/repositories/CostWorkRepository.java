package ru.tyreservice.aggregator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tyreservice.aggregator.entities.CostWork;

public interface CostWorkRepository extends JpaRepository<CostWork, Long> {
}
