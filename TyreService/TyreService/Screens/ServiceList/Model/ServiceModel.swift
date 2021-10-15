//
//  PartnerModel.swift
//  TyreService
//
//  Created by ULADZISLAU SHURYN on 4.10.21.
//

import Foundation

struct ServiceInitData: Decodable {
    //let types: [String]
    let works: [TypeService]
    let partners: [Service]
}

struct TypeService: Decodable {
    let id: Int
    let name: String
    let description: String
}

struct Service: Decodable {
    let id: Int
    let name: String
    let description: String
    let email: String
    let address: String
    let latitude: Double
    let longitude: Double
    let schedule: String
    let phone: String
    let rank: Double
    let carType: String
}
