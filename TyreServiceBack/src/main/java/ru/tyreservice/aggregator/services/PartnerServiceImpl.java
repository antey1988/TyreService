package ru.tyreservice.aggregator.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.tyreservice.aggregator.domain.entity.Partner;
import ru.tyreservice.aggregator.domain.enums.StateCarType;
import ru.tyreservice.aggregator.dto.PartnerResponseDTO;
import ru.tyreservice.aggregator.repositories.PartnerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class PartnerServiceImpl implements PartnerService {

    private final PartnerRepository partnerRepository;

    @Override
    public List<PartnerResponseDTO> getPartners(String nameService, Integer page, StateCarType type) {
        int sizePage = 2;
        int numberPage = page == null ? 0 : page;
        Sort sort = Sort.by(Sort.Order.desc("rank"), Sort.Order.asc("name"));
        Pageable pageable = PageRequest.of(numberPage, sizePage, sort);

        Page<Partner> partnersEntity = partnerRepository.findAll(pageable);
        List<Partner> partners = partnerRepository.findAll();

        List<PartnerResponseDTO> dtoEntity = partnersEntity.getContent().stream().map(PartnerResponseDTO::of).collect(Collectors.toList());
        return dtoEntity;
    }
}
