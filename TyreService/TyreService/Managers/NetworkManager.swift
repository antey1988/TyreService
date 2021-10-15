//
//  NetworkManager.swift
//  TyreService
//
//  Created by ULADZISLAU SHURYN on 4.10.21.
//

import Foundation
import Alamofire

class NetworkManager {
    
    func getServicesAndFilters(complition: @escaping ((RequestStatus, [Service]?, [CarType]?) -> Void)) {
        AF.request("http://194.67.91.182:8081/api").validate().responseDecodable(of: ServiceInitData.self) { (response) in
            guard let data = response.value else {
                complition(.error, nil, nil)
                return
            }
            complition(.ok, data.partners, data.types)
        }
    }
    
    func getServices(filterCarType: String, pageNumber: Int, search: String, complition: @escaping ((RequestStatus, [Service]?) -> Void)) {
        let searchString = search.addingPercentEncoding(withAllowedCharacters: .urlHostAllowed) ?? ""
        let url = "http://194.67.91.182:8081/api/partners?type=\(filterCarType)&name=\(searchString)&page=\(pageNumber)"
        AF.request(url).validate().responseDecodable(of: [Service].self) { (response) in
            guard let data = response.value else {
                complition(.error, nil)
                return
            }
            complition(.ok, data)
        }
    }
    
    func getFullInfoService(id: Int, complition: @escaping ((RequestStatus, Service?) -> Void)) {
        let url = "http://194.67.91.182:8081/api/partners/\(id)";
        AF.request(url).validate().responseDecodable(of: Service.self) { (response) in
            guard let data = response.value else {
                complition(.error, nil)
                return
            }
            complition(.ok, data)
        }
    }
}
