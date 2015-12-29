import java.util.Random;
import java.util.Scanner;

public class Uno {
  static Random random = new Random();
  static Scanner scanner = new Scanner(System.in);

  static final int startStapelSize = 7;

  static final char PLUS_2 = 'a';
  static final char AUSSETZEN = 'b';
  static final char PLUS_4 = 'c';
  static final char FARBWAHL = 'd';

  static final char BLAU = '0';
  static final char GRUEN = '1';
  static final char GELB = '2';
  static final char ROT = '3';
  static final char SCHWARZ = '4';
  static final char FEHLERHAFTE_FARBE = 'x';

  static final int ID_INDEX = 0;
  static final int FARBE_INDEX = 1;
  static final int KARTENLAENGE = 2;

/*
* According to my calculations the probability
* of each card is equal in my method
*/
  static String zieheKarte () {
    int r = random.nextInt(12 * 4 + 2);

    if(r==48) return "c4";
    if(r==49) return "d4";

  //we are left with 0..47, so c1=0..12 and c2=0..3
    String c1, c2;
    c1 = Integer.toString(r%12);
    c2 = Integer.toString(r/12);

    switch(c1){
      case "10": c1="a"; break;
      case "11": c1="b"; break;
      case "12": c1="c"; break;
      default: break;
    }

    return c1+c2;
  }

  static String farbeLesbar (char farbe) {
    switch (farbe){
      case BLAU: return "blue";
      case GRUEN: return "green";
      case GELB: return "yellow";
      case ROT: return "red";
      case SCHWARZ: return "black";
      default: return Character.toString(FEHLERHAFTE_FARBE);
    }
  }

//Makes a card and its colour readable
  static String karte(String card) {
    return karteLesbar(card.charAt(ID_INDEX)) + " " + farbeLesbar(card.charAt(FARBE_INDEX));
  }

//only the cards itself
  static String karteLesbar (char karte){
    switch(karte){
      case PLUS_2: return "plus_two";
      case AUSSETZEN: return "pass_turn";
      case PLUS_4: return "plus_four";
      case FARBWAHL: return "colour_choose";
      default: return Character.toString(karte);
    }
  }

//the whole deck readable
  static String kartenstapelLesbar (String kartenstapel) {

    String output = "";

    kartenstapel = kartenstapel.trim();

    int k = 0;

    for(String card: kartenstapel.split(" ")){

      k++;
      output += k + ":\t" + karteLesbar(card.charAt(ID_INDEX)) + " ";
      output += farbeLesbar(card.charAt(FARBE_INDEX)) + "\n";

    }

    return output;
  }

