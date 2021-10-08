package ru.tyreservice.aggregator.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.tyreservice.aggregator.domain.entity.Partner;

import java.util.List;


@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
    @Query(value = "select distinct p from Partner p left join p.services s where s.name like %:name%")
    List<Partner> findAll(@Param(value = "name") String name);
    Partner getFirstByName (String name);
}
