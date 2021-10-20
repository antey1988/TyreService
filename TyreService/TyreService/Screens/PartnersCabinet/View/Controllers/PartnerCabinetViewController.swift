//
//  PartnerCabinetViewController.swift
//  TyreService
//
//  Created by Kirill Severin on 12.10.21.
//

import UIKit

class PartnerCabinetViewController: UIViewController {
    
    @IBOutlet weak var activityIndicator: UIActivityIndicatorView!
    @IBOutlet weak var photo: UIImageView!
    @IBOutlet weak var nameTF: UITextField!
    @IBOutlet weak var descriptionTF: UITextField!
    @IBOutlet weak var phoneTF: UITextField!
    @IBOutlet weak var emailTF: UITextField!
    @IBOutlet weak var addressTF: UITextField!
    @IBOutlet weak var workingHours: UITextField!
    @IBOutlet weak var latitudeTF: UITextField!
    @IBOutlet weak var longitudeTF: UITextField!
    
    var viewModel: PartnerCabinetViewModel!
    let imagePicker = UIImagePickerController()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setUpUI()
    }
    
    //MARK: Actions
    
    @IBAction func updatePhoto(_ sender: UIButton) {
        showAlertOfActionSelection()
    }
    
    @IBAction func chooseTypeOfCar(_ sender: UIButton) {
        //TODO: finish choosing the type of car
    }
    
    @IBAction func saveCreatedInformation(_ sender: UIBarButtonItem) {
        //TODO: post request to save information
    }
    
    //MARK: - SetUpView
    
    private func setUpViewModel() {
        viewModel = PartnerCabinetViewModel(idService: 3)
        viewModel.updateInfo = { [weak self] in
            self?.stopActivityIndicator()
            self?.nameTF.text = self?.viewModel.service.name
            self?.descriptionTF.text = self?.viewModel.service.description
            self?.phoneTF.text = self?.viewModel.service.phone
            self?.emailTF.text = self?.viewModel.service.email
            self?.addressTF.text = self?.viewModel.service.address
            self?.workingHours.text = self?.viewModel.service.schedule
            self?.latitudeTF.text = self?.viewModel.service.latitude.toString()
            self?.longitudeTF.text = self?.viewModel.service.longitude.toString()
        }
    }
    
    private func setUpUI() {
        startActivityIndicator()
        setUpViewModel()
        imagePicker.delegate = self
        self.hideKeyboardWhenTappedAround()
    }
    
    private func startActivityIndicator() {
        activityIndicator.isHidden = false
        activityIndicator.startAnimating()
    }
    
    private func stopActivityIndicator() {
        activityIndicator.stopAnimating()
        activityIndicator.isHidden = true
    }
    
    //MARK: - AlertOfActionSelection
    
    private func showAlertOfActionSelection() {
        
        let alert = UIAlertController(title: nil, message: nil, preferredStyle: .actionSheet)
        let cameraAction = UIAlertAction(title: "Камера", style: .default, handler: { [weak self] _ in
            self?.openCamera()
        })
        let galleryAction = UIAlertAction(title: "Галарея", style: .default, handler: { [weak self] _ in
            self?.openGallery()
        })
        let cancelAction = UIAlertAction(title: "Отмена", style: .destructive, handler: nil)
        
        alert.addAction(cameraAction)
        alert.addAction(galleryAction)
        alert.addAction(cancelAction)
        
        self.present(alert, animated: true, completion: nil)
    }

    private func openCamera() {
        if(UIImagePickerController .isSourceTypeAvailable(UIImagePickerController.SourceType.camera)) {
            imagePicker.sourceType = UIImagePickerController.SourceType.camera
            imagePicker.allowsEditing = true
            self.present(imagePicker, animated: true, completion: nil)
        } else {
            showErrorAlert(with: "Ошибка", and: "У вас нет камеры")
        }
    }

    private func openGallery() {
        imagePicker.sourceType = UIImagePickerController.SourceType.photoLibrary
        imagePicker.allowsEditing = true
        self.present(imagePicker, animated: true, completion: nil)
    }
    
}

    //MARK: - Extension

extension PartnerCabinetViewController: UIImagePickerControllerDelegate, UINavigationControllerDelegate {

    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey : Any]) {
         guard let selectedImage = info[.originalImage] as? UIImage else {
             fatalError("\(info)")
         }
         photo.image = selectedImage
         dismiss(animated: true, completion: nil)
     }
}

extension PartnerCabinetViewController: UIScrollViewDelegate {
        
    func scrollViewWillBeginDecelerating(_ scrollView: UIScrollView) {
        if scrollView.panGestureRecognizer.translation(in: scrollView).y < 0 {
            self.navigationController?.setNavigationBarHidden(true, animated: true)
        } else {
            UIView.animate(withDuration: 1, delay: 0, options: UIView.AnimationOptions(), animations: {
                self.navigationController?.setNavigationBarHidden(false, animated: true)
                
            }, completion: nil)
        }
    }
}

