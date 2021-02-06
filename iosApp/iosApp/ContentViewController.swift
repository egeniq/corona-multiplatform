//
//  ContentViewController.swift
//  iosApp
//
//  Created by Johan Kool on 18/12/2020.
//  Copyright © 2020 orgName. All rights reserved.
//

import UIKit
import shared

class ContentViewController: UIViewController {

    @IBOutlet var tableView: UITableView!
    
    let mainViewModel = MainViewModel()
    let kermit = Kermit(loggerList: [NSLogLogger()], defaultTag: "iOSTag")

    var items: [CountryItem] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()

        tableView.register(UINib(nibName: "CountryItemCell", bundle: nil), forCellReuseIdentifier: "CountryItemCell")
        
        tableView.delegate = self
        tableView.dataSource = self
        
        // Do any additional setup after loading the view.
        mainViewModel.setup(kermit: kermit)
        
        mainViewModel.pageResultLD.addObserver { data in
            if let data = data {
                print(data)
                if let result = data as? Result {
                    result.resolve { (payload: Any?) in
                        if let items = payload as? [CountryItem] {
                            self.items = items
                            self.tableView.reloadData()
                        }
                    } onError: { (ex: KotlinThrowable) in
                        // handle error
                    }

                }
            } else {
                
            }
        }
        mainViewModel.fetchData()
    }

}

extension ContentViewController: UITableViewDelegate {
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {

    }
    
}

extension ContentViewController: UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return items.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell  = tableView.dequeueReusableCell(withIdentifier: "CountryItemCell") as! CountryItemCell
        cell.setItem(item: items[indexPath.row])
        return cell
    }
}
