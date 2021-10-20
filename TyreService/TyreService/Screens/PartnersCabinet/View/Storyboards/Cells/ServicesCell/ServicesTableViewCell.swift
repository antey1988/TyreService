//
//  ServicesTableViewCell.swift
//  TyreService
//
//  Created by Kirill Severin on 14.10.21.
//

import UIKit

class ServicesTableViewCell: UITableViewCell {
    
    @IBOutlet weak var service: UILabel!
    @IBOutlet weak var price: UITextField!
    @IBOutlet weak var switchServices: UISwitch!
    @IBOutlet weak var activityIndicator: UIStackView!
    
    static let cellIdentifier = "ServicesCell"
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    static func nib() -> UINib {
        return UINib(nibName: cellIdentifier, bundle: nil)
    }
    
    public func configure(viewModel: ServicesCellViewModel) {
        self.service.text = viewModel.name
        self.price.text = viewModel.price
    }
    
}
