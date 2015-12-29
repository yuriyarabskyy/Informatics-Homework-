import java.util.Scanner;

public class BartSimpson{
  public static void main(String[] args){
    Scanner input = new Scanner(System.in);
    String phrase = "Ich darf während der Vorlesung nicht Computer spielen!";
    System.out.print("How many time: ");
    int n = input.nextInt();
    for(int i=1;i<=n;i++){
      System.out.println(i+". "+phrase);
    }
  }
}
