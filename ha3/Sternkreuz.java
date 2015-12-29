import java.util.Scanner;

public class Sternkreuz{
  public static int kantenlaenge;
  public static String sternkreuz;

  public static void main(String[] args){
    while(kantenlaenge!=42&&kantenlaenge!=2){
      input();
      draw();
      output();
    }
  }

  public static void input(){
    Scanner scanner = new Scanner(System.in);
    kantenlaenge = 2;
    System.out.print("Enter Kantenlaenge: ");
    do{
      if(kantenlaenge<2) System.out.print("Wrong input, try again. This time Kantenlaenge has to be a number > 2\n>> ");
      try{
          kantenlaenge = scanner.nextInt();
      }
      catch(java.util.InputMismatchException err){
        kantenlaenge = -1;
        scanner.nextLine();
      }
    } while(kantenlaenge<2);
  }

  public static void draw(){
    sternkreuz = "";
    for(int i = 1; i<=kantenlaenge; i++){
      for(int j = 1; j<=kantenlaenge; j++)
        if(i==j||(kantenlaenge-i+1)==j
        ||i==1||i==kantenlaenge||j==1||j==kantenlaenge) sternkreuz += "*";
        else sternkreuz += " ";
      sternkreuz += "\n";
    }
  }

  public static void output(){
    System.out.println(sternkreuz);
  }
}
