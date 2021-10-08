package ru.tyreservice.aggregator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tyreservice.aggregator.entities.CostWork;

import java.util.List;

@Repository
public interface CostWorkRepository extends JpaRepository<CostWork, Long> {
    List<CostWork> findAllByPartnerId(Long id);
}
