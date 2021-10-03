package com.momentum.conversion.service;

import com.momentum.conversion.Model.ConvertModel;
import com.momentum.conversion.config.ConversionException;
import com.momentum.conversion.helper.Constants;
import com.momentum.conversion.helper.ConversionHelper;

public interface ConversionService {
    Constants.Service getType();

    default void convert(ConvertModel model) throws Exception {
        model.setResult(ConversionHelper.evaluate(model));
    }

    default void validate(ConvertModel model) throws Exception {
        String regex = "[0-9]+";
        if (model != null) {
            if (model.getInput() == null || model.getInput().isEmpty()) {
                throw new ConversionException("Input conversion is required");
            } else if (!model.getInput().matches(regex)) {
                throw new ConversionException("Input conversion should be numeric value");
            }
        }
    }
}
