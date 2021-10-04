package com.momentum.conversion.helper;

import com.momentum.conversion.Model.CommonModel;
import com.momentum.conversion.Model.ConvertModel;
import com.momentum.conversion.Model.DiscountModel;
import com.momentum.conversion.config.ConversionException;
import lombok.extern.slf4j.Slf4j;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Optional;

import static com.momentum.conversion.helper.Constants.*;
import static com.momentum.conversion.helper.Constants.Unit.*;

@Component
@Slf4j
public class ConversionHelper {

    private static final EnumMap<Constants.Unit, EnumMap<Constants.Unit, String>> commonEnumMap = new EnumMap<>(Constants.Unit.class);

    static {
        fillCommonMap();
    }

    private static void fillCommonMap() {
        fillTempMap();
        fillMassMap();
        fillTimeMap();
        fillDigitalStorageMap();
    }

    private static void fillTempMap() {
        EnumMap<Constants.Unit, String> toMap = new EnumMap<>(Constants.Unit.class);
        toMap.put(FAHRENHEIT, FORMULA_TO_F);
        commonEnumMap.put(CELSIUS, toMap);
        toMap = new EnumMap<>(Constants.Unit.class);
        toMap.put(CELSIUS, Constants.FORMULA_TO_C);
        commonEnumMap.put(FAHRENHEIT, toMap);
    }

    private static void fillMassMap() {
        EnumMap<Constants.Unit, String> toMap = new EnumMap<>(Constants.Unit.class);
        toMap.put(POUND, Constants.FORMULA_KG_POUND);
        commonEnumMap.put(KILOGRAM, toMap);
        toMap = new EnumMap<>(Constants.Unit.class);
        toMap.put(KILOGRAM, FORMULA_POUND_KG);
        commonEnumMap.put(POUND, toMap);
    }

    private static void fillTimeMap() {
        EnumMap<Constants.Unit, String> toMap = new EnumMap<>(Constants.Unit.class);
        toMap.put(SEC, FORMULA_MIN_SEC);
        commonEnumMap.put(MIN, toMap);
        toMap = new EnumMap<>(Constants.Unit.class);
        toMap.put(MIN, FORMULA_SEC_MIN);
        commonEnumMap.put(SEC, toMap);
    }

    private static void fillDigitalStorageMap() {
        EnumMap<Constants.Unit, String> toMap = new EnumMap<>(Constants.Unit.class);
        toMap.put(MB, FORMULA_GB_MB);
        commonEnumMap.put(GB, toMap);
        toMap = new EnumMap<>(Constants.Unit.class);
        toMap.put(GB, FORMULA_MB_GB);
        commonEnumMap.put(MB, toMap);
    }

    private static String getExpression(String from, String to, Constants.Service service) throws ConversionException {
        String result;
        switch (service) {
            case COMMON_SERVICE:
                result = getResult(Constants.Unit.class, from, to, commonEnumMap);
                break;
            default:
                throw new ConversionException("Given conversion is not available");
        }
        return result;
    }

    private static <E extends Enum<E>> String getResult(Class<E> type, String from, String to, EnumMap<E, EnumMap<E, String>> map) throws ConversionException {
        E fromE = lookup(type, from);
        E fromT = lookup(type, to);

        Optional<EnumMap<E, String>> optionalEnumMap = Optional.ofNullable(map.get(fromE));
        if (optionalEnumMap.isPresent()) {
            Optional<String> optionalS = Optional.ofNullable(optionalEnumMap.get().get(fromT));
            if (optionalS.isPresent()) {
                return optionalS.get();
            }
        }
        log.error("Mapping is missing for Type:{}, From:{}, To:{}", type.getName(), from, to);
        throw new ConversionException();
    }


    private static <E extends Enum<E>> E lookup(Class<E> e, String value) throws ConversionException {
        try {
            return Enum.valueOf(e, value);
        } catch (IllegalArgumentException ex) {
            throw new ConversionException("Invalid input value for " + e.getSimpleName() + ": " + value, ex);
        }
    }


    public static String evaluate(ConvertModel model) throws ConversionException {
        if (model instanceof CommonModel) {
            CommonModel commonModel = (CommonModel) model;
            Expression builder = new ExpressionBuilder(getExpression(commonModel.getFrom(), commonModel.getTo(), commonModel.getType()))
                    .variable("x")
                    .build()
                    .setVariable("x", Double.parseDouble(commonModel.getInput()));
            return String.valueOf(builder.evaluate());
        }
        log.error("Model mapping is missing");
        throw new ConversionException();
    }

    public static String evaluate(DiscountModel model) {
        Expression builder = new ExpressionBuilder(Constants.FORMULA_TO_DISCOUNT)
                .variable("x")
                .variable("y")
                .build()
                .setVariable("x", Double.parseDouble(model.getInput()))
                .setVariable("y", Double.parseDouble(model.getPercentageDiscount()));
        return String.valueOf(builder.evaluate());

    }


}

