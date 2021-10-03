package com.momentum.conversion.controller;

import com.momentum.conversion.Model.CommonModel;
import com.momentum.conversion.Model.ConvertModel;
import com.momentum.conversion.Model.DiscountModel;
import com.momentum.conversion.helper.Constants;
import com.momentum.conversion.helper.ConversionFactory;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("convert/v1/")
@Slf4j
public class ConversionController {

    ConversionFactory factory;

    ConversionController(ConversionFactory factory) {
        this.factory = factory;
    }

    @GetMapping(path = {
            "/temperature/{from}/{to}/{value}",
            "/mass/{from}/{to}/{value}",
            "/time/{from}/{to}/{value}",
            "/digitalstorage/{from}/{to}/{value}"
    })
    @ApiOperation(value = "Converter")
    public ResponseEntity<ConvertModel> commonConverter(@PathVariable String from, @PathVariable String to, @PathVariable String value) throws Exception {
        ConvertModel model = new CommonModel(Constants.Service.COMMON_SERVICE, from, to, value);
        factory.execute(model);
        return ResponseEntity.ok(model);
    }

    @GetMapping("/discount/{originalPrice}/{discount}")
    @ApiOperation(value = "Calculate Discount")
    public ResponseEntity<ConvertModel> discountConverter(@PathVariable String originalPrice, @PathVariable String discount) throws Exception {
        ConvertModel model = new DiscountModel(Constants.Service.DISCOUNT_SERV, originalPrice, discount);
        factory.execute(model);
        return ResponseEntity.ok(model);
    }
}
