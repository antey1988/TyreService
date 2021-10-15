//
//  PartnersListViewModel.swift
//  TyreService
//
//  Created by ULADZISLAU SHURYN on 4.10.21.
//

import Foundation

class ServiceListViewModel {
    private var servicesCellViewModel: [ServiceCellViewModel] = []
    private var filtersCellViewModel: [FilterCellViewModel] = []
    private var networkManager: NetworkManager!
    private var selectedFilterId = 0
    private var allServices = false
    private var pageNumber = 1 {
        didSet {
            startLoadIndicator?()
            if pageNumber == 1 {
                allServices = false
            }
            updatePartners()
        }
    }
    
    private var serviceInfoViewModel: ServiceInfoViewModel!
    
    var reloadPartners: (()->())?
    var reloadFilter: (()->())?
    var startLoadIndicator: (()->())?
    var stopLoadIndicator: (()->())?

    required init() {
        networkManager = NetworkManager()
        initPartnersAndGroups()
    }
    
    private func initPartnersAndGroups() {
        networkManager.getServicesAndFilters() { [weak self] status, partners, filters in
            switch status {
            case .OK:
                if let partners = partners {
                    self?.initPartnersViewModel(partners: partners)
                }
                if let filters = filters {
                    self?.initGroupsViewModel(filters: filters)
                }
                self?.reloadPartners?()
                self?.reloadFilter?()
                break
            case .ERROR:
                break
            }
        }
    }
    
    private func updatePartners() {
        networkManager.getPartners(lat: 55.751244, lon: 37.618423, filterId: selectedFilterId, pageNumber: pageNumber) { [weak self] status, partners in
            switch status {
            case .OK:
                self?.stopLoadIndicator?()
                self?.allServices = partners?.count == 0
                if let partners = partners {
                    self?.initPartnersViewModel(partners: partners)
                }
                self?.reloadPartners?()
                break
            case .ERROR:
                break
            }
        }
    }
    
    private func initGroupsViewModel(filters: [FilterModel]) {
        if pageNumber == 1 {
            filtersCellViewModel = []
        }
        filters.forEach { filter in
            filtersCellViewModel.append(FilterCellViewModel(filter: filter, isSelected: filter.id == selectedFilterId))
        }
    }
    
    private func initPartnersViewModel(partners: [Service]) {
        if pageNumber == 1 {
            servicesCellViewModel = []
        }
        partners.forEach { partner in
            servicesCellViewModel.append(ServiceCellViewModel(service: partner))
        }
    }
    
    public func getNumberGroupsCell() -> Int {
        return filtersCellViewModel.count
    }
    
    public func getNumberPartnerCell() -> Int {
        return servicesCellViewModel.count
    }
    
    public func getGroupViewModelById(id: Int) -> FilterCellViewModel {
        return filtersCellViewModel[id]
    }
    
    public func getPartnerViewModelById(id: Int) -> ServiceCellViewModel {
        return servicesCellViewModel[id]
    }
    
    public func changeSelectedFilterId(index: Int) {
        let selectedId = filtersCellViewModel[index].filter.id
        if selectedId != selectedFilterId {
            selectedFilterId = selectedId
            filtersCellViewModel.forEach { filterViewModel in
                filterViewModel.isSelected = filterViewModel.filter.id == selectedId
            }
            pageNumber = 1
            reloadFilter?()
        }
    }
    
    public func loadMoreData() {
        if !allServices {
            pageNumber += 1
        }
    }
    
    public func getServiceInfoViewModel(index: Int) -> ServiceInfoViewModel {
        serviceInfoViewModel = ServiceInfoViewModel(partner: servicesCellViewModel[index].service)
        return serviceInfoViewModel
    }
}
