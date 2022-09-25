package com.xxzou.javaexample.javase.encrypt.rsa;

import javax.crypto.Cipher;
import java.io.*;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RsaExecutor {

    private PublicKey publicKey;
    private PrivateKey privateKey;

    private RsaExecutor(){}

    public RsaExecutor(int keySize) throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(keySize);
        KeyPair pair = generator.generateKeyPair();
        publicKey = pair.getPublic();
        privateKey = pair.getPrivate();
    }

    public RsaExecutor(String publicKeyPath, String privateKeyPath, boolean base64Decode) throws Exception {
        byte[] publicKeyBytes = Files.readAllBytes(new File(publicKeyPath).toPath());
        byte[] privateKeyBytes = Files.readAllBytes(new File(privateKeyPath).toPath());

        if(base64Decode){
            publicKeyBytes = Base64.getDecoder().decode(publicKeyBytes);
            privateKeyBytes = Base64.getDecoder().decode(privateKeyBytes);
        }

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        publicKey = keyFactory.generatePublic(publicKeySpec);

        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        privateKey = keyFactory.generatePrivate(privateKeySpec);
    }

    public byte[] encrypt(byte[] input) throws Exception{
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return encryptCipher.doFinal(input);
    }

    public String encryptAndEncodeBase64(byte[] input) throws Exception{
        byte[] encryptData = encrypt(input);
        return Base64.getEncoder().encodeToString(encryptData);
    }

    public byte[] decrypt(byte[] cipherData) throws Exception{
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        return decryptCipher.doFinal(cipherData);
    }

    public byte[] decodeBase64AndDecrypt(String cipherText) throws Exception{
        byte[] encryptData = Base64.getDecoder().decode(cipherText);
        return decrypt(encryptData);
    }

    public void storeKeyPairInFile(boolean base64Encode){
        long now = System.currentTimeMillis();
        try(
            FileOutputStream pkOut = new FileOutputStream("public_" + now + ".key");
            FileOutputStream skOut = new FileOutputStream("private_" + now + ".key");
        ){
            byte[] pkBytes = publicKey.getEncoded();
            byte[] skBytes = privateKey.getEncoded();
            if(base64Encode){
                pkBytes = Base64.getEncoder().encode(pkBytes);
                skBytes = Base64.getEncoder().encode(skBytes);
            }

            pkOut.write(pkBytes);
            skOut.write(skBytes);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
