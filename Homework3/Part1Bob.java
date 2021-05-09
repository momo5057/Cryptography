

import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.PBEKeySpec;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
/**
 *
 * @author morganmaness
 */
public class Part1Bob {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        File file = new File("ctext.txt");
        Scanner fileReader = new Scanner(file);
        String m = fileReader.nextLine();
        System.out.println("CipherText : " + m);

        File file2 = new File("Part1Key.txt");
        Scanner fileReader2 = new Scanner(file2);
        String keyS = fileReader2.nextLine();

        byte[] decodedKey = Base64.getDecoder().decode(keyS);
        System.out.println("decodedKey : " + decodedKey);
        SecretKey key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        //System.out.println("secret: " + secret );
        System.out.println("key: " + key);

        byte[] IV = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(IV);

        byte[] cryptText = Base64.getDecoder().decode(m);
        String decryptedText = decrypt(cryptText,key, IV);
        String subString = decryptedText.substring(15, decryptedText.length());
        System.out.println("DeCrypted Text : "+ subString);
        //System.out.println(cipherText);
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

}
