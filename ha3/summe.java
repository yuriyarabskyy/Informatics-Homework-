import java.util.Scanner;
public class summe{
  public static int a;
  public static int b;

  public static void read(){
    Scanner input = new Scanner(System.in);
    a = input.nextInt();
    b = input.nextInt();
  }

    public static void swap(){
        int temp = a;
        a = b;
        b = temp;
    }

    public static void add(){
        for(int i = 0; i<Math.abs(a); i++){
          b = b + (int)Math.pow(-1,(a>>31));
        }
      }

  public static void main(String[] args){
    read();
    if(Math.abs(a)>Math.abs(b)) swap();
    add();
    System.out.println(b);
  }
}
