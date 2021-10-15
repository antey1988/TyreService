//
//  ReviewsCollectionViewCell.swift
//  TyreService
//
//  Created by ULADZISLAU SHURYN on 12.10.21.
//

import UIKit

class ReviewsCollectionViewCell: UICollectionViewCell {

    static let cellIdentifier = "ReviewsCollectionViewCell"

    override func awakeFromNib() {
        super.awakeFromNib()
        self.layer.cornerRadius = 5
    }
    
    static func nib() -> UINib {
        return UINib(nibName: cellIdentifier, bundle: nil)
    }

}
