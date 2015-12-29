public class Hund{
  public static void doge_wow(){    //One method to draw it all, but with if statements
    for(int i=1;i<=14;i++){
      for(int j=1;j<=24;j++){
        if(((i==1||i==4)&&j>16)   //Kopf
        ||((i==2||i==3)&&(j==17||j==24))  //Kopf
        ||((i==5||i==6)&&(j>16&&j<21))  //Hals
        ||(i==7&&j==1)    //Schwanz
        ||(i==8&&j<4))    //Schwanz
        System.out.print("+");
        else if((i>6&&i<12)&&(j>3&&j<21)  //Rumpf
        ||(i==2&&j==21)   //Auge
        ||(i>11&&(j==5||j==19))   //Beine
        ||(i==14&&((j>4&&j<8)||(j>18&&j<22))))  //Beine
        System.out.print("*");
        else System.out.print(" ");
      }
      System.out.println("");
    }
  }
  public static void draw_line(int count,String symbol){
    for(int i=0;i<count;i++) System.out.print(symbol);
  }
  public static void draw_padding(int count){
    for(int i=0;i<count;i++) System.out.print(" ");
  }
  public static void draw_eye(){
    System.out.print("*");
  }
  public static void breakLine(){
    System.out.println();
  }
  public static void draw_head(){
    draw_padding(16);
    draw_line(8,"+");
    breakLine();
    draw_padding(16);
    draw_line(1,"+");
    draw_padding(3);
    draw_eye();
    draw_padding(2);
    draw_line(1,"+");
    breakLine();
    draw_padding(16);
    draw_line(1,"+");
    draw_padding(6);
    draw_line(1,"+");
    breakLine();
    draw_padding(16);
    draw_line(8,"+");
    breakLine();
  }
  public static void draw_neck(){
    for(int i=0;i<2;i++){
      draw_padding(16);
      draw_line(4,"+");
      breakLine();
    }
  }
  public static void draw_body(){
    System.out.print("+");
    draw_padding(2);
    draw_line(17,"*");
    breakLine();
    draw_line(3,"+");
    draw_line(17,"*");
    breakLine();
    for(int i=0;i<3;i++){
      draw_padding(3);
      draw_line(17,"*");
      breakLine();
    }
  }
  public static void draw_legs(){
    for(int i=0;i<2;i++){
      draw_padding(4);
      draw_line(1,"*");
      draw_padding(13);
      draw_line(1,"*");
      breakLine();
    }
    draw_padding(4);
    draw_line(3,"*");
    draw_padding(11);
    draw_line(3,"*");
    breakLine();
  }
  public static void draw_dog(){
    draw_head();
    draw_neck();
    draw_body();
    draw_legs();
  }
  public static void main(String[] args){
    //doge_wow();   //so geht's viel einfacher
    draw_dog();
    }
}
