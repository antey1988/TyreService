//
//  UserManager.swift
//  TyreService
//
//  Created by Kirill Severin on 6.10.21.
//

import Foundation

final class UserManager {
    
    private enum SettingsKeys: String {
        case userModel
        case nameOrganization
        case phoneOrganization
        case emailOrganization
        case password
    }
    
    static var userModel: UserModel! {
        get {
            let key = SettingsKeys.userModel.rawValue
            guard let savedData = UserDefaults.standard.object(forKey: key) as? Data,
                  let decodedModel = try?
                    NSKeyedUnarchiver.unarchiveTopLevelObjectWithData(savedData) as? UserModel else {
                        return nil
                    }
            return decodedModel
        } set {
            let defaults = UserDefaults.standard
            let key = SettingsKeys.userModel.rawValue
            
            if let userModel = newValue {
                if let savedData = try? NSKeyedArchiver.archivedData(withRootObject: userModel, requiringSecureCoding: false) {
                    print("value: \(userModel) was added to key \(key)")
                    defaults.set(savedData, forKey: key)
                } else {
                    defaults.removeObject(forKey: key)
                }
            }
        }
    }
    
    static var nameOrganization: String! {
        get {
            let key = SettingsKeys.nameOrganization.rawValue
            return UserDefaults.standard.string(forKey: key)
        } set {
            let defaults = UserDefaults.standard
            let key = SettingsKeys.nameOrganization.rawValue
            if let name = newValue {
                print("value: \(name) was added to key \(key)")
                defaults.set(name, forKey: key)
            } else {
                defaults.removeObject(forKey: key)
            }
        }
    }
    
    static var phoneOrganization: String! {
        get {
            let key = SettingsKeys.phoneOrganization.rawValue
            return UserDefaults.standard.string(forKey: key)
        } set {
            let defaults = UserDefaults.standard
            let key = SettingsKeys.phoneOrganization.rawValue
            if let phone = newValue {
                print("value: \(phone) was added to key \(key)")
                defaults.set(phone, forKey: key)
            } else {
                defaults.removeObject(forKey: key)
            }
        }
    }
    
    static var emailOrganization: String! {
        get {
            let key = SettingsKeys.emailOrganization.rawValue
            return UserDefaults.standard.string(forKey: key)
        } set {
            let defaults = UserDefaults.standard
            let key = SettingsKeys.emailOrganization.rawValue
            if let email = newValue {
                print("value: \(email) was added to key \(key)")
                defaults.set(email, forKey: key)
            } else {
                defaults.removeObject(forKey: key)
            }
        }
    }
    
    static var password: String! {
        get {
            let key = SettingsKeys.password.rawValue
            return UserDefaults.standard.string(forKey: key)
        } set {
            let defaults = UserDefaults.standard
            let key = SettingsKeys.password.rawValue
            if let password = newValue {
                print("value: \(password) was added to key \(key)")
                defaults.set(password, forKey: key)
            } else {
                defaults.removeObject(forKey: key)
            }
        }
    }
    
}
