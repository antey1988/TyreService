//
//  ViewController.swift
//  TyreService
//
//  Created by ULADZISLAU SHURYN on 30.09.21.
//

import UIKit

class PartnersViewController: UIViewController {

    @IBOutlet weak var filterCollectionView: UICollectionView!
    @IBOutlet weak var partnersTableView: UITableView!
    
    lazy var viewModel: PartnersListViewModel = {
        return PartnersListViewModel()
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        initCollectionView()
        initTableView()
    }
    
    func initCollectionView() {
        self.filterCollectionView.register(FilterCollectionViewCell.nib(), forCellWithReuseIdentifier: FilterCollectionViewCell.cellIdentifier)
        self.filterCollectionView.delegate = self
        self.filterCollectionView.dataSource = self
        viewModel.reloadFilter = { [weak self] in
            self?.filterCollectionView.reloadData()
        }
    }
    
    func initTableView() {
        self.partnersTableView.register(PartnerTableViewCell.nib(), forCellReuseIdentifier: PartnerTableViewCell.cellIdentifier)
        self.partnersTableView.delegate = self
        self.partnersTableView.dataSource = self
        viewModel.reloadPartners = { [weak self] in
            self?.partnersTableView.reloadData()
        }
    }

}

extension PartnersViewController: UICollectionViewDelegate, UICollectionViewDataSource, UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return viewModel.getNumberGroupsCell()
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: FilterCollectionViewCell.cellIdentifier, for: indexPath) as! FilterCollectionViewCell
        cell.configure(viewModel: viewModel.getGroupViewModelById(id: indexPath.row))
        return cell
    }

    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let cellViewModel = viewModel.getGroupViewModelById(id: indexPath.row)
        let width = cellViewModel.filter.name.widthOfString(font: UIFont.preferredFont(forTextStyle: UIFont.TextStyle.body))
        return CGSize(width: width + 40, height: collectionView.frame.height)
    }

    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumInteritemSpacingForSectionAt section: Int) -> CGFloat {
        return 10
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        viewModel.changeSelectedFilterId(index: indexPath.row)
        self.filterCollectionView.scrollToItem(at: indexPath, at: .centeredHorizontally, animated: true)
    }
}

extension PartnersViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return viewModel.getNumberPartnerCell()
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: PartnerTableViewCell.cellIdentifier, for: indexPath) as! PartnerTableViewCell
        cell.configure(viewModel: viewModel.getPartnerViewModelById(id: indexPath.row))
        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return UITableView.automaticDimension
    }
}
