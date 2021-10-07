//
//  PartnersListViewModel.swift
//  TyreService
//
//  Created by ULADZISLAU SHURYN on 4.10.21.
//

import Foundation

class PartnersListViewModel {
    private var partnersCellViewModel: [PartnerCellViewModel] = []
    private var filtersCellViewModel: [FilterCellViewModel] = []
    private var networkManager: NetworkManager!
    private var selectedFilterId = 0
    
    var reloadPartners: (()->())?
    var reloadFilter: (()->())?

    required init() {
        networkManager = NetworkManager()
        getPartnersData()
    }
    
    private func getPartnersData() {
        networkManager.getParnersAndGroups(lat: 55.751244, lon: 37.618423, filterId: selectedFilterId) { [weak self] status, partners, filters in
            switch status {
            case .OK:
                if let partners = partners {
                    self?.initPartnersViewModel(partners: partners)
                }
                if let filters = filters {
                    self?.initGroupsViewModel(filters: filters)
                }
                self?.reloadPartners?()
                break
            case .ERROR:
                break
            }
        }
    }
    
    private func initGroupsViewModel(filters: [FilterModel]) {
        filtersCellViewModel = []
        filters.forEach { filter in
            filtersCellViewModel.append(FilterCellViewModel(filter: filter, isSelected: filter.id == selectedFilterId))
        }
    }
    
    private func initPartnersViewModel(partners: [Partner]) {
        partnersCellViewModel = []
        partners.forEach { partner in
            partnersCellViewModel.append(PartnerCellViewModel(partnerInfo: partner))
        }
    }
    
    public func getNumberGroupsCell() -> Int {
        return filtersCellViewModel.count
    }
    
    public func getNumberPartnerCell() -> Int {
        return partnersCellViewModel.count
    }
    
    public func getGroupViewModelById(id: Int) -> FilterCellViewModel {
        return filtersCellViewModel[id]
    }
    
    public func getPartnerViewModelById(id: Int) -> PartnerCellViewModel {
        return partnersCellViewModel[id]
    }
    
    public func changeSelectedFilterId(index: Int) {
        let selectedId = filtersCellViewModel[index].filter.id
        if selectedId != selectedFilterId {
            selectedFilterId = selectedId
            filtersCellViewModel.forEach { filterViewModel in
                filterViewModel.isSelected = filterViewModel.filter.id == selectedId
            }
            getPartnersData()
            reloadFilter?()
        }
    }
}
