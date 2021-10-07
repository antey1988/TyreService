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
import ru.tyreservice.aggregator.dto.CostWorkRequestDTO;
import ru.tyreservice.aggregator.dto.PartnerRequestDTO;
import ru.tyreservice.aggregator.dto.PartnerResponseDTO;
import ru.tyreservice.aggregator.dto.PartnerWithWorksResponseDTO;
import ru.tyreservice.aggregator.entities.CostWork;
import ru.tyreservice.aggregator.entities.PartnerNew;
import ru.tyreservice.aggregator.entities.Work;
import ru.tyreservice.aggregator.repositories.CostWorkRepository;
import ru.tyreservice.aggregator.repositories.PartnerNewRepository;
import ru.tyreservice.aggregator.repositories.PartnerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class PartnerServiceImpl implements PartnerService {

    private final PartnerRepository partnerRepository;
    private final PartnerNewRepository partnerNewRepository;
    private final CostWorkRepository costWorkRepository;

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
    public List<PartnerResponseDTO> getListPartners(String nameService, Integer page, StateCarType type) {
//        int sizePage = 2;
//        int numberPage = page == null ? 0 : page;
//        Sort sort = Sort.by(Sort.Order.desc("rank"), Sort.Order.asc("name"));
//        Pageable pageable = PageRequest.of(numberPage, sizePage, sort);

//        Page<Partner> pagePartners = partnerNewRepository.findAll(pageable);
        List<PartnerNew> partners_1 = partnerNewRepository.findAll();
//        List<Partner> partners_2 = partnerRepository.findAll("Мон");
//        List<Partner> partners_3 = partnerRepository.findAll("Дем");

//        List<PartnerResponseDTO> dtoEntity = pagePartners.getContent().stream().map(PartnerResponseDTO::of).collect(Collectors.toList());
        List<PartnerResponseDTO> dtoEntity = partners_1.stream().map(PartnerResponseDTO::of).collect(Collectors.toList());
        return dtoEntity;
    }

    @Override
    public PartnerWithWorksResponseDTO getPartnerWithWorks(Long id) {
        Optional<PartnerNew> optional = partnerNewRepository.findById(id);
        if (optional.isPresent()) {
            PartnerNew partner = optional.get();
            return PartnerWithWorksResponseDTO.of(partner);
        }
        return null;
    }

    @Override
    public PartnerWithWorksResponseDTO addWorks(Long id, List<CostWorkRequestDTO> costWorksRequestDTO) {
        PartnerNew partner = new PartnerNew();
        partner.setId(id);
        costWorksRequestDTO.stream().forEach(w -> {
            Work work = CostWorkRequestDTO.of(w);
            CostWork costWork = new CostWork();
            costWork.setPartner(partner);
            costWork.setWork(work);
            costWork.setPrice(w.getPrice());
            costWorkRepository.save(costWork);
        });
        return getPartnerWithWorks(id);
    }
}
