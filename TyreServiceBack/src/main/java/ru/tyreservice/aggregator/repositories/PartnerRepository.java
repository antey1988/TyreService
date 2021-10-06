package ru.tyreservice.aggregator.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tyreservice.aggregator.domain.entity.Partner;

import java.util.List;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
    @Query(value = "select p.* from partner p left join service s on p.id = s.service_id", nativeQuery = true)
    List<Partner> findAll();
}
