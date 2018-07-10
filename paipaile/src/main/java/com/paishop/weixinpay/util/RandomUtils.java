package com.paishop.weixinpay.util;

import java.util.Random;


public class RandomUtils {
    public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String numberChar = "0123456789";

   
    public static String generateString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(allChar.charAt(random.nextInt(allChar.length())));
        }
        return sb.toString();
    }

    /**
     * 杩斿洖涓�涓畾闀跨殑闅忔満绾瓧姣嶅瓧绗︿覆(鍙寘鍚ぇ灏忓啓瀛楁瘝)
     *
     * @param length 闅忔満瀛楃涓查暱搴�
     * @return 闅忔満瀛楃涓�
     */
    public static String generateMixString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(allChar.charAt(random.nextInt(letterChar.length())));
        }
        return sb.toString();
    }

    /**
     * 杩斿洖涓�涓畾闀跨殑闅忔満绾皬鍐欏瓧姣嶅瓧绗︿覆(鍙寘鍚皬鍐欏瓧姣�)
     *
     * @param length 闅忔満瀛楃涓查暱搴�
     * @return 闅忔満瀛楃涓�
     */
    public static String generateLowerString(int length) {
        return generateMixString(length).toLowerCase();
    }

    /**
     * 杩斿洖涓�涓畾闀跨殑闅忔満绾ぇ鍐欏瓧姣嶅瓧绗︿覆(鍙寘鍚ぇ鍐欏瓧姣�)
     *
     * @param length 闅忔満瀛楃涓查暱搴�
     * @return 闅忔満瀛楃涓�
     */
    public static String generateUpperString(int length) {
        return generateMixString(length).toUpperCase();
    }


    public static void main(String[] args) {
        System.out.println(generateString(32));
        System.out.println(generateMixString(32));
        System.out.println(generateLowerString(32));
        System.out.println(generateUpperString(32));
    }
}