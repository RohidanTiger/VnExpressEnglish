package vnexpressenglish.chickenzero.ht.com.vnexpressenglish.utils;

import java.text.DecimalFormat;

import tigerstyle.social.com.banggiaxe.config.Contants;

/**
 * Created by billymobile on 1/5/17.
 */

public class NumberFormater {
    public static String longFormat(long number){
        DecimalFormat formatter = new DecimalFormat(Contants.NUMBER_FORMAT);
        return formatter.format(number).replaceAll(",",".");
    }

    public static String twoDecimaFormat(Double number){
        return String.format("%.2f", number);
    }
}
