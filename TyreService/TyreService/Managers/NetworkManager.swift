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
        AF.request(Constants.api).validate().responseDecodable(of: ServiceInitData.self) { (response) in
            guard let data = response.value else {
                complition(.error, nil, nil, nil)
                return
            }
            complition(.ok, data.partners, data.types, data.works)
        }
    }
    
    func getServices(filterCarType: String, pageNumber: Int, search: String, latitude: Double, longitude: Double, filterWorksType: [String], complition: @escaping ((RequestStatus, [Service]?) -> Void)) {
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
        AF.request(Constants.apiPartners, parameters: parameters).validate().responseDecodable(of: [Service].self) { (response) in
            guard let data = response.value else {
                complition(.error, nil)
                return
            }
            complition(.ok, data)
        }
    }
    
    func getFullInfoService(id: Int, complition: @escaping ((RequestStatus, Service?) -> Void)) {
        let url = Constants.apiPartners + "/\(id)"
        AF.request(url).validate().responseDecodable(of: Service.self) { (response) in
            guard let data = response.value else {
                complition(.error, nil)
                return
            }
            complition(.ok, data)
        }
    }
    
    func requestOnWorks(partnerId: Int, clientName: String, clientPhone: String, clientAuto: String, works: [TypeService], visitDate: String, currentDate: String, complition: @escaping ((RequestStatus) -> Void)) {
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
        
        AF.request(Constants.apiCreateOrder, method: .post, parameters: parameters, encoding: JSONEncoding.default).validate().responseDecodable(of: ResponseStatus.self) { (response) in
            guard let data = response.value else {
                complition(.error)
                return
            }
            complition(data.success ? .ok : .error)
        }
        
    }
    
    func addReview(serviceId: Int, name: String, description: String, rating: Int, complition: @escaping ((RequestStatus) -> Void)) {
        let url = Constants.apiPartners + "/\(serviceId)/reviews"
        let parameters: [String: Any] = [
            "ball": rating,
            "message": description,
            "name": name
        ]
        
        AF.request(url, method: .post, parameters: parameters, encoding: JSONEncoding.default).validate().responseDecodable(of: ResponseStatus.self) { (response) in
            guard let data = response.value else {
                complition(.error)
                return
            }
            complition(data.success ? .ok : .error)
        }
    }
}
