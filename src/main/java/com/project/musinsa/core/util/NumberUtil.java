package com.project.musinsa.core.util;

import java.text.DecimalFormat;

public class NumberUtil {
    public static String numberComma(Long price) {
        DecimalFormat format = new DecimalFormat("###,###");
        return format.format(price);
    }
}
