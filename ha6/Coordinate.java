public class Coordinate{

   private int x, y;

   public Coordinate(int x, int y){
      this.x=x;
      this.y=y;
   }

   public int getX(){return x;}
   public int getY(){return y;}

   // Gibt zurueck, ob die Koordinaten im Spielfeld liegen.
   public boolean isValid(){
      return x <= 9 && x >= 0 && y <= 9 && y >= 0;
   }

   // Gibt zurueck, ob other die selben Koordinaten hat.
   //I changed the method, so that it would override the method from class Object
   @Override
   public boolean equals(Object obj){
      Coordinate other = (Coordinate) obj;
      return x == other.x && y == other.y;
   }
}