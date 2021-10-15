//
//  PartnerInfoViewModel.swift
//  TyreService
//
//  Created by ULADZISLAU SHURYN on 12.10.21.
//

import Foundation

class ServiceInfoViewModel {
    private var service: Service?
    private var menuListCellViewModel: [MenuCellServiceInfoViewModel] = []
    private var contactsCellViewModel: ContactsCellViewModel
    private var aboutServiceCellViewModel: AboutCellViewModel
    private var networkManager: NetworkManager
    private var selectedMenuItem: Int = 0 {
        didSet {
            menuListCellViewModel.forEach { menuItemViewModel in
                menuItemViewModel.isSelected = menuItemViewModel.id == selectedMenuItem
            }
            reloadMenu?()
        }
    }
    
    var reloadMenu: (()->())?
    var reloadInfo: (()->())?
    
    required init(service: Service) {
        self.service = service
        networkManager = NetworkManager()
        aboutServiceCellViewModel = AboutCellViewModel(viewModel: service)
        contactsCellViewModel = ContactsCellViewModel(longitude: service.longitude,
                                                           latitude: service.latitude,
                                                           address: service.address,
                                                           workTime: service.schedule,
                                                           phone: service.phone)
        getFullInfoService(idService: service.id)
        createMenu()
    }
    
    private func getFullInfoService(idService: Int) {
        networkManager.getFullInfoService(id: idService) { [weak self] (status, service) in
            switch status {
            case .ok:
                guard let service = service else { return }
                self?.service = service
                self?.aboutServiceCellViewModel = AboutCellViewModel(viewModel: service)
                self?.contactsCellViewModel = ContactsCellViewModel(longitude: service.longitude,
                                                                   latitude: service.latitude,
                                                                   address: service.address,
                                                                   workTime: service.schedule,
                                                                   phone: service.phone)
                self?.reloadInfo?()
                break
            case .error:
                break
            }
        }
    }
    
    private func createMenu() {
        menuListCellViewModel.append(MenuCellServiceInfoViewModel(id: 0, title: "О сервисе", isSelected: true))
        menuListCellViewModel.append(MenuCellServiceInfoViewModel(id: 1, title: "Контакты", isSelected: false))
        menuListCellViewModel.append(MenuCellServiceInfoViewModel(id: 2, title: "Отзывы", isSelected: false))
    }

    public func getNumberMenuItems() -> Int {
        return menuListCellViewModel.count
    }
    
    public func getMenuItemViewModel(index: Int) -> MenuCellServiceInfoViewModel {
        return menuListCellViewModel[index]
    }
    
    public func changeSelectedMenuItem(index: Int) {
       selectedMenuItem = index
    }
    
    public func getAboutServiceCellViewModel() -> AboutCellViewModel {
        return aboutServiceCellViewModel
    }
    
    public func getContactsCellViewModel() -> ContactsCellViewModel {
        return contactsCellViewModel
    }
    
    public func getServiceName() -> String {
        guard let service = service else {
            return ""
        }
        return service.name
    }
}
