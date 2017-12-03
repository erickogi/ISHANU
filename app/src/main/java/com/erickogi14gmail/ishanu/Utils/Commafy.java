package com.erickogi14gmail.ishanu.Utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Eric on 12/3/2017.
 */

public class Commafy {
    public static String addCommify(String no) {
        String commified = "0.0";
        Double number = 0.0;
        try {
            number = Double.valueOf(no);
        } catch (Exception nfe) {

        }
        NumberFormat anotherFormatU = NumberFormat.getNumberInstance(Locale.US);
        if (anotherFormatU instanceof DecimalFormat) {
            DecimalFormat anotherDFormat = (DecimalFormat) anotherFormatU;
            anotherDFormat.applyPattern("#.00");
            anotherDFormat.setGroupingUsed(true);
            anotherDFormat.setGroupingSize(3);

            commified = anotherDFormat.format(number);
        }


        return commified;
    }

    public static String removeCommify(String no) {
        String regex = "(?<=[\\d])(,)(?=[\\d])";
        Pattern p = Pattern.compile(regex);
        String str = no;
        Matcher m = p.matcher(str);
        str = m.replaceAll("");
        return str;
    }
}
