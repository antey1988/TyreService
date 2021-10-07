//
//  UIViewController.swift
//  TyreService
//
//  Created by Kirill Severin on 6.10.21.
//

import UIKit

extension UIViewController {
    
    func showErrorAlert(with title: String = NSLocalizedString("Ошибка", comment: ""), and message: String) {
        
        let alert = UIAlertController(title: title, message: message, preferredStyle: .alert)
        let okAction = UIAlertAction(title: NSLocalizedString("Понятно", comment: ""), style: .cancel, handler: nil)
        
        alert.addAction(okAction)
        self.present(alert, animated: true, completion: nil)
    }
    
}
