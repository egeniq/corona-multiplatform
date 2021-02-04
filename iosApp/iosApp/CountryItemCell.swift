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
    
    func setItem(item: CountryItem) {
        countryNameLabel.text = item.country
    }
}
