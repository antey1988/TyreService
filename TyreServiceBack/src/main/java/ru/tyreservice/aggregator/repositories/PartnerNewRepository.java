package ru.tyreservice.aggregator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tyreservice.aggregator.entities.PartnerNew;

import java.util.Optional;

@Repository
public interface PartnerNewRepository extends JpaRepository<PartnerNew, Long>, JpaSpecificationExecutor<PartnerNew> {
//    @Query(value = "select p from PartnerNew p left join fetch p.costsWorks cw join fetch cw.work where p.id = :id")
//    Optional<PartnerNew> findById(Long id);
}
