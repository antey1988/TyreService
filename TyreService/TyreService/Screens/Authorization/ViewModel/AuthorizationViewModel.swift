//
//  AuthorizationViewModel.swift
//  TyreService
//
//  Created by Kirill Severin on 10.10.21.
//

import Foundation

class AuthorizationViewModel {
    
    static let shared = AuthorizationViewModel()
    
    var showErrorMessage: ((String) -> ())?
    
    func checkEmptyEmail(email: String) {
        if (isValidEmail(email) && email != "") {
            print("That's ok")
        } else {
            self.showErrorMessage?(NSLocalizedString(Errors.emptyOrIncorrectEmail, comment: ""))
        }
    }
    
    func checkEmptyPassword(password: String) -> Bool {
        if password.count <= 5 {
            return false
        } else {
            return true
        }
    }
    
    private func isValidEmail(_ email: String) -> Bool {
        let emailRegEx = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}"
        let emailPred = NSPredicate(format:"SELF MATCHES %@", emailRegEx)
        return emailPred.evaluate(with: email)
    }
    
}
