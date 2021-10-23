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
    private var typeService: TypeService!
    private var updateServicesViewModel: ServicesCellViewModel!
    
    public var startLoading: (() -> ())?
    public var stopLoading: (() -> ())?
    public var updateInfo: (() -> ())?
    public var savingFailed: ((String) -> ())?
    public var showErrorMessage: ((String) -> ())?
    
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
    
    func saveCredentials(name: String, description: String, phone: String, email: String, address: String, workingHours: String, carType: String, lon: String, lat: String, completion: @escaping ((Error?) -> Void)) {
        
        let name = name.trimmingCharacters(in: .whitespaces)
        let description = description.trimmingCharacters(in: .whitespaces)
        let phone = phone.trimmingCharacters(in: .whitespaces)
        let email = email.trimmingCharacters(in: .whitespaces)
        let address = address.trimmingCharacters(in: .whitespaces)
        let workingHours = workingHours.trimmingCharacters(in: .whitespaces)
        let lon = lon.trimmingCharacters(in: .whitespaces)
        let lat = lat.trimmingCharacters(in: .whitespaces)
        
        if name == "" && name.count < 2 {
            self.showErrorMessage?(NSLocalizedString(Errors.emptyName, comment: ""))
            return
        }
        if description == "" && description.count < 25 {
            self.showErrorMessage?(NSLocalizedString(Errors.emptyField, comment: ""))
            return
        }
        if phone == "" && phone.count < 11 {
            self.showErrorMessage?(NSLocalizedString(Errors.emptyOrIncorrectPhone, comment: ""))
            return
        }
        if email == "" && !isValidEmail(email) {
            self.showErrorMessage?(NSLocalizedString(Errors.emptyOrIncorrectEmail, comment: ""))
            return
        }
        if address == "" && address.count < 18 {
            self.showErrorMessage?(NSLocalizedString(Errors.emptyOrIncorrectAddress, comment:  ""))
            return
        }
        if workingHours == "" && workingHours.count < 10 {
            self.showErrorMessage?(NSLocalizedString(Errors.emptyWorkingHours, comment: ""))
            return
        }
        if lon == "" && lat == "" {
            self.showErrorMessage?(NSLocalizedString(Errors.emptyCoordinates, comment: ""))
            return
        }
        
        //как-то проверить фотку, если она не в string
        
    }
    
    func saveListOfServices() {
        //TODO: checking services and post request
    }
    
    func getNumberOfRows() -> Int {
        return service?.works?.count ?? 0
    }
    
    private func isValidEmail(_ email: String) -> Bool {
        let emailRegEx = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}"
        let emailPred = NSPredicate(format:"SELF MATCHES %@", emailRegEx)
        return emailPred.evaluate(with: email)
    }
    
}
