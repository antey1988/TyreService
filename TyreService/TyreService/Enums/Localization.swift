//
//  Localization.swift
//  TyreService
//
//  Created by Kirill Severin on 15.10.21.
//

import Foundation

enum Errors {
    static let emptyName = "Введите ваше имя"
    static let emptyOrIncorrectPhone = "Введите ваш номер телефона с кодом, или проверьте его корректность" // 74957556983 - 11 символов
    static let emptyField = "Это поле не должно быть пустым"
    static let emptyOrIncorrectEmail = "Введите ваш email или проверьте его корректность"
    static let smallPassword = "Пароль должен содержать не менее шести символов"
    static let emptyOrIncorrectAddress = "Введите ваш домашний адрес, с указанием города, улицы, дома и квартиры" //Уфа,ул.Лазо,д9,кв5 - 18 символов
    static let emptyWorkingHours = "Введите время работы" //8.00-10.00 - 10 символов
    static let emptyCoordinates = "Введите координаты" 
    static let somethingWentWrong = "Что-то пошло не так"
    static let registrationFailed = "Ошибка регистрации"
    static let authorizationError = "Ошибка авторизации"
}
