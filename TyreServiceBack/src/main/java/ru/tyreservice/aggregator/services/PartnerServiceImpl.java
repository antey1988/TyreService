package ru.tyreservice.aggregator.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tyreservice.aggregator.dto.requests.PartnerRequestDTO;
import ru.tyreservice.aggregator.dto.responses.PartnerResponseDTO;
import ru.tyreservice.aggregator.dto.responses.PartnerWithWorksResponseDTO;
import ru.tyreservice.aggregator.entities.Partner;
import ru.tyreservice.aggregator.enums.StateCarType;
import ru.tyreservice.aggregator.repositories.PartnerRepository;
import ru.tyreservice.aggregator.repositories.PartnerSpecification;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PartnerServiceImpl implements PartnerService {
    @Value(value = "${partners.sizepage}")
    private int SIZE_PAGE;
    private final PartnerRepository repository;

    public PartnerServiceImpl(PartnerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PartnerResponseDTO> readListPartners(StateCarType type, String name, Long id, Integer page) {
        int sizePage = SIZE_PAGE;
        int numberPage = page == null ? 0 : page;
        Sort sort = Sort.by(Sort.Order.desc("rank"), Sort.Order.asc("name"));
        Pageable pageable = PageRequest.of(numberPage, sizePage, sort);

        Page<Partner> pagePartners = repository.findAll(PartnerSpecification.readPartnersByFilter(type, name, id), pageable);
        List<Partner> partners = pagePartners.getContent();
//        List<PartnerNew> partners = partnerNewRepository.findAll(PartnerSpecification.readPartnersByFilter(type, name, id));
        List<PartnerResponseDTO> dtoEntity = partners.stream().map(PartnerResponseDTO::fromEntity).collect(Collectors.toList());
        return dtoEntity;
    }

    @Override
    @Transactional
    public PartnerWithWorksResponseDTO readPartnerWithWorks(Long id) {
        Partner partner = findById(id);
        return PartnerWithWorksResponseDTO.fromEntity(partner);
    }

    @Override
    public PartnerWithWorksResponseDTO createPartner(PartnerRequestDTO partnerRequest) {
        Partner partner = PartnerRequestDTO.onCreate(partnerRequest);
        partner = repository.save(partner);
        return PartnerWithWorksResponseDTO.fromEntity(partner);
    }

    @Override
    @Transactional
    public PartnerWithWorksResponseDTO updatePartner(Long id, PartnerRequestDTO partnerRequest) {
        Partner partner = findById(id);
        PartnerRequestDTO.onUpdate(partner, partnerRequest);
        return PartnerWithWorksResponseDTO.fromEntity(partner);
    }

    private Partner findById(Long id) {
        Optional<Partner> optional = repository.findById(id);
        return optional.orElseThrow(RuntimeException::new);
    }
}