  static String startStapel () {
    String cards = "";

    for(int i = 0; i < startStapelSize; i++)
      cards += zieheKarte() + " ";

    return cards;
  }

//number of cards in the deck
  static int count(String cards) {
    if(cards.equals("")) return 0;
    return cards.trim().split(" ").length;
  }

//add a card to the deck
  static String add(String cards, String card){ return cards + card + " "; }

//check if you can put the card on the top
  static boolean check(String card, String top) {

    if(card.charAt(ID_INDEX) == top.charAt(ID_INDEX) || card.charAt(FARBE_INDEX) == top.charAt(FARBE_INDEX)) return true;
    if(top.charAt(ID_INDEX) == FARBWAHL && card.charAt(ID_INDEX) == PLUS_4) return true;
    if(top.charAt(ID_INDEX) == PLUS_4 && card.charAt(ID_INDEX) == PLUS_2) return true;
    if(card.charAt(ID_INDEX) == PLUS_4 && top.charAt(ID_INDEX) == PLUS_2) return true;
    if(card.charAt(ID_INDEX) == FARBWAHL && !(top.charAt(ID_INDEX) == PLUS_4 || top.charAt(ID_INDEX) == PLUS_2)) return true;

    return false;
  }

//remove a card from the deck under the conditions, that 'top' is on the top
  static String remove(String cards, String what, String top) {

    String output = "";

    int cardNumber;

    String chosenCard = "";

    if(what.charAt(0) == 'u') {

      System.out.println("Uno!");

      cardNumber = Character.getNumericValue(what.charAt(1));
    }
    else{ cardNumber = Integer.parseInt(what); }

    for(String card: cards.trim().split(" ")) {

      cardNumber--;

      if(cardNumber == 0) {

        chosenCard = card;

        continue;
      }

      output += card + " ";

    }

    if(!check(chosenCard, top)) {

      System.out.println("You have chosen the wrong card!\nTake a new card from the stack..");

      cards += zieheKarte() + " ";

      return cards;

    }

    return output;
  }

//method used for user input of cards and also colours
  static String read(int limit, boolean allowZero){
    String input;
    boolean flag = false;

    if(!allowZero)
      System.out.print("Choose your card:  ");
    else
      System.out.print("Choose your colour(0/Blue,1/Green,2/Yellow,3/Red:  ");

    do{
      if(flag) System.out.println("Invalid input, try again!");
      input = scanner.next();
      flag = true;
    } while(!input.matches("(u?[1-9]\\d*)") || evaluate(input) + 1 > limit || (allowZero && input.equals("(0)")));

    return input;
  }

//check if for it's possible for the player to make a move
  static boolean possible(String cards, String top) {

    for(String card: cards.trim().split(" ")) {
      if(check(card, top)) return true;
    }

    return false;
  }

//used when PLUS_2 or PLUS_4 is on the top to check if you can counter that card
  static boolean specialCheck(String cards) {
    for (String card: cards.trim().split(" ")) {
      if(card.charAt(ID_INDEX) == PLUS_2 || card.charAt(ID_INDEX) == PLUS_4) return true;
    }
    return false;
  }

//returns the number of the chosen card
//important because player can type in for instance "u3"
  static int evaluate(String card) {
    if(card.charAt(0) == 'u') return Integer.parseInt(card.substring(1)) - 1;
    else return Integer.parseInt(card) - 1;
  }

//check if you're allowed to put a PLUS_4 on the top
  static boolean isAllowedBlack(String cards, String top) {
//if the top card is not special and the player has a card besides PLUS_4 to counter it
    for(String card: cards.trim().split(" ")) {
      if((card.charAt(ID_INDEX) == top.charAt(ID_INDEX) || card.charAt(FARBE_INDEX) == top.charAt(FARBE_INDEX))
              && !(card.charAt(ID_INDEX) == PLUS_2 || card.charAt(ID_INDEX) == PLUS_4)
              && !(top.charAt(ID_INDEX) == PLUS_2 || top.charAt(ID_INDEX) == PLUS_4)) return false;
    }
//checks if the previous card is a special card
    for(String card: cards.trim().split(" ")) {
      if(card.charAt(ID_INDEX) == FARBWAHL || card.charAt(ID_INDEX) == PLUS_2 || card.charAt(ID_INDEX) == PLUS_4) return true;
    }

    return false;
  }

//used to change the black colour to the speciefied one
  static String changeColor(String card, String color) {
    return card.replace(SCHWARZ,color.charAt(0));
  }
//counts the number of points in the round
  static int points(String cards) {
    int points = 0;
    if(cards.equals("")) return 0;
    for(String card: cards.trim().split(" ")) {
      switch(card.charAt(ID_INDEX)) {
        case PLUS_2:case AUSSETZEN: points += 10; break;
        case PLUS_4:case FARBWAHL: points += 20; break;
        default: points += Character.getNumericValue(card.charAt(ID_INDEX)); break;
      }
    }
    return points;
  }

//artificial intelligence of the computer
//pretty damn hard to win
  static String compute(String cards, String top) {
    int k = 0;
    int best = k;
    for (String card: cards.trim().split(" ")) {
      k++;
      //looks if it's possible to put a PLUS_2 or PLUS_4
      if(check(card, top) && (card.charAt(ID_INDEX) == PLUS_2 || card.charAt(ID_INDEX) == PLUS_4)) {
        if(count(cards) == 2) return "u" + k;
        return Integer.toString(k);
      }
      //if not the previous cards, then try to put the 'AUSSETZEN' card on top
      if(check(card, top) && card.charAt(ID_INDEX) == AUSSETZEN) {
        if(count(cards) == 2) return "u" + k;
        return Integer.toString(k);
      }
      //if not then just a normal card
      if(check(card, top)) best = k;
    }
    if(count(cards) == 2) return "u" + best;
    return Integer.toString(best);
  }

