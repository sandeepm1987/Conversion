package com.momentum.conversion.helper;

import java.math.BigDecimal;

public class Constants {

    public static final String FORMULA_TO_C = "(x-32)*5/9";
    public static final String FORMULA_TO_F = "(x*9/5)+32";
    public static final String FORMULA_TO_DISCOUNT = "x-(x*y/100)";
    public static final String FORMULA_KG_POUND = "x*2.205";
    public static final String FORMULA_POUND_KG = "x/2.205";

    public static final String FORMULA_MIN_SEC = "60*x";
    public static final String FORMULA_SEC_MIN = "x/60";

    public static final String FORMULA_MB_GB = "x/1024";
    public static final String FORMULA_GB_MB = "1024*x";

    public static final BigDecimal NINE = new BigDecimal(9);
    public static final BigDecimal FIVE = new BigDecimal(5);
    public static final BigDecimal THIRTY_TWO = new BigDecimal(32);

    public static final Integer GLOBAL_EXCEPTION = 9001;
    public static final Integer VALIDATION_EXCEPTION = 9002;

    public enum Service {
        COMMON_SERVICE,
        DISCOUNT_SERV
    }

    public enum Unit {
        CELSIUS,
        FAHRENHEIT,

        KILOGRAM,
        POUND,

        SEC,
        MIN,

        MB,
        GB

    }

    public enum Discount {
        PERCENTAGE,

    }
}

