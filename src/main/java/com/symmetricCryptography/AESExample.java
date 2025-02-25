package com.symmetricCryptography;

import javax.crypto.*;
import java.security.*;
import java.util.Base64;
import java.util.Scanner;

public class AESExample {
    public static void main(String[] args) throws NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchPaddingException {
        //First step ask the user input
        Scanner input = new Scanner(System.in);
        System.out.println("enter message to be encrypted: ");
        String plaintext = input.nextLine();

        // Generate a secret key for AES encryption
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        SecretKey secretKey = keyGenerator.generateKey();

        // Create an AES cipher instance
        Cipher cipher = Cipher.getInstance("AES");
        // Initialize the cipher for encryption using the secret key
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        //Start encryption
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());

        //Encode this encryption data in base64 format for more readability
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("Encrypted text using AES: " + encryptedText);

        // Initialize the cipher for decryption using the same secret key
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        //Decrypt  the encoded text
        byte [] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));

        // Convert the decrypted bytes back to plaintext
        String decryptedText = new String(decryptedBytes);
        System.out.println("Decrypted Text (AES): " + decryptedText);

    }
}
