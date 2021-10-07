//
//  SignUpViewModel.swift
//  TyreService
//
//  Created by Kirill Severin on 6.10.21.
//

import Foundation

class SignUpViewModel {
    
    var showErrorMessage: ((String) -> ())?
    
    func registration(name: String, phone: String, email: String, passwordOne: String, passwordTho: String, completion: @escaping ((Error?) -> Void)) {
        
        let nameText = name.trimmingCharacters(in: .whitespaces)
        let phoneText = phone.trimmingCharacters(in: .whitespaces)
        let emailText = email.trimmingCharacters(in: .whitespaces)
        let passwordOneText = passwordOne.trimmingCharacters(in: .whitespaces)
        let passwordThoText = passwordTho.trimmingCharacters(in: .whitespaces)
        
        if nameText == "" {
            self.showErrorMessage?(NSLocalizedString(Errors.emptyName, comment: ""))
            return
        }
        if phoneText == "" {
            self.showErrorMessage?(NSLocalizedString(Errors.emptyPhone, comment: ""))
            return
        }
        if emailText == "" {
            self.showErrorMessage?(NSLocalizedString(Errors.emptyEmail, comment: ""))
            return
        }
        if passwordOneText == "" {
            self.showErrorMessage?(NSLocalizedString(Errors.emptyPassword, comment: ""))
            return
        }
        if passwordThoText == "" {
            self.showErrorMessage?(NSLocalizedString(Errors.enterPasswordAgain, comment: ""))
        }
        if passwordOne.count < 6 {
            self.showErrorMessage?(NSLocalizedString(Errors.smallPassword, comment: ""))
            return
        }
        if passwordOne != passwordTho {
            self.showErrorMessage?(NSLocalizedString(Errors.passwordDiscrepancy, comment: ""))
            return
        }
        
        let userObject = UserModel(name: nameText, phone: phoneText, email: emailText, password: passwordThoText)
        
        UserManager.nameOrganization = nameText
        UserManager.phoneOrganization = phoneText
        UserManager.emailOrganization = emailText
        UserManager.password = passwordThoText
        UserManager.userModel = userObject
        
        return
    }
    
    //return сложность пароля я передаю пароль,он возвращает значение
    
    func checkPasswordStrength(password: String) -> Int {
        var strengthPassword = 0
        
        let passwordWithBigCh = NSPredicate(format: "SELF MATCHES %@ ",
                                            "^(?=.*[a-z])(?=.*[A-Z]).{6,}$")
        let passwordWithDigit = NSPredicate(format: "SELF MATCHES %@ ",
                                            "^(?=.*[a-z])(?=.*[0-9]).{6,}$")
        let passwordWithSpecialChar = NSPredicate(format: "SELF MATCHES %@ ",
                                                  "^(?=.*[a-z])(?=.*[$@$#!%*?&]).{6,}$")
        let passwordWithBigChAndDigit = NSPredicate(format: "SELF MATCHES %@ ",
                                                    "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,}$")
        let passwordWithBigChAndSpecialCh = NSPredicate(format: "SELF MATCHES %@ ",
                                                        "^(?=.*[a-z])(?=.*[A-Z])(?=.*[$@$#!%*?&]).{8,}$")
        let passwordWithDigitAndSpecialCh = NSPredicate(format: "SELF MATCHES %@ ",
                                                        "^(?=.*[a-z])(?=.*[0-9])(?=.*[$@$#!%*?&]).{8,}$")
        let hardestPassword = NSPredicate(format: "SELF MATCHES %@ ",
                                          "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$#!%*?&]).{10,}$")
        
        switch password {
        case _ where hardestPassword.evaluate(with: password) :
            strengthPassword = 1
        case _ where passwordWithBigChAndDigit.evaluate(with: password) || passwordWithBigChAndSpecialCh.evaluate(with: password) || passwordWithDigitAndSpecialCh.evaluate(with: password) :
            strengthPassword = 2
        case _ where passwordWithDigit.evaluate(with: password) || passwordWithBigCh.evaluate(with: password) || passwordWithSpecialChar.evaluate(with: password) :
            strengthPassword = 3
        case _ where password.count >= 6 :
            strengthPassword = 4
        default:
            strengthPassword = 0
        }
        return strengthPassword
    }
    
}
