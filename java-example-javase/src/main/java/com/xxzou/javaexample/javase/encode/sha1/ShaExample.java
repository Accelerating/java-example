package com.xxzou.javaexample.javase.encode.sha1;

import java.math.BigInteger;
import java.security.MessageDigest;

public class ShaExample {

    public static void main(String[] args) throws Exception{
        String text = "hello world";
        MessageDigest mdSha1 = MessageDigest.getInstance("SHA-1");
        byte[] mdSha1Bytes = mdSha1.digest(text.getBytes());
        String code1 = new BigInteger(1, mdSha1Bytes).toString(16);
        System.out.println(code1);

        MessageDigest mdSha256 = MessageDigest.getInstance("SHA-256");
        byte[] mdSha256Bytes = mdSha256.digest(text.getBytes());
        String code256 = new BigInteger(1, mdSha256Bytes).toString(16);
        System.out.println(code256);

        MessageDigest mdSha512 = MessageDigest.getInstance("SHA-512");
        byte[] mdSha512Bytes = mdSha512.digest(text.getBytes());
        String code512 = new BigInteger(1, mdSha512Bytes).toString(16);
        System.out.println(code512);

    }
}
