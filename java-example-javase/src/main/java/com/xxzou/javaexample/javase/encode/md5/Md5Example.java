package com.xxzou.javaexample.javase.encode.md5;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Md5Example {

    public static void main(String[] args) throws Exception{

        String text = "hello world";
        MessageDigest digest1 = MessageDigest.getInstance("MD5");
        byte[] digestBytes1 = digest1.digest(text.getBytes());
        String code1 = new BigInteger(1, digestBytes1).toString(16);
        System.out.println(code1);

        String text1 = "hello";
        String text2 = "world";
        MessageDigest digest2 = MessageDigest.getInstance("MD5");
        digest2.update(text1.getBytes());
        digest2.update(text2.getBytes());
        byte[] digestByte2 = digest2.digest(text.getBytes());
        String code2 = new BigInteger(1, digestByte2).toString(16);
        System.out.println(code2);
    }

}
