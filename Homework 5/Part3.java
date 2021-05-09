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
import javax.crypto.Mac;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.KeyFactory;

public class Part3
{
    public static void main(String[] args) throws Exception
    {
        Scanner scan = new Scanner(System.in);
        String m;
        System.out.println("Type your message: ");
        m = scan.nextLine();
        System.out.println("HMAC");
        System.out.println(HMAC(m));
        System.out.println("RSA");
        System.out.println(RSAENC(m));
        //System.out.println("AES 256");
    }
    public static String HMAC(String m) throws Exception
    {
      double duration = 0;
      for(int i = 0; i < 100; i++){
        long startTime = System.nanoTime();
        String secret = "hmacshasecretkey";
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        String hash = Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(m.getBytes()));
        long endTime = System.nanoTime();
        duration = duration + (endTime - startTime);
        }
        duration = duration/100;
      String ret = ("HMAC Generation: " +  duration);
      return ret;
    }

    public static String RSAENC(String m) throws Exception
    {
      double durationEnc = 0;
      PrivateKey privateKey = null;
      String encryptedText = "";
      for(int i = 0; i < 100; i++){
        long startTimeEnc = System.nanoTime();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        // Generate the KeyPair
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // Get the public and private key
        PublicKey publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();
        // Encryption
        byte[] cipherTextArray = encrypt(m, publicKey);
        encryptedText = Base64.getEncoder().encodeToString(cipherTextArray);
        long endTimeEnc = System.nanoTime();
        durationEnc = durationEnc + (endTimeEnc - startTimeEnc);
      }
        double duration = 0;
        for(int i = 0; i < 100; i++){
          long startTime = System.nanoTime();
          byte[] cipherTextArray = Base64.getDecoder().decode(encryptedText);
          String decryptedText = decrypt(cipherTextArray, privateKey);
          long endTime = System.nanoTime();
          duration = duration + (endTime - startTime);
        }
        durationEnc = durationEnc/100;
        duration = duration/100;
      String ret = ("RSA Encryption: " +  durationEnc + "\nRSA Decryption: " + duration);
      return ret;
    }


    public static byte[] encrypt (String plainText,PublicKey publicKey ) throws Exception
    {
        //Get Cipher Instance RSA With ECB Mode and OAEPWITHSHA-512ANDMGF1PADDING Padding
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-512ANDMGF1PADDING");

        //Initialize Cipher for ENCRYPT_MODE
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        //Perform Encryption
        byte[] cipherText = cipher.doFinal(plainText.getBytes()) ;

        return cipherText;
    }
    public static String decrypt (byte[] cipherTextArray, PrivateKey privateKey) throws Exception
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
