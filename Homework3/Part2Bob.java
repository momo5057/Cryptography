import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import javax.crypto.Cipher;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.security.spec.KeySpec;
//import java.security.spec.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.KeyFactory;

public class Part2Bob
{
  public static void main(String[] args) throws Exception
  {
    File file = new File("ctext2.txt");
    Scanner fileReader = new Scanner(file);
    String m = fileReader.nextLine();
    //byte[] cipherTextArray = m.getBytes();
    byte[] cipherTextArray = Base64.getDecoder().decode(m);
    System.out.println("CipherText : " + m);

    File file2 = new File("Part2Key.txt");
    Scanner fileReader2 = new Scanner(file2);
    String keyS = fileReader2.nextLine();

    byte[] decodedKey = Base64.getDecoder().decode(keyS);
    KeyFactory kf = KeyFactory.getInstance("RSA");
    PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(keyS));
    PrivateKey privKey = kf.generatePrivate(keySpecPKCS8);

    // Decryption
    String decryptedText = decrypt(cipherTextArray, privKey);
    System.out.println("DeCrypted Text : "+decryptedText);
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
