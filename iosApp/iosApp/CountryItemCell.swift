//
//  CountryItemCell.swift
//  iosApp
//
//  Created by Dmytro Kovalenko on 03.02.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import UIKit
import shared

class CountryItemCell: UITableViewCell {
    
    
    @IBOutlet weak var countryNameLabel: UILabel!
    @IBOutlet weak var totalCasesLabel: UILabel!
    @IBOutlet weak var newCasesLabel: UILabel!
    @IBOutlet weak var totalDeathsLabel: UILabel!
    @IBOutlet weak var newDeathsLabel: UILabel!
    
    func setItem(item: CountryItem) {
        countryNameLabel.text = item.country
        totalCasesLabel.text =  toK(number: item.totalCases)
        newCasesLabel.text = item.newCases?.stringValue
        totalDeathsLabel.text = toK(number: item.totalDeaths)
        newDeathsLabel.text = item.newDeaths?.stringValue
    }
    
    func toK(number: KotlinInt?) -> String {
        if let numberInt = number?.intValue, numberInt > 1000 {
            return "\(numberInt/1000)k"
        } else {
            return number?.stringValue ?? ""
        }
    }
}
