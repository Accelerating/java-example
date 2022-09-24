package com.xxzou.javaexample.javase.encode.base64;

import java.util.Base64;

public class Base64Example {

    public static void main(String[] args) {
        String text = "hello world";
        byte[] data = text.getBytes();

        String encodeData = encode(data);
        System.out.println(encodeData);

        byte[] decodeData = decode(encodeData);
        System.out.println(new String(decodeData));
    }

    private static String encode(byte[] bytes){
        return Base64.getEncoder().encodeToString(bytes);
    }


    private static byte[] decode(String text){
        return Base64.getDecoder().decode(text);
    }

}
