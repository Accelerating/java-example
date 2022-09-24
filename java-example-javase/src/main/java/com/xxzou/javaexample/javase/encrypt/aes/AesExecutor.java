package com.xxzou.javaexample.javase.encrypt.aes;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class AesExecutor {

    /**
     * ECB (Electronic Code Book)
     */
    private static final String ECB = "ECB";
    /**
     * CBC (Cipher Block Chaining)
     */
    private static final String CBC = "CBC";
    /**
     * CFB (Cipher FeedBack)
     */
    private static final String CFB = "CFB";
    /**
     * OFB (Output FeedBack)
     */
    private static final String OFB = "OFB";
    /**
     * CTR (Counter)
     */
    private static final String CTR = "CTR";
    /**
     * GCM (Galois/Counter Mode)
     */
    private static final String GCM = "GCM";



    private SecretKey secretKey;
    private IvParameterSpec iv;
    private String algorithm;

    /**
     * @param algorithm such as AES/CBC/PKCS5Padding
     * @param bitSize bitSize must in 128, 192, or 256 bits
     */
    public AesExecutor(String algorithm, int bitSize){
        this.algorithm = algorithm;
        if(!isECBMode()){
            iv = generateIv();
        }
        secretKey = generateSecretKey(bitSize);
    }

    /**
     *
     * @param algorithm
     * @param key keyLength must in 16,24 or 32
     */
    public AesExecutor(String algorithm, String key){
        this.algorithm = algorithm;
        if(!isECBMode()){
            iv = generateIv();
        }
        secretKey = generateKeyFromKey(key);
    }


    public byte[] encrypt(byte[] rawData) {
        try{
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            return cipher.doFinal(rawData);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public byte[] decrypt(byte[] cipherData){
        try{
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            return cipher.doFinal(cipherData);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String encryptStr(String input){
        byte[] encryptData = encrypt(input.getBytes());
        return Base64.getEncoder().encodeToString(encryptData);
    }

    public String decryptStr(String cipherText){
        byte[] decryptData = decrypt(Base64.getDecoder().decode(cipherText));
        return new String(decryptData);
    }


    /**
     *
     * @param bitSize such as 128, 192, 256
     * @return
     */
    private SecretKey generateSecretKey(int bitSize){
        try{
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(bitSize);
            return keyGenerator.generateKey();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public SecretKey generateKeyFromKey(String key){
        byte[] bytes = key.getBytes();
        return  new SecretKeySpec(bytes, "AES");
    }



    /**
     * IV is not used in ECB mode
     * @return
     */
    public static IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    private boolean isECBMode(){
        String mode = algorithm.split("/")[1];
        return ECB.equals(mode);
    }

}
