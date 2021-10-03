package com.momentum.conversion.service.impl;

import com.momentum.conversion.Model.ConvertModel;
import com.momentum.conversion.Model.DiscountModel;
import com.momentum.conversion.config.ConversionException;
import com.momentum.conversion.helper.Constants;
import com.momentum.conversion.helper.ConversionHelper;
import com.momentum.conversion.service.ConversionService;
import org.springframework.stereotype.Service;

@Service
public class DiscountService implements ConversionService {

    @Override
    public Constants.Service getType() {
        return Constants.Service.DISCOUNT_SERV;
    }

    @Override
    public void convert(ConvertModel model) {
        model.setResult(ConversionHelper.evaluate((DiscountModel) model));
    }

    @Override
    public void validate(ConvertModel model) throws Exception {
        ConversionService.super.validate(model);
        String regex = "[0-9]+";
        DiscountModel m = (DiscountModel) model;
        if (m != null) {
            if (m.getPercentageDiscount() == null || m.getPercentageDiscount().isEmpty()) {
                throw new ConversionException("Input Percentage is required");
            } else if (!m.getPercentageDiscount().matches(regex)) {
                throw new ConversionException("Input Percentage should be numeric value");
            }
        }
    }
}
