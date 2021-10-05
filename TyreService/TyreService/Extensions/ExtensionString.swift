//
//  ExtensionString.swift
//  TyreService
//
//  Created by ULADZISLAU SHURYN on 4.10.21.
//

import Foundation
import UIKit

extension String {
    func widthOfString(font: UIFont) -> CGFloat {
        let fontAttributes = [NSAttributedString.Key.font: font]
        let size = (self as NSString).size(withAttributes: fontAttributes)
        return ceil(size.width)
    }
}
