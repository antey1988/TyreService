//
//  PartnersListViewModel.swift
//  TyreService
//
//  Created by ULADZISLAU SHURYN on 4.10.21.
//

import Foundation

class ServiceListViewModel {
    private var servicesCellViewModel: [ServiceCellViewModel] = []
    private var filtersCellViewModel: [CarTypeCellViewModel] = []
    private var serviceInfoViewModel: ServiceInfoViewModel!
    private var networkManager: NetworkManager!
    private var selectedFilterId = ""
    private var allServices = false
    private var debounce_timer:Timer?
    private var searchText = "" {
        didSet {
            debounce_timer?.invalidate()
            debounce_timer = Timer.scheduledTimer(withTimeInterval: 0.5, repeats: false) { [weak self] _ in
                self?.updatePartners(page: 0)
            }
        }
    }
    private var pageNumber = 0 {
        didSet {
            startLoadIndicator?()
            allServices = pageNumber != 0
            updatePartners(page: pageNumber)
        }
    }
    
    
    var reloadPartners: (()->())?
    var reloadFilter: (()->())?
    var startLoadIndicator: (()->())?
    var stopLoadIndicator: (()->())?

    required init() {
        networkManager = NetworkManager()
        initPartnersAndGroups()
    }
    
    private func initPartnersAndGroups() {
        networkManager.getServicesAndFilters() { [weak self] (status, services, filters) in
            switch status {
            case .ok:
                if let services = services {
                    self?.initPartnersViewModel(services: services)
                }
                if let filters = filters {
                    self?.initGroupsViewModel(carTypes: filters)
                }
                self?.reloadPartners?()
                self?.reloadFilter?()
                break
            case .error:
                break
            }
        }
    }
    
    private func updatePartners(page: Int) {
        networkManager.getServices(filterCarType: selectedFilterId, pageNumber: page, search: searchText) { [weak self] (status, services) in
            switch status {
            case .ok:
                self?.stopLoadIndicator?()
                self?.allServices = services?.count == 0
                if let services = services {
                    if page == 0 {
                        self?.servicesCellViewModel = []
                    }
                    self?.initPartnersViewModel(services: services)
                }
                self?.reloadPartners?()
                break
            case .error:
                break
            }
        }
    }
    
    private func initGroupsViewModel(carTypes: [CarType]) {
        filtersCellViewModel = [CarTypeCellViewModel(key: "", name: "Все", isSelected: true)]
        carTypes.forEach { type in
            filtersCellViewModel.append(CarTypeCellViewModel(key: type.key, name: type.name, isSelected: false))
        }
    }
    
    private func initPartnersViewModel(services: [Service]) {
        services.forEach { service in
            servicesCellViewModel.append(ServiceCellViewModel(service: service))
        }
    }
    
    public func getNumberGroupsCell() -> Int {
        return filtersCellViewModel.count
    }
    
    public func getNumberPartnerCell() -> Int {
        return servicesCellViewModel.count
    }
    
    public func getGroupViewModelById(id: Int) -> CarTypeCellViewModel {
        return filtersCellViewModel[id]
    }
    
    public func getPartnerViewModelById(id: Int) -> ServiceCellViewModel {
        return servicesCellViewModel[id]
    }
    
    public func changeSelectedFilterId(index: Int) {
        let selectedId = filtersCellViewModel[index].key
        if selectedId != selectedFilterId {
            selectedFilterId = selectedId
            filtersCellViewModel.forEach { filterViewModel in
                filterViewModel.isSelected = filterViewModel.key == selectedId
            }
            pageNumber = 0
            reloadFilter?()
        }
    }
    
    public func loadMoreData() {
        if !allServices {
            pageNumber += 1
        }
    }
    
    public func getServiceInfoViewModel(index: Int) -> ServiceInfoViewModel {
        serviceInfoViewModel = ServiceInfoViewModel(service: servicesCellViewModel[index].service)
        return serviceInfoViewModel
    }
    
    public func keyupSearchBar(text: String) {
        searchText = text
    }
}
