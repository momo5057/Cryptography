import javax.crypto.Mac;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

public class shaAlice {
  public static void main(String[] args) {
    try {
      Scanner scan = new Scanner(System.in);
      String m;
      System.out.println("Type your message: ");
      m = scan.nextLine();
     String secret = "hmacshasecretkey";

     Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
     SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
     sha256_HMAC.init(secret_key);

     String hash = Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(m.getBytes()));
     System.out.println("Hash: " + hash);

     FileWriter myWriter = new FileWriter("mactext.txt");
     myWriter.write(m);
     myWriter.write("\n" + hash);
     myWriter.close();
    }
    catch (Exception e){
     System.out.println("Error");
    }

  }
}
