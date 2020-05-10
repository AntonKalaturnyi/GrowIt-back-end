package com.growit.api.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class ConstantUtil {

    public static final String VALID_POSTAL_CODE_REGEXP = "\\d{5}";

    public static final String VALID_PHONE = "^\\+?3?8?(0[5-9][0-9]\\d{7})$";

    public static final String LAND_UA = "ua";

    public static final String LAND_ENG = "eng";

    public static final String RANK_A = "A";

    public static final String RANK_B = "B";

    public static final String RANK_C = "C";

    public static final String RANK_D = "D";

    public static final String RANK_E = "E";

    public static final String TRANS_IN = "IN";

    public static final String TRANS_OUT = "OUT";

    public static final String FIRST_PRIORITY = "FIRST";

    public static final String SECOND_PRIORITY = "SECOND";

    public static final String THIRD_PRIORITY = "THIRD";

    public static final String FOURTH_PRIORITY = "FOURTH";

    public ConstantUtil() {
    }

    public static int getRandom6DigitNumber() {
        return new Random().nextInt((999999 - 100000) + 1) + 100000;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
