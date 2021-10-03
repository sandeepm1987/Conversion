package com.momentum.conversion.unit;

import com.momentum.conversion.Model.ConvertModel;
import com.momentum.conversion.Model.DiscountModel;
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
public class DiscountHelperTest {

    ConversionHelper conversionHelper;

    @Test
    void testDiscountConverter() throws Exception {
        ConvertModel model = new DiscountModel(Constants.Service.DISCOUNT_SERV, "100", "10");
        tempHelper(model, "90.0", "Testing discount");
    }

    @Test
    void testDiscountConverter_Exception() {
        Assertions.assertThrows(Exception.class, () -> {
            ConvertModel model = new DiscountModel(Constants.Service.DISCOUNT_SERV, "100", "10_TEST");
            tempHelper(model, "90.0", "Testing discount");
        });
    }

    private void tempHelper(ConvertModel model, String result, String message) throws Exception {
        AssertionErrors.assertEquals(message, result, conversionHelper.evaluate((DiscountModel) model));
        log.info("Completed {}", message);
    }

}
