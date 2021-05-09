import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.ArrayList;
import java.math.BigInteger;


public class Part4 {

  public static byte[] getSHA(String input) throws NoSuchAlgorithmException
   {
       // Static getInstance method is called with hashing SHA
       MessageDigest md = MessageDigest.getInstance("SHA-256");
       // digest() method called
       // to calculate message digest of an input
       // and return array of byte
       return md.digest(input.getBytes(StandardCharsets.UTF_8));
   }

   public static String toHexString(byte[] hash)
   {
       // Convert byte array into signum representation
       BigInteger number = new BigInteger(1, hash);

       // Convert message digest into hex value
       StringBuilder hexString = new StringBuilder(number.toString(16));

       return hexString.toString();
   }

    private static String generateRandomString(StringBuilder sb, Random random) {
          String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
          String message = "";
          int length = 18;
          for(int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            message += randomChar;
          }
    return message;
    }
    public static String hash(int a){
      try{
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        String algorithm = "SHA3-256";
        String hash = "";
        boolean match = false;
        ArrayList<String> matches = new ArrayList<>();
        ArrayList<String> message = new ArrayList<>();
        String n = generateRandomString(sb, random);
        hash = toHexString(getSHA(n));
        //hash = hash.substring(0, 1);
        matches.add(hash);
        message.add(n);
        int i = 1;
        while(match == false)
        {
          String m = generateRandomString(sb, random);
          hash = toHexString(getSHA(m));
          //hash = hash.substring(0, 1);
          matches.add(hash);
          message.add(m);
          for(int j = 0; j < matches.size()-1; j++)
          {
            String jS = matches.get(j);
            String iS = matches.get(i);
            if(jS.substring(0, 1).equals(iS.substring(0, 1)))
            {
              match = true;
              String zero = (i + " messages tried" + "\nMessage 1 : " + message.get(i) +"\nHash 1 : " +
                  matches.get(i) +"\nMessage 2 : " + message.get(j) + "\nHash 2 : " + matches.get(j));
              String one = String.valueOf(i);
              if(a == 0)
                return zero;
              return one;
            }
          }
          i++;
        }

      }
        catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown for incorrect algorithm: " + e);
        }
        return null;
    }
    public static void main(String[] args) {
      System.out.println("*** Part A ***");
      System.out.println(hash(0));
      System.out.println("*** Part B ***");
      int total = 0;
      for(int i = 0; i < 20; i++)
      {
        total += Integer.parseInt(hash(1));
      }
      total = total/20;
      System.out.println("Average number of trials : " + total);
    }
}
