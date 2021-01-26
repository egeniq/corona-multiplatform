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

    let mainViewModel = MainViewModel()
    let kermit = Kermit(loggerList: [NSLogLogger()], defaultTag: "iOSTag")

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        mainViewModel.setup(kermit: kermit)
        mainViewModel.pageData.addObserver { data in
            if let data = data {
                print(data)
            } else {
                print("Received nil")
            }
        }
        mainViewModel.fetchData()
    }


    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