  public static void main (String[] args) {

    int playerOnePoints = 0, playerTwoPoints = 0;

    boolean playerOne, more, isComputer = false;

    System.out.print("Against a computer? (y/n)  ");
    String answer;
    do{
      answer = scanner.next();
    } while(!answer.matches("[yn]") && answer.length() != 1);
    if(answer.equals("y")) isComputer = true;
//begin of the game loop
    do {
      String player1 = startStapel(),
              player2 = startStapel();
      playerOne = true;
      int cardsToTake = 0;
      String top = zieheKarte();
//following conditions used to take measures if the top card is a special one
      System.out.println("Top of the stack: " + karte(top));
      if(top.charAt(ID_INDEX) == PLUS_2) cardsToTake += 2;
      if(top.charAt(ID_INDEX) == PLUS_4) cardsToTake += 4;
      if(top.charAt(ID_INDEX) == AUSSETZEN) {
        System.out.println("Player one passes his turn");
        playerOne = !playerOne;
      }
      if(top.charAt(ID_INDEX) == FARBWAHL || top.charAt(ID_INDEX) == PLUS_4) {
        System.out.println("Player one:");
        top = changeColor(top, read(3, true));
      }

      while (count(player1) > 0 && count(player2) > 0) {

        if (playerOne) {

          System.out.print("Player one is next..\nPick your card\n" + kartenstapelLesbar(player1));
          System.out.println("Top of the stack: " + karte(top));
//if the previously put card is PLUS_2 or PLUS_4 and the player can't counter it
          if ((top.charAt(ID_INDEX) == PLUS_2 || top.charAt(ID_INDEX) == PLUS_4) && cardsToTake > 0) {
            if (!specialCheck(player1)) {
              System.out.println("Player one has to take " + cardsToTake + " cards");
              for (int i = 0; i < cardsToTake; i++)
                player1 = add(player1, zieheKarte());
              cardsToTake = 0;
              playerOne = !playerOne;
              continue;
            }
          }

//if it's possible for the player to make a move
          if (possible(player1, top)) {

            String card = read(count(player1), false);
            int cardInt = evaluate(card);
            String karte = player1.substring(cardInt * 3, cardInt * 3 + KARTENLAENGE);
//if the player hat to put a special card to counter the top one, but didn't do it
//then he has to take 'cardsToTake' cards, that have accumulated during the previous move
//by each of the players countering the PLUS_2 and PLUS_4 cards
            if ((top.charAt(ID_INDEX) == PLUS_2 || top.charAt(ID_INDEX) == PLUS_4)
                    && !(karte.charAt(ID_INDEX) == PLUS_2 || karte.charAt(ID_INDEX) == PLUS_4) && cardsToTake > 0) {
              System.out.println("Player one has to take " + cardsToTake + " cards");
              for (int i = 0; i < cardsToTake; i++)
                player1 = add(player1, zieheKarte());
              cardsToTake = 0;
              System.out.println("Wrong card!");
              playerOne = !playerOne;
              continue;
            }

            if (karte.charAt(ID_INDEX) == PLUS_2 && check(karte, top)) cardsToTake += 2;
            if (karte.charAt(ID_INDEX) == PLUS_4 && isAllowedBlack(player1, top)) cardsToTake += 4;

            if (karte.charAt(ID_INDEX) == FARBWAHL || karte.charAt(ID_INDEX) == PLUS_4)
              karte = changeColor(karte, read(3, true));

            if (karte.charAt(ID_INDEX) == AUSSETZEN && check(karte, top)) {
              System.out.println("Player two passes his move");
              playerOne = !playerOne;
            }

            player1 = remove(player1, card, top);
            if (check(karte, top)) top = karte;
//check if the player didn't forget to say uno
            if(count(player1) == 1 && card.charAt(0) != 'u') {
              System.out.println("You forgot to say uno!\nTake a card");
              player1 = add(player1, zieheKarte());
            }

          } else {
            System.out.println("No possible move, take a card");
            player1 = add(player1, zieheKarte());
          }

          playerOne = !playerOne;

        } else {
//all the same conditions for the second player, wheather he's a player or a computer
          System.out.print("Player two is next..\nPick your card\n" + kartenstapelLesbar(player2));
          System.out.println("Top of the stack: " + karte(top));

          if ((top.charAt(ID_INDEX) == PLUS_2 || top.charAt(ID_INDEX) == PLUS_4) && cardsToTake > 0) {
            if (!specialCheck(player2)) {
              System.out.println("Player two has to take " + cardsToTake + " cards");
              for (int i = 0; i < cardsToTake; i++)
                player2 = add(player2, zieheKarte());
              cardsToTake = 0;
              playerOne = !playerOne;
              continue;
            }
          }

          if (possible(player2, top)) {
            String card;
            if(!isComputer) card = read(count(player2), false);
            else card = compute(player2, top);

            int cardInt = evaluate(card);
            String karte = player2.substring(cardInt * 3, cardInt * 3 + KARTENLAENGE);

            if ((top.charAt(ID_INDEX) == PLUS_2 || top.charAt(ID_INDEX) == PLUS_4)
                    && !(karte.charAt(ID_INDEX) == PLUS_2 || karte.charAt(ID_INDEX) == PLUS_4) && cardsToTake > 0) {
              System.out.println("Player one has to take " + cardsToTake + " cards");
              for (int i = 0; i < cardsToTake; i++)
                player2 = add(player2, zieheKarte());
              cardsToTake = 0;
              playerOne = !playerOne;
              continue;
            }

            if (karte.charAt(ID_INDEX) == PLUS_2 && check(karte, top)) cardsToTake += 2;
            if (karte.charAt(ID_INDEX) == PLUS_4 && isAllowedBlack(player2, top)) cardsToTake += 4;

            if (karte.charAt(ID_INDEX) == FARBWAHL || karte.charAt(ID_INDEX) == PLUS_4) {
              if(!isComputer) karte = changeColor(karte, read(3, true));
              else karte = karte.replace(SCHWARZ,player2.trim().split(" ")[0].charAt(FARBE_INDEX));
            }

            if (karte.charAt(ID_INDEX) == AUSSETZEN && check(karte, top)) {
              System.out.println("Player one passes his move");
              playerOne = !playerOne;
            }

            player2 = remove(player2, card, top);
            if (check(karte, top)) top = karte;

            if(count(player2) == 1 && card.charAt(0) != 'u') {
              System.out.println("You forgot to say uno!\nTake a card");
              player2 = add(player2, zieheKarte());
            }

          } else {
            System.out.println("No possible move, take a card");
            player2 = add(player2, zieheKarte());
          }

          playerOne = !playerOne;

        }

        System.out.println();
      }
      //counting the points for the last round
      playerOnePoints += points(player2);
      playerTwoPoints += points(player1);

      System.out.print("Do you want to end the game?(y/n)\t ");

      do{
        answer = scanner.next();
      } while(!answer.matches("[yn]") || answer.length() != 1);

      more = false;
      if(answer.equals("n")) more = true;

    } while(more);

    System.out.println("Player one : " + playerOnePoints + "\nPlayer two : " + playerTwoPoints);

    if(playerOnePoints > playerTwoPoints) System.out.println("Congratulations player one, you won!");
    else if(playerOnePoints < playerTwoPoints) System.out.println("Congratulations player two, you won!");
    else System.out.println("It's a tie!!!");
  }
}
