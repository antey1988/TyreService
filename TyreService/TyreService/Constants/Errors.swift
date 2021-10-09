//
//  Errors.swift
//  TyreService
//
//  Created by Kirill Severin on 6.10.21.
//

import Foundation

class Errors {
    
    static let shared = Errors()
    
    static let emptyName = "Введите имя"
    static let emptyPhone = "Введите номер телефона"
    static let emptyEmail = "Введите email"
    static let emptyPassword = "Введите пароль"
    static let enterPasswordAgain = "Введите пароль ещё раз"
    static let passwordDiscrepancy = "Пароли не совпадают"
    static let somethingWentWrong = "Что-то пошло не так"
    static let smallPassword = "Пароль должен содержать не менее шести символов"
}
