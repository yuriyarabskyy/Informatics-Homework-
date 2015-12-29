import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Vokalersetzung{

  public static void main(String[] args){
    String text = getText("text.txt");
    String vowel = getVowel();
    String vowels = "[aouei]";
    text = text.replaceAll(vowels,vowel.toLowerCase());
    text = text.replaceAll(vowels.toUpperCase(),vowel.toUpperCase());
    System.out.println(text);
  }

  public static String getText(String file)
  {
    try{
    Scanner scanner = new Scanner(new File(file));
    StringBuffer string = new StringBuffer();
    while(scanner.hasNextLine()){
      string.append(scanner.nextLine()+"\n");
    }
    return string.toString();
  }
  catch(FileNotFoundException e){
    System.out.print("Sorry, file doesn't exist");
    return null;
  }
  }

  public static String getVowel()
  {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter your chosen vowel: ");
    return input.next();
  }
}
