package com.omegaeducacional.caixa.utils;

// !! DISCLAIMER
// This code is not my!! Link for the original author is https://stackoverflow.com/questions/27667034/how-to-encrypt-password-using-spring-hibernate-in-java

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

public class EncryptionUtils {

    public static String encryptPassphrase(String passphrase) {
        try {
            Random RNG = new Random();
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] salt = new byte[18];
            RNG.nextBytes(salt);

            md.update(salt);
            md.update(passphrase.getBytes(StandardCharsets.UTF_8));

            byte[] passEnc = md.digest();
            return String.format("%s$%s",
                    new String(Base64.getEncoder().encode(salt)),
                    new String(Base64.getEncoder().encode(passEnc)));
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);     // this should never happen though
        }
    }

    public static boolean verifyPassphrase(String passphrase, String encryptedPassphrase) {
        String[] splited = encryptedPassphrase.split("\\$");
        byte[] salt = Base64.getDecoder().decode(splited[0].getBytes());
        byte[] passEncDb = Base64.getDecoder().decode(splited[1].getBytes());

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(salt);
            md.update(passphrase.getBytes(StandardCharsets.UTF_8));
            byte[] passEnc = md.digest();

            return Arrays.equals(passEncDb, passEnc);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);     // this should never happen though
        }
    }
}
