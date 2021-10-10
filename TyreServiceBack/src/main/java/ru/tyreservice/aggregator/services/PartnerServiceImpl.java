package ru.tyreservice.aggregator.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tyreservice.aggregator.domain.entity.Partner;
import ru.tyreservice.aggregator.domain.enums.StateCarType;
import ru.tyreservice.aggregator.dto.requests.PartnerRequestDTO;
import ru.tyreservice.aggregator.dto.responses.PartnerResponseDTO;
import ru.tyreservice.aggregator.dto.responses.PartnerWithWorksResponseDTO;
import ru.tyreservice.aggregator.entities.PartnerNew;
import ru.tyreservice.aggregator.repositories.PartnerNewRepository;
import ru.tyreservice.aggregator.repositories.PartnerRepository;
import ru.tyreservice.aggregator.repositories.PartnerSpecification;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class PartnerServiceImpl implements PartnerService {

    private final PartnerRepository partnerRepository;
    private final PartnerNewRepository partnerNewRepository;

    @Override
    public List<PartnerResponseDTO> getPartners(String nameService, Integer page, StateCarType type) {
        int sizePage = 2;
        int numberPage = page == null ? 0 : page;
        Sort sort = Sort.by(Sort.Order.desc("rank"), Sort.Order.asc("name"));
        Pageable pageable = PageRequest.of(numberPage, sizePage, sort);

        Page<Partner> pagePartners = partnerRepository.findAll(pageable);
//        List<Partner> partners_1 = partnerRepository.findAll();
//        List<Partner> partners_2 = partnerRepository.findAll("Мон");
//        List<Partner> partners_3 = partnerRepository.findAll("Дем");

        List<PartnerResponseDTO> dtoEntity = pagePartners.getContent().stream().map(PartnerResponseDTO::of).collect(Collectors.toList());
        return dtoEntity;
    }

    @Override
    public List<PartnerResponseDTO> readListPartners(StateCarType type, String name, Long id, Integer page) {
        int sizePage = 2;
        int numberPage = page == null ? 0 : page;
        Sort sort = Sort.by(Sort.Order.desc("rank"), Sort.Order.asc("name"));
        Pageable pageable = PageRequest.of(numberPage, sizePage, sort);

        Page<PartnerNew> pagePartners = partnerNewRepository.findAll(PartnerSpecification.readPartnersByFilter(type, name, id), pageable);
        List<PartnerNew> partners = pagePartners.getContent();
//        List<PartnerNew> partners = partnerNewRepository.findAll(PartnerSpecification.readPartnersByFilter(type, name, id));
        List<PartnerResponseDTO> dtoEntity = partners.stream().map(PartnerResponseDTO::of).collect(Collectors.toList());
        return dtoEntity;
    }

    @Override
    @Transactional
    public PartnerWithWorksResponseDTO readPartnerWithWorks(Long id) {
        Optional<PartnerNew> optional = partnerNewRepository.findById(id);
        if (optional.isPresent()) {
            PartnerNew partner = optional.get();
            return PartnerWithWorksResponseDTO.of(partner);
        }
        return null;
    }

    @Override
    public PartnerWithWorksResponseDTO createPartnerWithWorks(PartnerRequestDTO partnerRequestDTO) {
        PartnerNew partner = PartnerRequestDTO.onCreate(partnerRequestDTO);
        partner = partnerNewRepository.save(partner);
        return PartnerWithWorksResponseDTO.of(partner);
    }

    @Override
    public PartnerWithWorksResponseDTO updatePartnerWithWorks(Long id, PartnerRequestDTO partnerRequestDTO) {
        return null;
    }
}
