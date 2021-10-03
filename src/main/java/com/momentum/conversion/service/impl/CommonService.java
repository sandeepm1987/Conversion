package com.momentum.conversion.service.impl;

import com.momentum.conversion.helper.Constants;
import com.momentum.conversion.service.ConversionService;
import org.springframework.stereotype.Service;

@Service
public class CommonService implements ConversionService {

    @Override
    public Constants.Service getType() {
        return Constants.Service.COMMON_SERVICE;
    }
}
