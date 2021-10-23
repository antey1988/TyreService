//
//  UIViewController.swift
//  TyreService
//
//  Created by Kirill Severin on 6.10.21.
//

import UIKit

extension UIViewController {
    
    func showErrorAlert(with title: String = NSLocalizedString("Ошибка", comment: ""), and message: String, handler: @escaping () -> Void = { }) {
        
        let alert = UIAlertController(title: title, message: message, preferredStyle: .alert)
        let okAction = UIAlertAction(title: NSLocalizedString("ОК", comment: ""), style: .cancel, handler: { (actin) in
            handler()
        })
        
        alert.addAction(okAction)
        self.present(alert, animated: true, completion: nil)
    }
    
    func hideKeyboardWhenTappedAround() {
        let tap = UITapGestureRecognizer(target: self, action: #selector(UIViewController.dismissKeyboard))
        tap.cancelsTouchesInView = false
        view.addGestureRecognizer(tap)
    }
    
    @objc func dismissKeyboard() {
        view.endEditing(true)
    }
    
}
