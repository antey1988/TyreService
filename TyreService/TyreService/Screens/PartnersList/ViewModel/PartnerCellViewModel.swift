//
//  PartnerCellViewModel.swift
//  TyreService
//
//  Created by ULADZISLAU SHURYN on 4.10.21.
//

import Foundation

class PartnerCellViewModel {
    var partner: Partner
    
    required init(partnerInfo: Partner) {
        self.partner = partnerInfo
    }
}
