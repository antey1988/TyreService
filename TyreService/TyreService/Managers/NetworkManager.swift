//
//  NetworkManager.swift
//  TyreService
//
//  Created by ULADZISLAU SHURYN on 4.10.21.
//

import Foundation
import Alamofire

class NetworkManager {
    
    func getServicesAndFilters(complition: @escaping ((RequestStatus, [Service]?, [FilterModel]?) -> Void)) {
        let filter = [
            FilterModel(id: 0, name: "Все"),
            FilterModel(id: 1, name: "Грузовые"),
            FilterModel(id: 2, name: "Легковые"),
            FilterModel(id: 3, name: "Фуры")
        ]
        
        AF.request("http://194.67.91.182:8081/api").validate().responseDecodable(of: ServiceInitData.self) { (response) in
            guard let data = response.value else { return }
            complition(.OK, data.partners, filter)
        }
    }
    
    func getPartners(lat: Double, lon: Double, filterId: Int, pageNumber: Int, complition: @escaping ((RequestStatus, [Service]?) -> Void)) {
        DispatchQueue.global().async {
            usleep(700000)
            DispatchQueue.main.async {
                complition(.OK, [])
            }
        }
    }
}
