import java.util.Scanner;

public class Multtable{

  public static void main(String[] args){
    int n = input();
    for(int i = 1; i <= n; i++){
      for(int j = 1; j <= n; j++) System.out.print(i*j+"\t");
      System.out.println();
    }
  }

  public static int input(){
    Scanner scanner = new Scanner(System.in);
    int x = 0;
    System.out.print("Enter x: ");
    do{
      if(x<0) System.out.print("Wrong input, try again. This time x has to be a number > 0\n>> ");
      try{
          x = scanner.nextInt();
      }
      catch(java.util.InputMismatchException err){
        x = -1;
        scanner.nextLine();
      }
    } while(x<0);
    return x;
  }
}
