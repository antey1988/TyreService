//
//  NetworkManager.swift
//  TyreService
//
//  Created by ULADZISLAU SHURYN on 4.10.21.
//

import Foundation

class NetworkManager {
    
    func getParnersAndGroups(lat: Double, lon: Double, complition: @escaping ((RequestStatus, [Partner]?, [FilterModel]?) -> Void)) {
        let partners = [
            Partner(id: 0, name: "Partner 1", address: "ул. Солтыса 21", description: "Ремонт шин, сезонное хранение шин, подкачка шин", latitude: 55.751244, longitude: 37.618423, time_open: 7200, time_close: 72800, description_work_time: "TEST 1", phone: "11111111"),
            Partner(id: 1, name: "Partner 2", address: "ул. Солтыса 22", description: "Ремонт шин, сезонное хранение шин, подкачка шин", latitude: 55.751244, longitude: 37.618423, time_open: 7200, time_close: 72800, description_work_time: "TEST 2", phone: "11111111"),
            Partner(id: 2, name: "Partner 3", address: "ул. Солтыса 23", description: "Ремонт шин, сезонное хранение шин, подкачка шин", latitude: 55.751244, longitude: 37.618423, time_open: 7200, time_close: 72800, description_work_time: "TEST 3", phone: "11111111"),
            Partner(id: 3, name: "Partner 4", address: "ул. Солтыса 24", description: "Ремонт шин, сезонное хранение шин, подкачка шин", latitude: 55.751244, longitude: 37.618423, time_open: 7200, time_close: 72800, description_work_time: "TEST 4", phone: "11111111"),
            Partner(id: 4, name: "Partner 5", address: "ул. Солтыса 25", description: "Ремонт шин, сезонное хранение шин, подкачка шин", latitude: 55.751244, longitude: 37.618423, time_open: 7200, time_close: 72800, description_work_time: "TEST 5", phone: "11111111")
        ]
        
        let filter = [
            FilterModel(id: 0, name: "Все"),
            FilterModel(id: 1, name: "Грузовые"),
            FilterModel(id: 2, name: "Легковые"),
            FilterModel(id: 3, name: "Фуры")
        ]
        
        complition(.OK, partners, filter)
    }
}