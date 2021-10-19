package ru.tyreservice.aggregator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tyreservice.aggregator.entities.CostWork;

import java.util.List;

@Repository
public interface CostWorkRepository extends JpaRepository<CostWork, Long> {
    @Query(value = "select cw from CostWork cw join fetch cw.work where cw.partner.id = :id")
    List<CostWork> findAllByPartnerId(Long id);

    void deleteAllByPartnerIdAndWorkIdIn(Long id, List<Long> works);

    @Query(value = "select cw.partner.id, count(cw) from CostWork cw where cw.work.id in :ids " +
            "group by cw.partner.id having count(cw) = :size")
    List<Long> getIds(List<Long> ids, Long size);
}
