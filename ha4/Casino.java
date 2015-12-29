
import java.util.Random;
import java.util.Scanner;

public class Casino{
  static Random  random = new Random();
  static Scanner input = new Scanner(System.in);
  static String  spieler, dealer;     //hier werden die Karten als Stringzahlen mit Leerzeichen gespeichert
  static boolean isSpielerDran;
  static int     geld;
  static int     einsatz;

  static int zieheKarte()
  {
    return random.nextInt(14);
  }

  static String kartenbeschriftung(int kartenID)
  {
    switch(kartenID){
      case 0: return "Ass";
      case 1: return "Eins";
      case 2: return "Zwei";
      case 3: return "Drei";
      case 4: return "Vier";
      case 5: return "Fuenf";
      case 6: return "Sechs";
      case 7: return "Sieben";
      case 8: return "Acht";
      case 9: return "Neun";
      case 10: return "Zehn";
      case 11: return "Bube";
      case 12: return "Dame";
      case 13: return "Koenig";
      default: return "Error";
    }
  }

  static int augenzahl(int kartenID,int count)  //count entspricht der augenzahl ohne Assen
  {
    switch(kartenID){
      case 0: if(isSpielerDran){
        if(count+11<22) return 11;            //die Assen werden erst jetzt addiert in Abhangigkeit davon, ob
        else return 1;                        //die 21 Grenze ueberschritten wird
      }                                       //in der Methode count unten wird es klar, wieso ich das auf solche Weise mache
      else{
        if(count+11<22) return 11;
        else return 1;
      }
      case 11: case 12: case 13: return 10;
      default: return kartenID;
    }
  }

//dummlicherweise hab ich nur diese Methode benutzt, um jegliche Zahlen einzugeben
//deshalb musste ich hier viele verschiedene abfragen machen, wie meine variable nachricht ausschaut
  static int leseZahl(String nachricht)
  {
    System.out.print(nachricht+" ");
    int zahl = 0;
    do{
    try{
      zahl = input.nextInt();
      input.nextLine();
      if(!nachricht.contains("(1/0)")&&((!nachricht.contains("Geld")&&zahl>geld)||zahl<=0))  //ich musste fuer die Abfrage
        throw new java.util.InputMismatchException();        //mit dem Geld erlauben eine beliebig grosse Zahl einzugeben
    }
    catch(java.util.InputMismatchException err){
      zahl = 0;
      System.out.println("Invalid input, try again");
      input.nextLine();
      }
    }while(zahl<=0&&!nachricht.contains("(1/0)"));  //also wenn wir nach 1 oder 0 fragen, dann ist 0 auch eine gueltige Antwort
    return zahl;                                    //Hauptsache es funktioniert und faengt alle invalid inputs ab :)
  }

  static void line()  //malt ein eine Linie damit es schoen aussieht
  {
    for(int i=0;i<50;i++) System.out.print("-");
    System.out.println();
  }

  static void spielStand()  //sagt bescheid welche Karten die Spieler haben und die Summe Ihrer Punkte
  {
    line();
    String spielerKarten = "", dealerKarten = "";
    for(String karte : spieler.split(" ")){
      spielerKarten += kartenbeschriftung(Integer.parseInt(karte)) + "\t ";
    }
    System.out.println("Spieler: "+ spielerKarten + " ==> Summe = \t" + Integer.toString(count(spieler)));
    if(isSpielerDran){  //wenn der Spieler noch dran ist, dann darf nur die erste Karte angezeigt werden
      dealerKarten = kartenbeschriftung(Integer.parseInt(dealer.split(" ")[0])) + "\t UNKNOWN";
      System.out.println("Dealer: "+dealerKarten);
    }
    else{
      for(String karte : dealer.split(" ")){
        dealerKarten += kartenbeschriftung(Integer.parseInt(karte)) + "\t ";
      }
        System.out.println("Dealer: "+dealerKarten + " ==> Summe = \t" + Integer.toString(count(dealer)));
    }
    line();
  }

  static void gameStart()
  {
    for(int i=0;i<2;i++){
      spieler += Integer.toString(zieheKarte())+" ";
      dealer += Integer.toString(zieheKarte())+" ";
    }
  }

  static void reset()
  {
    spieler = dealer = "";
    isSpielerDran = true;
  }

  static void weiterZiehen()
  {
    int karte = zieheKarte();
    if(isSpielerDran){
      spieler += Integer.toString(karte) + " ";
      System.out.println("Sie haben "+kartenbeschriftung(karte)+" gezogen");
    }
    else{
      dealer += Integer.toString(karte) + " ";
      System.out.println("Dealer hat "+kartenbeschriftung(karte)+" gezogen");
    }
  }

//sagt uns wie viele Punkte das gegebene Set von Karten ergibt
//alle Karten ausser Ass werden einfach dazuaddiert
//die Assen werden beim Durchlauf gezaehlt und erst am Ende dazuaddiert
//es wird fuer sie berechnet, ob sie als 1 oder als 11 gelten
  static int count(String cards)
  {
    String[] cards_array = cards.split(" ");
    int count = 0;
    int ass_count = 0;
    for(int i=0;i<cards_array.length;i++){
      int n = Integer.parseInt(cards_array[i]);
      if(n==0){
        ass_count++;
        continue;
      }
      count += augenzahl(n,count);
    }
    while(ass_count>0){
      count += augenzahl(0,count);
      ass_count--;
    }
    return count;
  }

//Dealer is dann dran
  static void change()
  {
    isSpielerDran = false;
    System.out.println("Dealer ist jetzt dran..");
  }

  static boolean check_if_ende(){
    if(isSpielerDran){
      if(count(spieler)>21){
        geld -= einsatz;
        System.out.println("Sie haben "+einsatz+" Euro verloren\n");
        return true;
      }
    }
    else{
      if(count(dealer)>21||count(spieler)>count(dealer)){
        geld += einsatz-(int)(0.25*einsatz);  //damit es fuer den Spieler in die bessere Seite abgerundet wird
        System.out.println("Sie haben gewonnen");
      }
      else if(count(dealer)>count(spieler)){
        geld -= einsatz;
        System.out.println("Sie haben "+einsatz+" Euro verloren");
      }
      else{
        System.out.println("Unentschieden");
      }
      return true;
    }
    return false;
  }

  static void enter(){
    System.out.print("Press Enter..");
    input.nextLine();
  }

  public static void main(String[] args)
  {
    geld = leseZahl("Wie viel Geld moechten Sie verspielen?");
    while(geld>0){
      System.out.println("Ihr Geld ---> "+geld);
      reset();
      einsatz = leseZahl("Wie viel setzen Sie in dieser Runde ein?");
      gameStart();
      spielStand();
      while(count(spieler)<21&&leseZahl("Weiterziehen? (1/0)")>0){
        line();
        weiterZiehen();
        spielStand();
      }
      if(check_if_ende()) continue;
      change();
      spielStand();
      while(count(dealer)<17){
        enter();
        line();
        weiterZiehen();
        spielStand();
      }
      enter();
      check_if_ende();
      enter();
      line();
    }
    System.out.println("Das Spiel ist vorbei..");
  }
}
