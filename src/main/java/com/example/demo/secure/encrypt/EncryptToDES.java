package com.example.demo.secure.encrypt;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptToDES {

	
    public static String encriptGeneratedWordToDES(String password) {
        String key = "12345678";
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        byte[] encryptWordBytes;
        String encryptWordString = "";

        SecretKey secretKey = new SecretKeySpec(keyBytes, "DES");

        try {
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] mensagemBytes = password.getBytes(StandardCharsets.UTF_8);
            encryptWordBytes = cipher.doFinal(mensagemBytes);
            encryptWordString = Base64.getEncoder().encodeToString(encryptWordBytes);

        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
                | BadPaddingException e) {
            e.printStackTrace();
        }

        return encryptWordString;
    }
}
