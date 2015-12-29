import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

public class GuessTheNumber {

   private static int zuRatendeZahl; // zu ratende Zahl
   private static boolean neugierig; // wurde zuletzt eine 0 eingegeben?
                                     // (siehe Aufgabenstellung)
   private static LinkedList<Integer> gerateneZahlen; // schon geratene Zahlen

   // Properties zum Laden / Speichern.
   private static Properties spielstand;


   // Startzustand herstellen.
   private static void init() {
      // Noch keine geratenen Zahlen.
      gerateneZahlen = new LinkedList<Integer>();

      // Zu ratende Zahl festlegen.
      zahlNeuSetzen(1,7);

      // Properties zum Laden / Speichern.
      spielstand = new Properties();

      // Vermerken, dass Spielerin noch nicht neugierig war,
      // d.h. es wurde nicht zuletzt eine 0 eingegeben, um
      // sich den Spielstand anzusehen.
      neugierig = false;
   }

   // Setzt die Klassenvariable zuRatendeZahl auf einen zufaelligen Wert
   // zwischen von und bis (jeweils inklusive).
   private static void zahlNeuSetzen(int von, int bis){
      zuRatendeZahl = reinerZufall(von, bis);
   }

   private static void optionenAusgeben(){
      // Optionen ausgeben.
      System.out.println("Raten Sie eine Zahl zwischen 1 und 7.\n"
          + " Geben Sie 0 ein, um sich die schon geratenen Zahlen anzeigen zu lassen.\n"
          + " Geben Sie erneut 0 ein, um die zu ratende Zahl zu sehen.\n"
          + " Geben Sie 8 ein, um das aktuelle Spiel zu speichern.\n"
          + " Geben Sie 9 ein, um ein gespeicherter Spiel fortzusetzen.\n");
   }

   public static void spiele() {
      // Startbedingungen herstellen.
      init();

      // Optionen ausgeben.
      optionenAusgeben();

      while (true) {
         // Zahl / Befehl einlesen.
         int eingabe = new java.util.Scanner(System.in).nextInt();

         switch (eingabe){
            case -1:
               System.out.println("Abbruch");
               return;
               //break;
            case 0:
               if (neugierig) {
                  zahlAnzeigen();
               } else {
                  geratenAnzeigen();
               }
               break;
            case 8:
               speichern();
               break;
            case 9:
               laden();
               break;
            case 1: case 2: case 3: case 4: case 5: case 6: case 7:
               if (eingabe == zuRatendeZahl) {
                  System.out.println("Richtig geraten. Sie sind gut!");
                  return;
               } else {
                  System.out.println("Falsch geraten.");
                  if (!gerateneZahlen.contains(eingabe))
                     gerateneZahlen.add(eingabe);
               }
               break;
            default: optionenAusgeben();
         }
         // Merken, ob zuletzt 0 eingegeben wurde.
         if (eingabe != 9) // Falls nicht neuer Zustand eingelesen wurde.
            neugierig = (eingabe == 0);
      }
   }

   private static void zahlAnzeigen(){
      System.out.println("Die Zahl ist die " + zuRatendeZahl + ".");
   }

   private static void geratenAnzeigen(){
      System.out.println("Bereits geratene Zahlen:");
      for (int i : gerateneZahlen) {
         System.out.print(i + ", ");
      }
      System.out.println();
   }

   public static void main(String[] args) {
      System.out.println("Das Spiel beginnt...");
      spiele();
   }

   private static void speichern(){
      System.out.println("Speichern. Dateiname eingeben: ");
      String dateiname = new java.util.Scanner(System.in).nextLine();
      System.out.println("\n  Noch nicht implementiert, Spiel"
         + " wurde nicht gespeichert.");
   }

   private static void laden(){
      System.out.println("Laden. Dateiname eingeben: ");
      String dateiname = new java.util.Scanner(System.in).nextLine();
      System.out.println("\n  Noch nicht implementiert, Spiel"
         + " wurde nicht geladen.");
   }

   // Liefert eine ZufallszuRatendeZahl zwischen von und bis (jeweils inklusive).
   private static int reinerZufall(int von, int bis) {
      return (int) (Math.random() * (bis + 1 - von) + von);
   }
}