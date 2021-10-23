//
//  PartnerServicesTableViewController.swift
//  TyreService
//
//  Created by Kirill Severin on 17.10.21.
//

import UIKit

class PartnerServicesTableViewController: UITableViewController {
    
    private var viewModel: PartnerCabinetViewModel!
    private var cellViewModel: ServicesCellViewModel!

    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    
    @IBAction func saveServices(_ sender: UIBarButtonItem) {
        //TODO: post request to save information
    }
    
    private func setUpUI() {
        navigationItem.rightBarButtonItem = editButtonItem
        navigationController?.navigationBar.tintColor = .black
    }
    
    // MARK: - TableViewDataSource

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return viewModel?.getNumberOfRows() ?? 0
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: ServiceViewCell.cellIdentifier, for: indexPath) as! ServicesTableViewCell
        //TODO: - display a list of services
        return cell
    }

}
