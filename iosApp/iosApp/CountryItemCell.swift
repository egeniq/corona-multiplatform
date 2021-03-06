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
    
    func configureForCountry(countryRow: CountryRow) {
        countryNameLabel.text = countryRow.country
   
        totalCasesLabel.text =  toK(number: countryRow.totalCases)
        totalCasesLabel.textAlignment = NSTextAlignment.right
     
        newCasesLabel.text = toK(number: countryRow.newCases)
        newCasesLabel.textAlignment = NSTextAlignment.right

        totalDeathsLabel.text = toK(number: countryRow.totalDeaths)
        totalDeathsLabel.textAlignment = NSTextAlignment.right

        newDeathsLabel.text = countryRow.newDeaths?.stringValue
        newDeathsLabel.textAlignment = NSTextAlignment.right

    }
    
    func toK(number: KotlinInt?) -> String {
        if let numberInt = number?.intValue, numberInt > 1000 {
            return "\(numberInt/1000)k"
        } else {
            return number?.stringValue ?? ""
        }
    }
}
