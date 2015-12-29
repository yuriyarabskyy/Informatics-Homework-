public class house{
//visio professional
  public static void draw_triangle(){
    for(int i=0;i<7;i++){
      for(int j=6-i;j>0;j--){
        System.out.print(" ");
      }
      for(int z=1;z<=1+i*2;z++){
          System.out.print("*");
      }
      System.out.println();
  }
  }

  public static void draw_box(){
    for(int i=0;i<14;i++) System.out.print("+");
    System.out.println();
    for(int i=0;i<4;i++){
      for(int j=0;j<2;j++) System.out.print("+");
      for(int j=0;j<10;j++) System.out.print(" ");
      for(int j=0;j<2;j++) System.out.print("+");
      System.out.println();
    }
    for(int i=0;i<14;i++) System.out.print("+");
    System.out.println();
  }

  public static void main(String[] args){
    draw_triangle();
    draw_box();
  }
}
