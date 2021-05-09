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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;
import java.io.FileWriter;

public class Part1Alice {

    public static void main(String[] args) throws Exception{
        Scanner scan = new Scanner(System.in);
        String m;
        System.out.println("Type your message: ");
        m = scan.nextLine();
        m = "                " + m;

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);

        // Generate Key
        SecretKey key = keyGenerator.generateKey();

        // Generating IV.
        byte[] IV = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(IV);

        //System.out.println("Key : " + key);

        String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
        //System.out.println("encodedKey : " + encodedKey);

        byte[] cipherText = encrypt(m.getBytes(),key, IV);
        String cipherEncoded = Base64.getEncoder().encodeToString(cipherText);

        //System.out.println("Encrypted Text as string: "+ Base64.getEncoder().encodeToString(cipherText) );

        FileWriter myWriter = new FileWriter("ctext.txt");
        myWriter.write(Base64.getEncoder().encodeToString(cipherText));
        myWriter.close();
        FileWriter myWriter2 = new FileWriter("Part1Key.txt");
        myWriter2.write(encodedKey);
        myWriter2.close();

        System.out.println("CipherText : " + Base64.getEncoder().encodeToString(cipherText));


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

}
