//
//  ServicesCellViewModel.swift
//  TyreService
//
//  Created by Kirill Severin on 15.10.21.
//

import Foundation

class ServicesCellViewModel {
    
    let name: String
    let price: String
    
    required init(name: String, price: Int) {
        self.name = name
        self.price = price.description + "руб."
    }
    
}
