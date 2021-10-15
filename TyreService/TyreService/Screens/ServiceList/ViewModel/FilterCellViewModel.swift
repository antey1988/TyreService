//
//  GroupCellViewModel.swift
//  TyreService
//
//  Created by ULADZISLAU SHURYN on 4.10.21.
//

import Foundation

class FilterCellViewModel {
    var filter: FilterModel
    var isSelected: Bool
    
    required init(filter: FilterModel, isSelected: Bool) {
        self.filter = filter
        self.isSelected = isSelected
    }
}
