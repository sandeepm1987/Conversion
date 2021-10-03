package com.momentum.conversion.unit;

import com.momentum.conversion.Model.CommonModel;
import com.momentum.conversion.Model.ConvertModel;
import com.momentum.conversion.helper.Constants;
import com.momentum.conversion.helper.ConversionHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.AssertionErrors;

@ExtendWith(SpringExtension.class)
@Slf4j
public class CommonHelperTest {

    ConversionHelper conversionHelper;

    @Test
    void testTempConverterC_F() throws Exception {
        ConvertModel model = new CommonModel(Constants.Service.COMMON_SERVICE, "celsius", "fahrenheit", "15");
        tempHelper(model, "59.0", "Testing Temperature");
    }

    @Test
    void testTempConverterF_C() throws Exception {
        ConvertModel model = new CommonModel(Constants.Service.COMMON_SERVICE, "fahrenheit", "celsius", "59.0");
        tempHelper(model, "15.0", "Testing Temperature");
    }

    @Test
    void testTempConverterF_C_Exception() {
        Assertions.assertThrows(Exception.class, () -> {
            ConvertModel model = new CommonModel(Constants.Service.COMMON_SERVICE, "fahrenheit1", "celsius", "59.0");
            tempHelper(model, "15.0", "Testing Temperature");
        });
    }

    @Test
    void testTempConverterF_C_Exception_1() {
        Assertions.assertThrows(Exception.class, () -> {
            ConvertModel model = new CommonModel(Constants.Service.COMMON_SERVICE, "fahrenheit", "celsius", "test");
            tempHelper(model, "15.0", "Testing Temperature");
        });
    }

    @Test
    void testMassConverterK_P() throws Exception {
        ConvertModel model = new CommonModel(Constants.Service.COMMON_SERVICE, "kilogram", "pound", "20");
        tempHelper(model, "44.1", "Testing Mass");
    }

    @Test
    void testMassConverterP_K() throws Exception {
        ConvertModel model = new CommonModel(Constants.Service.COMMON_SERVICE, "pound", "kilogram", "44.1");
        tempHelper(model, "20.0", "Testing Mass");
    }

    @Test
    void testMassConverterK_P_Exception() {
        Assertions.assertThrows(Exception.class, () -> {
            ConvertModel model = new CommonModel(Constants.Service.COMMON_SERVICE, "kilogram", "test", "59.0");
            tempHelper(model, "15.0", "Testing Mass");
        });
    }

    @Test
    void testTimeConverterK_P() throws Exception {
        ConvertModel model = new CommonModel(Constants.Service.COMMON_SERVICE, "min", "sec", "1");
        tempHelper(model, "60.0", "Testing Time");
    }

    @Test
    void testDataConverterG_M() throws Exception {
        ConvertModel model = new CommonModel(Constants.Service.COMMON_SERVICE, "gb", "mb", "1");
        tempHelper(model, "1024.0", "Testing Data");
    }



    private void tempHelper(ConvertModel model, String result, String message) throws Exception {
        AssertionErrors.assertEquals(message, result, conversionHelper.evaluate(model));
        log.info("Completed {}", message);
    }

}
