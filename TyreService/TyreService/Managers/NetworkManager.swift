//
//  NetworkManager.swift
//  TyreService
//
//  Created by ULADZISLAU SHURYN on 4.10.21.
//

import Foundation
import Alamofire

class NetworkManager {
    
    func getServicesAndFilters(complition: @escaping ((RequestStatus, [Service]?, [CarType]?, [TypeService]?) -> Void)) {
        AF.request("http://194.67.91.182:8081/api").validate().responseDecodable(of: ServiceInitData.self) { (response) in
            guard let data = response.value else {
                complition(.error, nil, nil, nil)
                return
            }
            complition(.ok, data.partners, data.types, data.works)
        }
    }
    
    func getServices(filterCarType: String, pageNumber: Int, search: String, latitude: Double, longitude: Double, filterWorksType: [String], complition: @escaping ((RequestStatus, [Service]?) -> Void)) {
        let url = "http://194.67.91.182:8081/api/partners"
        var parameters: [String: Any] = [
            "type": filterCarType,
            "name": search,
            "page": pageNumber,
            "latitude": latitude,
            "longitude": longitude,
        ]
        if filterWorksType.count > 0 {
            parameters["id"] = filterWorksType.joined(separator: ",")
        }
        AF.request(url, parameters: parameters).validate().responseDecodable(of: [Service].self) { (response) in
            guard let data = response.value else {
                complition(.error, nil)
                return
            }
            complition(.ok, data)
        }
    }
    
    func getFullInfoService(id: Int, complition: @escaping ((RequestStatus, Service?) -> Void)) {
        let url = "http://194.67.91.182:8081/api/partners/\(id)"
        AF.request(url).validate().responseDecodable(of: Service.self) { (response) in
            guard let data = response.value else {
                complition(.error, nil)
                return
            }
            complition(.ok, data)
        }
    }
    
    func requestOnWorks(partnerId: Int, clientName: String, clientPhone: String, clientAuto: String, works: [TypeService], visitDate: String, currentDate: String, complition: @escaping ((RequestStatus) -> Void)) {
        let url = "http://194.67.91.182:8081/api/orders"
        let parameters: [String: Any] = [
            "partnerId": partnerId,
            "clientName": clientName,
            "clientPhone": clientPhone,
            "clientAuto": clientAuto,
            "bookingDate": visitDate,
            "createDate": currentDate,
            "lines": works.map({ work in
                return ["id": work.id, "price": work.price, "count": 1]
            })
        ]
        
        AF.request(url, method: .post, parameters: parameters, encoding: JSONEncoding.default).responseJSON { (response) in
            complition(.ok)
        }
        
    }
}
