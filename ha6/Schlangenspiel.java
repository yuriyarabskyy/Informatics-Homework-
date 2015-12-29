public class Schlangenspiel {
  public static void main (String[] args) {
    // Positionen der Spielsteine
    int piece1, piece2;
    // Nummer des Spielsteins, der versetzt wird
    int piece;
    // Startpositionen
    piece1 = 0;
    piece2 = 0;

    // Wer ist dran?
    int player;
    player = 1;

    // Wuerfelergebnis
    int dice;
    // Zielfeld
    int field;
    while (piece1 < 35 && piece2 < 35) {
      Spielfeld.paintField(piece1, piece2);
      //Wuerfeln
      dice = Spielfeld.dice();
      piece = 0;
      if (player == 1) {
        Spielfeld.write("Spieler 1 ist dran.\n"
          + "Du hast eine " + dice + " gewuerfelt. ");
        // Auswahl des Spielsteins
        piece = 1;
      } else {
        Spielfeld.write("Spieler 2 ist dran.\n"
          + "Du hast eine " + dice + " gewuerfelt. ");
        // Auswahl des Spielsteins
        piece = 2;
      }
      // Setzen
      field = 0;
      if (piece == 1)
        field = piece1;
      if (piece == 2)
        field = piece2;
      field = field + dice;
      int cont = 1; // Der Stein muss evt. noch versetzt werden.

      if (field < 35) {
        /*
         * Versetzen des aktuellen Spielsteins "piece"
         * solange das Zielfeld "field" eine der folgenden
         * Bedingungen erfuellt:
         * Es ist ein Schlangenfeld.
         * Es ist ein Leiterfeld.
         * Es ist bereits besetzt. (In diesem Fall muss ein
         * anderer Stein versetzt werden.)
         */
        while (cont == 1) {
          cont = 0;
          // Schlangen- und Leiterfelder
          if (field != 0) {
            while (field % 5 == 0 || field % 7 == 0) {
              if (field % 5 == 0) {
                field = field + 3;
                Spielfeld.write("Leiterfeld! Du kommst 3 Felder "
                  + "vor auf Feld " + field + ".");
              }
              if (field % 7 == 0) {
                field = field - 4;
                if (field < 0) {
                  field = 0;
                }
                Spielfeld.write("Schlangenfeld! Du faellst leider 4 "
                  + "Felder zurueck auf Feld " + field + ".");
              }
            }
          }

          // Spielstein setzen
          if (piece == 1)
            piece1 = field;
          if (piece == 2)
            piece2 = field;
          Spielfeld.paintField(piece1, piece2);

          if (field != 0) {
            // Schlagen eines Spielsteins
            if (piece == 1) {// Spielstein von Spieler 1
              // fremder Spielstein auf dem Feld
              if (field == piece2 && field < 35) {
                // neuer Spielstein, der versetzt werden muss
                piece = 2;
                cont = 1;
                field = piece2 - 6;
                if (field < 0) {
                  field = 0;
                }
                Spielfeld.write("Spieler 1 schlaegt Spieler 2. \n"
                  + "Er faellt zurueck auf Feld " + field + ".");
              }
            } else {// Spielstein von Spieler 2
              // fremder Spielstein auf dem Feld
              if (field == piece1 && field < 35) {
                // neuer Spielstein, der versetzt werden muss
                piece = 1;
                cont = 1;
                field = piece1 - 6;
                if (field < 0) {
                  field = 0;
                }
                Spielfeld.write("Spieler 2 schlaegt Spieler 1. \n"
                  + "Er faellt zurueck auf Feld " + field + ".");
              }
            }
          }
        }
      }
      //Spielstein setzen
      if (field == 35) {
        if (piece == 1)
          piece1 = 35;
        if (piece == 2)
          piece2 = 35;
      }
      Spielfeld.paintField(piece1, piece2);

      // Ende des Spielzugs, naechsten Spieler auswaehlen
      if (player == 1) {
        // Noch einmal Wuerfeln bei einer 6
        if (dice == 6) {
          Spielfeld.write("Spieler 1, du hattest eine 6 "
            + "gewuerfelt und darfst noch einmal.");
          player = 1; // ueberfluessig
        } else {
          Spielfeld.write("Spieler 1, du beendest deinen Zug.");
          player = 2;
        }
        field = 0;

      } else {
        // Noch einmal Wuerfeln bei einer 6
        if (dice == 6) {
          Spielfeld.write("Spieler 2, du hattest eine 6 "
            + "gewuerfelt und darfst noch einmal.");
          player = 2; // ueberfluessig
        } else {
          Spielfeld.write("Spieler 2, du beendest deinen Zug.");
          player = 1;
        }
        field = 0;
      }
    }
    // Ende des Spiels
    if (piece1 > 34) {
      Spielfeld.write("Spieler 1 gewinnt!");
    } else {
      Spielfeld.write("Spieler 2 gewinnt!");
    }
  }
}