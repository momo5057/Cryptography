import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Part3
{
    public static void main(String[] args) throws Exception
    {
        Scanner scan = new Scanner(System.in);
        String m;
        System.out.println("Type your message: ");
        m = scan.nextLine();
        System.out.println("AES 128");
        System.out.println(AES128(m));
        System.out.println("AES 192");
        System.out.println(AES192(m));
        System.out.println("AES 256");
        System.out.println(AES256(m));
        System.out.println("RSA 1024");
        System.out.println(RSA1024(m));
        System.out.println("RSA 2048");
        System.out.println(RSA2048(m));
        System.out.println("RSA 4096");
        System.out.println(RSA4096(m));
    }
    public static String AES128(String m) throws Exception
    {
      double durationEnc = 0;
      double durationDec = 0;
      for(int i = 0; i < 100; i++){
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);

        // Generate Key
        SecretKey key = keyGenerator.generateKey();

        // Generating IV.
        byte[] IV = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(IV);

        long startTime = System.nanoTime();
        byte[] cipherText = encrypt(m.getBytes(),key, IV);
        long endTime = System.nanoTime();
        durationEnc = durationEnc + (endTime - startTime);
        long startTimeDec = System.nanoTime();
        String decryptedText = decrypt(cipherText,key, IV);
        long endTimeDec = System.nanoTime();
        durationDec = durationDec + (endTimeDec - startTimeDec);
        }
      durationEnc = durationEnc / 100 / 100000;
      durationDec = durationDec / 100 / 100000;
      String ret = ("Encryption : " +  durationEnc + " milliseconds \n" +
      "Decryption : " + durationDec + " milliseconds");
      return ret;
    }

    public static String AES192(String m) throws Exception
    {
      double durationEnc = 0;
      double durationDec = 0;
      for(int i = 0; i < 100; i++){
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(192);

        // Generate Key
        SecretKey key = keyGenerator.generateKey();

        // Generating IV.
        byte[] IV = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(IV);

        long startTime = System.nanoTime();
        byte[] cipherText = encrypt(m.getBytes(),key, IV);
        long endTime = System.nanoTime();
        durationEnc = durationEnc + (endTime - startTime);
        long startTimeDec = System.nanoTime();
        String decryptedText = decrypt(cipherText,key, IV);
        long endTimeDec = System.nanoTime();
        durationDec = durationDec + (endTimeDec - startTimeDec);
        }
      durationEnc = durationEnc / 100 / 100000;
      durationDec = durationDec / 100 / 100000;
      String ret = ("Encryption : " +  durationEnc + " milliseconds \n" +
      "Decryption : " + durationDec + " milliseconds");
      return ret;
    }

    public static String AES256(String m) throws Exception
    {
      double durationEnc = 0;
      double durationDec = 0;
      for(int i = 0; i < 100; i++){
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);

        // Generate Key
        SecretKey key = keyGenerator.generateKey();

        // Generating IV.
        byte[] IV = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(IV);

        long startTime = System.nanoTime();
        byte[] cipherText = encrypt(m.getBytes(),key, IV);
        long endTime = System.nanoTime();
        durationEnc = durationEnc + (endTime - startTime);
        long startTimeDec = System.nanoTime();
        String decryptedText = decrypt(cipherText,key, IV);
        long endTimeDec = System.nanoTime();
        durationDec = durationDec + (endTimeDec - startTimeDec);
        }
      durationEnc = durationEnc / 100 / 100000;
      durationDec = durationDec / 100 / 100000;
      String ret = ("Encryption : " +  durationEnc + " milliseconds \n" +
      "Decryption : " + durationDec + " milliseconds");
      return ret;
    }

    public static String RSA1024(String m) throws Exception
    {
      double durationEnc = 0;
      double durationDec = 0;
      for(int i = 0; i < 100; i++){
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(4096);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        long startTime = System.nanoTime();
        byte[] cipherTextArray = encryptRSA(m, publicKey);
        long endTime = System.nanoTime();
        durationEnc = durationEnc + (endTime - startTime);
        long startTimeDec = System.nanoTime();
        String encryptedText = Base64.getEncoder().encodeToString(cipherTextArray);
        String decryptedText = decryptRSA(cipherTextArray, privateKey);
        long endTimeDec = System.nanoTime();
        durationDec = durationDec + (endTimeDec - startTimeDec);
      }
      durationEnc = durationEnc / 100 / 100000;
      durationDec = durationDec / 100 / 100000;
      String ret = ("Encryption : " +  durationEnc + " milliseconds \n" +
      "Decryption : " + durationDec + " milliseconds");
      return ret;
    }
    public static String RSA2048(String m) throws Exception
    {
      double durationEnc = 0;
      double durationDec = 0;
      for(int i = 0; i < 100; i++){
        // Get an instance of the RSA key generator
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);

        // Generate the KeyPair
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // Get the public and private key
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Encryption
        long startTime = System.nanoTime();
        byte[] cipherTextArray = encryptRSA(m, publicKey);
        long endTime = System.nanoTime();
        durationEnc = durationEnc + (endTime - startTime);
        long startTimeDec = System.nanoTime();
        String encryptedText = Base64.getEncoder().encodeToString(cipherTextArray);
        // Decryption
        String decryptedText = decryptRSA(cipherTextArray, privateKey);
        long endTimeDec = System.nanoTime();
        durationDec = durationDec + (endTimeDec - startTimeDec);
      }
      durationEnc = durationEnc / 100 / 100000;
      durationDec = durationDec / 100 / 100000;
      String ret = ("Encryption : " +  durationEnc + " milliseconds \n" +
      "Decryption : " + durationDec + " milliseconds");
      return ret;
    }
    public static String RSA4096(String m) throws Exception
    {
      double durationEnc = 0;
      double durationDec = 0;
      for(int i = 0; i < 100; i++){
        // Get an instance of the RSA key generator
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(4096);

        // Generate the KeyPair
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // Get the public and private key
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Encryption
        long startTime = System.nanoTime();
        byte[] cipherTextArray = encryptRSA(m, publicKey);
        long endTime = System.nanoTime();
        durationEnc = durationEnc + (endTime - startTime);
        long startTimeDec = System.nanoTime();
        String encryptedText = Base64.getEncoder().encodeToString(cipherTextArray);
        // Decryption
        String decryptedText = decryptRSA(cipherTextArray, privateKey);
        long endTimeDec = System.nanoTime();
        durationDec = durationDec + (endTimeDec - startTimeDec);
      }
      durationEnc = durationEnc / 100 / 100000;
      durationDec = durationDec / 100 / 100000;
      String ret = ("Encryption : " +  durationEnc + " milliseconds \n" +
      "Decryption : " + durationDec + " milliseconds");
      return ret;
    }

    public static byte[] encrypt (byte[] plaintext,SecretKey key,byte[] IV ) throws Exception
    {
      //Get Cipher Instance
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

      //Create SecretKeySpec
      SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");

      //Create IvParameterSpec
      IvParameterSpec ivSpec = new IvParameterSpec(IV);

      //Initialize Cipher for ENCRYPT_MODE
      cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

      //Perform Encryption
      byte[] cipherText = cipher.doFinal(plaintext);

      return cipherText;
    }

    public static String decrypt (byte[] cipherText, SecretKey key,byte[] IV) throws Exception
    {
      //Get Cipher Instance
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

      //Create SecretKeySpec
      SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");

      //Create IvParameterSpec
      IvParameterSpec ivSpec = new IvParameterSpec(IV);

      //Initialize Cipher for DECRYPT_MODE
      cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

      //Perform Decryption
      byte[] decryptedText = cipher.doFinal(cipherText);

      return new String(decryptedText);
    }
    public static byte[] encryptRSA (String plainText,PublicKey publicKey ) throws Exception
    {
        //Get Cipher Instance RSA With ECB Mode and OAEPWITHSHA-512ANDMGF1PADDING Padding
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-512ANDMGF1PADDING");

        //Initialize Cipher for ENCRYPT_MODE
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        //Perform Encryption
        byte[] cipherText = cipher.doFinal(plainText.getBytes()) ;

        return cipherText;
    }

    public static String decryptRSA (byte[] cipherTextArray, PrivateKey privateKey) throws Exception
    {
        //Get Cipher Instance RSA With ECB Mode and OAEPWITHSHA-512ANDMGF1PADDING Padding
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-512ANDMGF1PADDING");

        //Initialize Cipher for DECRYPT_MODE
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        //Perform Decryption
        byte[] decryptedTextArray = cipher.doFinal(cipherTextArray);

        return new String(decryptedTextArray);
    }
}
