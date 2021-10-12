//
//  AuthorizationViewController.swift
//  TyreService
//
//  Created by Kirill Severin on 10.10.21.
//

import UIKit

class AuthorizationViewController: UIViewController {
    
    @IBOutlet weak var emailTF: UITextField!
    @IBOutlet weak var passwordTF: UITextField!
    @IBOutlet weak var signInButton: UIButton!
    @IBOutlet weak var signUpButton: UIButton!
    
    var viewModel: AuthorizationViewModel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setUpViewModel()
        signInButton.isEnabled = false
    }
    
    @IBAction func emailTextField(_ sender: UITextField) {
        viewModel.checkEmptyEmail(email: emailTF.text ?? "")
    }
    
    @IBAction func passwordTextField(_ sender: UITextField) {
        if viewModel.checkEmptyPassword(password: passwordTF.text ?? "") {
            self.signInButtonTrue()
        } else {
            self.signInButtonFalse()
        }
    }
    
    @IBAction func signInButtonPressed(_ sender: UIButton) {
        //authorization and prepare
    }
    
    @IBAction func signUpButtonPressed(_ sender: UIButton) {
        //prepare
    }
    
    private func signInButtonTrue() {
        signInButton.isEnabled = true
    }
    
    private func signInButtonFalse() {
        signInButton.isEnabled = false
    }
    
    private func setUpViewModel() {
        viewModel = AuthorizationViewModel()
        viewModel.showErrorMessage = { errorMessage in
            self.showErrorAlert(and: errorMessage)
        }
    }
    
}
