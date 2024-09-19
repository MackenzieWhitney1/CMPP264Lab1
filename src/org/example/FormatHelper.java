package org.example;

import java.text.NumberFormat;

public class FormatHelper {
    // number format for currency
    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();

    public static String formatCurrency(double num){
        return currency.format(num);
    }
}
