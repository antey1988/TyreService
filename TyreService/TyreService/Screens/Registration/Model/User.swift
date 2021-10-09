//
//  User.swift
//  TyreService
//
//  Created by Kirill Severin on 6.10.21.
//

import Foundation

class UserModel: NSObject, NSCoding {
    
    let name: String
    let phone: String
    let email: String
    let password: String
    
    init(name: String, phone: String, email: String, password: String) {
        self.name = name
        self.phone = phone
        self.email = email
        self.password = password
    }
    
    func encode(with coder: NSCoder) {
        coder.encode(name, forKey: "name")
        coder.encode(phone, forKey: "phone")
        coder.encode(email, forKey: "email")
        coder.encode(password, forKey: "password")
       }
       
       required init?(coder: NSCoder) {
        name = coder.decodeObject(forKey: "name") as? String ?? ""
        phone = coder.decodeObject(forKey: "phone") as? String ?? ""
        email = coder.decodeObject(forKey: "email") as? String ?? ""
        password = coder.decodeObject(forKey: "password") as? String ?? ""
           
       }
    
}


