import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
/**
 *
 * @author morganmaness
 */
public class Assignment1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Scanner scanLines = new Scanner(System.in);
        int userChoice;
        String plainText;
        String cryptText;
        int key;
        char letter;
        String fileName;
        System.out.println("For encryption, type 1");
        System.out.println("For decryption, type 2");
        System.out.println("For brute force attack, type 3");
        System.out.println("To exit, type 4");
        userChoice = scan.nextInt();
        while(userChoice != 4)
        {
          if(userChoice == 1)
          {
            cryptText = "";
            System.out.println("Plaintext message: ");
            plainText = scanLines.nextLine();
            System.out.println("Key: ");
            key = scan.nextInt();
            plainText = plainText.toLowerCase();
            for(int i =0; i < plainText.length(); i++)
            {
              letter = plainText.charAt(i);
              if(letter >= 'a' && letter <= 'z')
              {
                letter = (char)(letter + key);
                if(letter > 'z')
                  letter = (char)(letter - 'z' + 'a' - 1);
              }
              cryptText += letter;
            }
            System.out.println(cryptText);
          }
          else if(userChoice == 2)
          {
            plainText = "";
            System.out.println("Cryptext message: ");
            cryptText = scanLines.nextLine();
            System.out.println("Key: ");
            key = scan.nextInt();
            cryptText = cryptText.toLowerCase();
            for(int i =0; i < cryptText.length(); i++)
            {
              letter = cryptText.charAt(i);
              if(letter >= 'a' && letter <= 'z')
              {
                letter = (char)(letter - key);
                if(letter < 'a')
                  letter = (char)(letter + 'z' - 'a' + 1);
              }
              plainText += letter;
            }
            System.out.println(plainText);
          }
          else
          {
            plainText = "";
            boolean found = false;
            System.out.println("Crytext message: ");
            cryptText = scanLines.nextLine();
            System.out.println("Text file: ");
            fileName = scanLines.nextLine();
            //Figure out how to read each line of the file
            cryptText = cryptText.toLowerCase();
            int testCount = 0;
            File file = new File(fileName);


            while(found == false)
            {
              testCount++;
              plainText = "";
              boolean saveForLater = false;
              String newCrypt = "";
              for(int i =0; i < cryptText.length(); i++)
              {
                letter = cryptText.charAt(i);
                if(saveForLater == false)
                {
                  newCrypt += letter;
                  if(letter < 'a' || letter > 'z')
                    saveForLater = true;
                }
              }
              for(int i =0; i < newCrypt.length(); i++)
              {
                letter = newCrypt.charAt(i);
                if(letter >= 'a' && letter <= 'z')
                {
                  letter = (char)(letter - testCount);
                  if(letter < 'a')
                    letter = (char)(letter + 'z' - 'a' + 1);
                }
                plainText += letter;
              }
              try {
                File myObj = new File(fileName);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                  String data = myReader.nextLine();
                  data = data.toLowerCase();
                  if(data.contains(plainText))
                  {
                    found = true;
                  }
                }
                myReader.close();
              } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              }
              if(testCount == 26)
              {
                found = true;
              }
            }
            if(testCount == 26)
            {
              System.out.println("No match found");
            }
            else
            {
              plainText = "";
              for(int i =0; i < cryptText.length(); i++)
              {
                letter = cryptText.charAt(i);
                if(letter >= 'a' && letter <= 'z')
                {
                  letter = (char)(letter - testCount);
                  if(letter < 'a')
                    letter = (char)(letter + 'z' - 'a' + 1);
                }
                plainText += letter;
              }
              System.out.println("Plaintext: " + plainText);
              System.out.println("Key: " + testCount);
            }
          }
          System.out.println("For encryption, type 1");
          System.out.println("For decryption, type 2");
          System.out.println("For brute force attack, type 3");
          System.out.println("To exit, type 4");
          userChoice = scan.nextInt();
      }
    }

}
