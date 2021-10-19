//
//  PartnerCabinetViewModel.swift
//  TyreService
//
//  Created by Kirill Severin on 15.10.21.
//

import Foundation

class PartnerCabinetViewModel {
    
    private var networkManager: NetworkManager
    var service: Service!
    var typeService: TypeService!
    public var updateInfo: (() -> ())?
    private var updateServicesViewModel: ServicesCellViewModel!
    
    required init(idService: Int) {
       networkManager = NetworkManager()
       getServiceInfo(id: idService)
   }
    
    func getServiceInfo(id: Int) {
        networkManager.getFullInfoService(id: id) { [weak self] (requestStatus, service) in
            switch requestStatus {
            case .ok:
                if let service = service {
                    self?.service = service
                }
                self?.updateInfo?()
            case .error:
                break
            }
        }
    }
    
    func getNumberOfRows() -> Int {
        return service.works?.count ?? 0
    }
    
}
