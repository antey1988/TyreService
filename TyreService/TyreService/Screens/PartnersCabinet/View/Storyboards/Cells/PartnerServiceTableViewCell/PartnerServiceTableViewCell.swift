//
//  ServicesTableViewCell.swift
//  TyreService
//
//  Created by Kirill Severin on 14.10.21.
//

import UIKit

class PartnerServiceTableViewCell: UITableViewCell {
    
    @IBOutlet weak var service: UILabel!
    @IBOutlet weak var price: UITextField!
    @IBOutlet weak var switchService: UISwitch!
    
    static let cellIdentifier = "PartnerServiceTableViewCell"
    weak var viewModel: PartnerServiceCellViewModel?
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    static func nib() -> UINib {
        return UINib(nibName: cellIdentifier, bundle: nil)
    }
    
    @IBAction func priceDidEnd(_ sender: UITextField) {
        let priceToInt: Int? = Int(price.text ?? "")
        if let priceToInt = priceToInt {
            viewModel?.price = priceToInt
        }
    }
    
    @IBAction func tappedSwitch(_ sender: UISwitch) {
        viewModel?.isActive = switchService.isOn
    }
    public func configure(viewModel: PartnerServiceCellViewModel) {
        self.viewModel = viewModel
        
        self.service.text = viewModel.name
        self.price.text = viewModel.price.description
        self.switchService.setOn(viewModel.isActive, animated: true)
    }
}
