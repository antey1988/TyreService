//
//  FilterCollectionViewCell.swift
//  TyreService
//
//  Created by ULADZISLAU SHURYN on 4.10.21.
//

import UIKit

class FilterCollectionViewCell: UICollectionViewCell {

    @IBOutlet weak var text: UILabel!
    
    static let cellIdentifier = "FilterCollectionViewCell"
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        self.backgroundColor = .clear
        self.layer.cornerRadius = 8
        self.layer.masksToBounds = true
        self.clipsToBounds = true
    }
    
    static func nib() -> UINib {
        return UINib(nibName: cellIdentifier, bundle: nil)
    }
    
    public func configure(viewModel: FilterCellViewModel) {
        self.text.text = viewModel.filter.name
        if viewModel.isSelected {
            self.backgroundColor = UIColor.systemGray.withAlphaComponent(0.3)
        } else {
            self.backgroundColor = .clear
        }
    }
}
