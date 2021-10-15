//
//  PartnerInfoViewModel.swift
//  TyreService
//
//  Created by ULADZISLAU SHURYN on 12.10.21.
//

import Foundation

class ServiceInfoViewModel {
    private var partner: Service
    private var menuListCellViewModel: [MenuCellServiceInfoViewModel] = []
    private var contactsCellViewModel: ContactsCellViewModel
    private var servicesCellViewModel: ServicesCellViewModel
    private var selectedMenuItem: Int = 0 {
        didSet {
            menuListCellViewModel.forEach { menuItemViewModel in
                menuItemViewModel.isSelected = menuItemViewModel.id == selectedMenuItem
            }
            reloadMenu?()
        }
    }
    
    var reloadMenu: (()->())?
    
    required init(partner: Service) {
        self.partner = partner
        self.servicesCellViewModel = ServicesCellViewModel(description: partner.description)
        self.contactsCellViewModel = ContactsCellViewModel(longitude: partner.longitude,
                                                           latitude: partner.latitude,
                                                           address: partner.address,
                                                           workTime: partner.schedule,
                                                           phone: partner.phone)
        createMenu()
    }
    
    private func createMenu() {
        menuListCellViewModel.append(MenuCellServiceInfoViewModel(id: 0, title: "Услуги", isSelected: true))
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
    
    public func getServicesCellViewModel() -> ServicesCellViewModel {
        return servicesCellViewModel
    }
    
    public func getContactsCellViewModel() -> ContactsCellViewModel {
        return contactsCellViewModel
    }
    
    public func getServiceName() -> String {
        return partner.name
    }
}
