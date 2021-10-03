package com.momentum.conversion.Model;

import com.momentum.conversion.helper.Constants;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountModel extends ConvertModel {

    String percentageDiscount;

    public DiscountModel(Constants.Service type, String originalPrice, String percentageDiscount) {
        super(type, originalPrice);
        this.percentageDiscount = percentageDiscount;
    }
}
