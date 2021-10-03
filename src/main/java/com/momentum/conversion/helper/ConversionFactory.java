package com.momentum.conversion.helper;

import com.momentum.conversion.Model.ConvertModel;
import com.momentum.conversion.service.ConversionService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConversionFactory {

    private static final Map<Constants.Service, ConversionService> serviceMap = new HashMap<>();

    private List<ConversionService> conversionService;

    ConversionFactory(List<ConversionService> conversionService) {
        this.conversionService = conversionService;
    }

    @PostConstruct
    public void prepareServiceMap() {
        for (ConversionService service : conversionService) {
            serviceMap.put(service.getType(), service);
        }
    }

    private static ConversionService getService(Constants.Service name) {
        return serviceMap.get(name);
        //if(service == null) throw new RuntimeException("Unknown service type: " + type);
    }

    public static void execute(ConvertModel model) throws Exception {
        getService(model.getType()).validate(model);
        getService(model.getType()).convert(model);
    }


}
