//
//  PartnersListViewModel.swift
//  TyreService
//
//  Created by ULADZISLAU SHURYN on 4.10.21.
//

import Foundation

class PartnersListViewModel {
    var partnersCellViewModel: [PartnerCellViewModel] = []
    var filtersCellViewModel: [FilterCellViewModel] = []
    var networkManager: NetworkManager!
    
    required init() {
        networkManager = NetworkManager()
        getPartnersData()
    }
    
    private func getPartnersData() {
        networkManager.getParnersAndGroups(lat: 55.751244, lon: 37.618423) { status, partners, filters in
            switch status {
            case .OK:
                if let partners = partners {
                    self.initPartnersViewModel(partners: partners)
                }
                if let filters = filters {
                    self.initGroupsViewModel(filters: filters)
                }
                break
            case .ERROR:
                break
            }
        }
    }
    
    private func initGroupsViewModel(filters: [FilterModel]) {
        filters.forEach { filter in
            filtersCellViewModel.append(FilterCellViewModel(filter: filter, isSelected: filter.id == 0))
        }
    }
    
    private func initPartnersViewModel(partners: [Partner]) {
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
}
