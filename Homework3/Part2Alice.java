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

public class Part2Alice
{
  public static void main(String[] args) throws Exception
  {
    Scanner scan = new Scanner(System.in);
    String m;
    System.out.println("Type your message: ");
    m = scan.nextLine();

    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    keyPairGenerator.initialize(2048);

    // Generate the KeyPair
    KeyPair keyPair = keyPairGenerator.generateKeyPair();
    // Get the public and private key
    PublicKey publicKey = keyPair.getPublic();
    PrivateKey privateKey = keyPair.getPrivate();
    //System.out.println("Private Key: " + privateKey);

    // Encryption
    byte[] cipherTextArray = encrypt(m, publicKey);
    String encryptedText = Base64.getEncoder().encodeToString(cipherTextArray);
    //System.out.println("Encrypted Text : "+encryptedText);

    //Save private key to file
    String encodedKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());
    FileWriter myWriter2 = new FileWriter("Part2Key.txt");
    myWriter2.write(encodedKey);
    myWriter2.close();
    //Save cipherText to file
    FileWriter myWriter = new FileWriter("ctext2.txt");
    myWriter.write(encryptedText);
    myWriter.close();
    System.out.println("CipherText : " + encryptedText);
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
}
