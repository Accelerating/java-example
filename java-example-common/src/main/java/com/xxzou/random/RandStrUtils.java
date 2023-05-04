package com.xxzou.random;

/**
 * @author zouxuxuan
 */
public class RandStrUtils {

    private static final char[] CHAR_ARR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();

    public static String getRandStr(int length){
        char[] cs = new char[length];

        for (int i = 0; i < length; i++) {
            cs[i] = CHAR_ARR[RandNumUtils.getRandInt(0, CHAR_ARR.length)];
        }
        return new String(cs);
    }

}
