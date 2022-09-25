package com.xxzou.javaexample.javase.encrypt.rsa;

public class RsaExample {

    public static void main(String[] args) throws Exception {

        String secretMessage = "this is secret message";

        RsaExecutor rsaExecutor = new RsaExecutor(2048);
        String cipherText = rsaExecutor.encryptAndEncodeBase64(secretMessage.getBytes());
        System.out.println(cipherText);

        byte[] rawBytes = rsaExecutor.decodeBase64AndDecrypt(cipherText);
        System.out.println(new String(rawBytes));

        //save file
        rsaExecutor.storeKeyPairInFile(true);


        RsaExecutor rsaExecutor2 = new RsaExecutor("public_1664099362322.key", "private_1664099362322.key", true);
        String cipherText2 = rsaExecutor2.encryptAndEncodeBase64(secretMessage.getBytes());
        System.out.println(cipherText2);
        byte[] rawBytes2 = rsaExecutor2.decodeBase64AndDecrypt(cipherText2);
        System.out.println(new String(rawBytes2));
    }

}
