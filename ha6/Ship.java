
import java.util.LinkedList;

public class Ship{

   //the coordinates of all field of the ship
   private final LinkedList<Coordinate> coordinates;

   //isHit tells if the selected field was hit
   private LinkedList<Boolean> isHit = new LinkedList<>();

   public Ship(LinkedList<Coordinate> coordinates) {

      this.coordinates = coordinates;

      for(Coordinate x : coordinates) {

         isHit.add(coordinates.indexOf(x), false);

      }
   }

   public boolean contains(Coordinate c){

      if(!c.isValid()) return false;

      return coordinates.contains(c);
   }

   public boolean isHit(Coordinate c){

      if(!contains(c)) return false;

      return isHit.get(coordinates.indexOf(c));

   }

   public void hit(Coordinate c) {

      if(contains(c)) {

         isHit.set(coordinates.indexOf(c), true);

      }

   }

   public boolean isSunk(){

      //if even one of the field is not hit, then return false;
      for(int i = 0; i < coordinates.size(); i++) {

         if(!isHit.get(i)) return false;

      }

      return true;
   }

}