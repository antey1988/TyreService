//
//  MenuTableViewController.swift
//  TyreService
//
//  Created by ULADZISLAU SHURYN on 19.10.21.
//

import UIKit

class MenuTableViewController: UITableViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
    }

    override func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        // если не авторизован тогда 2,3,5 indexPath скрываем
        return 80
    }

    override func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 0
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
        switch indexPath.row {
        case 0:
            print("Entry")
        case 1:
            print("LK")
        case 2:
            print("Orders")
        case 3:
            let storyBoard : UIStoryboard = UIStoryboard(name: "ServiceList", bundle:nil)
            let servicesListVC = storyBoard.instantiateViewController(withIdentifier: "ServicesListVC") as! ServicesListViewController
            navigationController?.pushViewController(servicesListVC, animated: false)
        case 4:
            print("Exit")
            break
        default:
            break
        }
    }

}
