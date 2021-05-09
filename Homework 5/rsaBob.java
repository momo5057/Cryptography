import javax.crypto.Mac;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.KeyFactory;
import javax.crypto.Cipher;
import java.security.spec.RSAPrivateKeySpec;

public class rsaBob {
  public static void main(String[] args) {
    try {
      File file = new File("sigtest.txt");
      Scanner fileReader = new Scanner(file);
      String m = fileReader.nextLine();
      String hash = fileReader.nextLine();
      byte[] cipherTextArray = Base64.getDecoder().decode(hash);

      File file2 = new File("rsaKey.txt");
      Scanner fileReader2 = new Scanner(file2);
      String keyS = fileReader2.nextLine();

      byte[] decodedKey = Base64.getDecoder().decode(keyS);
      KeyFactory kf = KeyFactory.getInstance("RSA");
      PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(keyS));
      PrivateKey privKey = kf.generatePrivate(keySpecPKCS8);

      String decryptedText = decrypt(cipherTextArray, privKey);

     if(decryptedText.equals(m)){
        System.out.println("Verification Successful");
     }
     else{
       System.out.println("Verification Unsuccessful");
     }

    }
    catch (Exception e){
     System.out.println("Error");
    }

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
