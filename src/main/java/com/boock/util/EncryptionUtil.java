package com.boock.util;

import org.jasypt.util.text.AES256TextEncryptor;


public class EncryptionUtil {
    private static final String SECRET_KEY = "MD_AES256";//秘钥自己设置

    public static String encrypt(String data) {
        AES256TextEncryptor encryptor = new AES256TextEncryptor();
        encryptor.setPassword(SECRET_KEY);
        return encryptor.encrypt(data);
    }
    public static String decrypt(String encryptedData) {
        AES256TextEncryptor encryptor = new AES256TextEncryptor();
        encryptor.setPassword(SECRET_KEY);
        return encryptor.decrypt(encryptedData);
    }
}
