package com.xxzou.javaexample.javase.encrypt.aes;

public class AesExample {
    public static void main(String[] args) {

        String text = "hello world";

        AesExecutor executor1 = new AesExecutor("AES/CBC/PKCS5Padding", 128);
        String cipherText1 = executor1.encryptStr(text);
        String decryptText1 = executor1.decryptStr(cipherText1);
        System.out.println(decryptText1);


        AesExecutor executor2 = new AesExecutor("AES/CBC/PKCS5Padding", "12");
        String cipherText2 = executor2.encryptStr(text);
        String decryptText2 = executor2.decryptStr(cipherText2);
        System.out.println(decryptText2);


    }


}
