//
//  ServiceInfoViewController.swift
//  TyreService
//
//  Created by ULADZISLAU SHURYN on 12.10.21.
//

import UIKit

class ServiceInfoViewController: UIViewController {

    @IBOutlet weak var menuCollectionView: UICollectionView!
    @IBOutlet weak var infoCollectionView: UICollectionView!
    @IBOutlet weak var serviceNameView: UIView!
    @IBOutlet weak var serviceNameLabel: UILabel!
    
    weak var viewModel: ServiceInfoViewModel!

    override func viewDidLoad() {
        super.viewDidLoad()
        initView()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: false)
    }
    
    func initView() {
        serviceNameView.layer.cornerRadius = 8
        serviceNameLabel.text = viewModel.getServiceName()
        initMenuCollectionView()
        initInfoCollectionView()
    }
    
    func initMenuCollectionView() {
        menuCollectionView.register(MenuServiceInfoCollectionViewCell.nib(), forCellWithReuseIdentifier: MenuServiceInfoCollectionViewCell.cellIdentifier)
        menuCollectionView.delegate = self
        menuCollectionView.dataSource = self
        menuCollectionView.layer.cornerRadius = 8
        viewModel.reloadMenu = { [weak self] in
            self?.menuCollectionView.reloadData()
        }
    }
    
    func initInfoCollectionView() {
        infoCollectionView.register(ServicesCollectionViewCell.nib(), forCellWithReuseIdentifier: ServicesCollectionViewCell.cellIdentifier)
        infoCollectionView.register(ContacntsCollectionViewCell.nib(), forCellWithReuseIdentifier: ContacntsCollectionViewCell.cellIdentifier)
        infoCollectionView.register(ReviewsCollectionViewCell.nib(), forCellWithReuseIdentifier: ReviewsCollectionViewCell.cellIdentifier)
        infoCollectionView.delegate = self
        infoCollectionView.dataSource = self
    }
}

extension ServiceInfoViewController: UICollectionViewDelegate, UICollectionViewDataSource, UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return viewModel.getNumberMenuItems()
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        if collectionView == infoCollectionView {
            switch indexPath.row {
            case 0:
                let cell = collectionView.dequeueReusableCell(withReuseIdentifier: ServicesCollectionViewCell.cellIdentifier, for: indexPath) as! ServicesCollectionViewCell
                cell.configure(viewModel: viewModel.getServicesCellViewModel())
                return cell
            case 1:
                let cell = collectionView.dequeueReusableCell(withReuseIdentifier: ContacntsCollectionViewCell.cellIdentifier, for: indexPath) as! ContacntsCollectionViewCell
                cell.configure(viewModel: viewModel.getContactsCellViewModel())
                return cell
            default:
                let cell = collectionView.dequeueReusableCell(withReuseIdentifier: ReviewsCollectionViewCell.cellIdentifier, for: indexPath) as! ReviewsCollectionViewCell
                return cell
            }
        }
        
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: MenuServiceInfoCollectionViewCell.cellIdentifier, for: indexPath) as! MenuServiceInfoCollectionViewCell
        cell.configure(viewModel: viewModel.getMenuItemViewModel(index: indexPath.row))
        return cell
    }

    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        if collectionView == infoCollectionView {
            return CGSize(width: UIScreen.main.bounds.width - 30, height: collectionView.frame.height)
        }
        return CGSize(width: (UIScreen.main.bounds.width - 40) / 3, height: collectionView.frame.height)
    }

    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        if collectionView == menuCollectionView {
            viewModel.changeSelectedMenuItem(index: indexPath.row)
            infoCollectionView.scrollToItem(at: indexPath, at: .right, animated: true)
        }
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumLineSpacingForSectionAt section: Int) -> CGFloat {
        return 10
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, insetForSectionAt section: Int) -> UIEdgeInsets {
        if collectionView == menuCollectionView {
            return UIEdgeInsets(top: 0, left: 0, bottom: 0, right: 0)
        }
        return UIEdgeInsets(top: 0, left: 5, bottom: 0, right: 5)
    }
    
    func scrollViewDidEndDecelerating(_ scrollView: UIScrollView) {
        if scrollView == infoCollectionView {
            if let visibleCell = infoCollectionView.visibleCells.first, let indexPath = infoCollectionView.indexPath(for: visibleCell) {
                viewModel.changeSelectedMenuItem(index: indexPath.row)
            }
        }
    }
}
