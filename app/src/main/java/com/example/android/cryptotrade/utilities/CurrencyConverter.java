package com.example.android.cryptotrade.utilities;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Created by DITHA on 15/07/2017.
 */

public class CurrencyConverter {
    public DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
    public DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

    public CurrencyConverter() {
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
    }
}
