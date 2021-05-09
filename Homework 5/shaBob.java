import javax.crypto.Mac;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

public class shaBob {
  public static void main(String[] args) {
    try {
      File file = new File("mactext.txt");
      Scanner fileReader = new Scanner(file);
      String m = fileReader.nextLine();
      String hash = fileReader.nextLine();
      String secret = "hmacshasecretkey";

     Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
     SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
     sha256_HMAC.init(secret_key);

     String verification = Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(m.getBytes()));
     System.out.println("Hash:" + verification);
     
     if(verification.equals(hash)){
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
}
