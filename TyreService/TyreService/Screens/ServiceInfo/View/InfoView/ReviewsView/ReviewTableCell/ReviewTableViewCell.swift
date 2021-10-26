//
//  ReviewTableViewCell.swift
//  TyreService
//
//  Created by ULADZISLAU SHURYN on 23.10.21.
//

import UIKit

class ReviewTableViewCell: UITableViewCell {

    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var dateLabel: UILabel!
    @IBOutlet weak var messageLabel: UILabel!
    @IBOutlet var ratingCollectionView: [UIImageView]!
    
    static let cellIdentifier = "ReviewTableViewCell"

    static func nib() -> UINib {
        return UINib(nibName: cellIdentifier, bundle: nil)
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    func configure(viewModel: ReviewTableCellViewModel) {
        nameLabel.text = viewModel.review.name
        messageLabel.text = viewModel.review.message
        dateLabel.text = viewModel.getDate()
        fillRatingView(rating: viewModel.review.ball)
    }
    
    private func fillRatingView(rating: Int) {
        if rating > 0 {
            for ratingNumber in 0...rating - 1 {
                ratingCollectionView[ratingNumber].tintColor = #colorLiteral(red: 0.9882352941, green: 0.7607843137, blue: 0, alpha: 1)
            }
        }
    }
}
