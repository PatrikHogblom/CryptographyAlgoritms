package asymmetricCryptography;


import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Scanner;

/*RSA: Rivest–Shamir–Adleman example*/


public class RSAExample {
    public static void main(String[] args) throws Exception {

        //First step ask the user input
        Scanner input = new Scanner(System.in);
        System.out.println("enter msg to be encrypted: ");
        String userMessage = input.nextLine();

        //second step generate rsa key pair
        KeyPair keyPair = generateRSAKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        //third step: Implement encryption
        byte [] cipherText = encrypt(userMessage, publicKey);

        //forth step: print the chiper text
        System.out.println("Original text is: " + userMessage);
        System.out.println("Cipher text is: " + new String(cipherText));

        //fifth step: decrypt
        String originalMsg = decrypt(cipherText, privateKey);
        System.out.println("Decrypted Text from cipher text is: " + originalMsg);

    }

    public static KeyPair generateRSAKeyPair() throws Exception
    {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        return generator.generateKeyPair();
    }

    public static byte[] encrypt(String msg, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(msg.getBytes());
    }

    public static String decrypt( byte [] cipherText, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte [] decryptedOriginalArray = cipher.doFinal(cipherText);
        return new String(decryptedOriginalArray);
    }



}
