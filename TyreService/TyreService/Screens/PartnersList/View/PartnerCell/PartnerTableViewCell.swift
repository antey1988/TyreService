//
//  PartnerTableViewCell.swift
//  TyreService
//
//  Created by ULADZISLAU SHURYN on 4.10.21.
//

import UIKit

class PartnerTableViewCell: UITableViewCell {

    @IBOutlet weak var containerView: UIView!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var photo: UIImageView!
    @IBOutlet weak var ratingLabel: UILabel!
    @IBOutlet weak var descriptionLabel: UILabel!
    @IBOutlet weak var addressLabel: UILabel!
    @IBOutlet weak var ratingView: UIStackView!
    @IBOutlet weak var nameView: UIView!
    @IBOutlet weak var distanceView: UIView!
    @IBOutlet weak var distanceLabel: UILabel!
    
    static let cellIdentifier = "PartnerTableViewCell"

    static func nib() -> UINib {
        return UINib(nibName: cellIdentifier, bundle: nil)
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
        photo.image = UIImage(named: "sto")
        ratingView.layer.cornerRadius = 5
        ratingView.layer.masksToBounds = true
        ratingView.clipsToBounds = true
        
        nameView.layer.cornerRadius = 5
        nameView.layer.masksToBounds = true
        nameView.clipsToBounds = true
        
        containerView.layer.cornerRadius = 5
        containerView.layer.masksToBounds = true
        containerView.clipsToBounds = true
        
        distanceView.layer.cornerRadius = 5
        distanceView.layer.masksToBounds = true
        distanceView.clipsToBounds = true
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }

    public func configure(viewModel: PartnerCellViewModel) {
       
    }
}
