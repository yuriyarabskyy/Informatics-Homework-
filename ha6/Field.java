import java.util.LinkedList;
import java.util.Scanner;

public class Field {

   // Liste mit allen Schiffen
   private LinkedList<Ship> shipList;

   // Liste mit allen geratenen Feldern
   private LinkedList<Coordinate> guess;

   private static final int numberOfShips = 7;

   private static Scanner input = new Scanner(System.in);



   private void init(){

      shipList = new LinkedList<>();

      guess = new LinkedList<>();

      for(int i = 0; i < numberOfShips; i++) {

         LinkedList<Coordinate> coordList;
         Coordinate coordinate;
         int ifVertical;
         boolean okay;

         do {

            okay = true;
            //should the ship be placed vertically of horizontally
            ifVertical = myRandom(0, 2);

            coordList = new LinkedList<>();

            if (ifVertical == 0) coordinate = new Coordinate(myRandom(0, 10-i), myRandom(0, 10));
            else coordinate = new Coordinate(myRandom(0, 10), myRandom(0, 10-i));

            for(int j = 0; j <= i; j++) {

               if(ifVertical == 0) coordList.add(new Coordinate(coordinate.getX()+j,coordinate.getY()));
               else coordList.add(new Coordinate(coordinate.getX(),coordinate.getY()+j));

            }
            //check if the ship fits
            for(Ship ship : shipList)
               for(Coordinate coordinate1 : coordList)
                  if(ship.contains(new Coordinate(coordinate1.getX()+1,coordinate1.getY()))
                       ||ship.contains(new Coordinate(coordinate1.getX()-1,coordinate1.getY()))
                       ||ship.contains(new Coordinate(coordinate1.getX(),coordinate1.getY()+1))
                       ||ship.contains(new Coordinate(coordinate1.getX(),coordinate1.getY()-1))
                       ||ship.contains(new Coordinate(coordinate1.getX()+1,coordinate1.getY()+1))
                       ||ship.contains(new Coordinate(coordinate1.getX()+1,coordinate1.getY()-1))
                       ||ship.contains(new Coordinate(coordinate1.getX()-1,coordinate1.getY()+1))
                       ||ship.contains(new Coordinate(coordinate1.getX()-1,coordinate1.getY()-1))
                       ||ship.contains(new Coordinate(coordinate1.getX(),coordinate1.getY())))
                  okay = false;

         } while(!okay);

         shipList.add(new Ship(coordList));

      }

   }

   //are all the ships destroyed
   private boolean isEnd() {

      for(Ship ship : shipList) {

         if(!ship.isSunk()) return false;

      }

      return true;

   }

   private void guessField() {

      String fieldX, fieldY;
      System.out.println("If you want to see the field, enter 'cheat'");
      System.out.print("Enter X and Y: ");

      do {

         fieldX = input.next();

         if(fieldX.equals("cheat")) {
            printFields(true);
            return;
         }

         fieldY = input.next();

      } while(!isValid(fieldX, fieldY));

      Coordinate guessField = new Coordinate(Integer.parseInt(fieldX),Integer.parseInt(fieldY));

      guess.add(guessField);

      for(Ship ship : shipList) ship.hit(guessField);

      printFields(false);

   }

   //tests if the input of the field is valid
   private static boolean isValid(String x, String y) {

      if(!x.matches("[0-9]")||!y.matches("[0-9]")){
         System.out.println("You have to enter a number, try again..");
         return false;
      }

      return true;
   }

   public void play(){

      init();

      do{

         guessField();

      } while(!isEnd());

   }


   // Aufruf mit true zeigt alle Schiffe an,
   // Aufruf mit false nur die schon geratenen Felder.
   public void printFields(boolean show){
      System.out.println("\n  --------------------");
      for (int up = 9; up >= 0; up--) {
         for (int right = 1; right <= 24; right++) {
            int x=(right-3)/2;
            Coordinate current = new Coordinate(x,up);
            char next = ' ';
            if (right == 1) next = (""+up).charAt(0); else
            if (right == 2 || right == 22) next = '|';
            for (int shipNr = 0; shipNr < shipList.size(); shipNr++) {
               if (right%2==0) break;
               Ship ship = shipList.get(shipNr);
               if (show && ship.contains(current)) {
                  next='X'; break;
               }
               for (int i = 0; i < guess.size(); i++) {
                  if (!show && guess.get(i).equals(current)) {
                     next='W'; // Wasser
                  }
               }
               if (!show && ship.isHit(current)) {
                  next='T'; // Treffer
                  if (ship.isSunk()) next='V'; // Versenkt
                  break;
               }
            }
            System.out.print(next);
         }
         System.out.println();
      }
      System.out.println("  - - - - - - - - - -\n  0 1 2 3 4 5 6 7 8 9\n");
   }

   private static int myRandom(int low, int high) {
      return (int) (Math.random() * (high - low) + low);
   }

}
