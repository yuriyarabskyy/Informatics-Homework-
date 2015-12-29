/**
 * Created by yuriyarabskyy on 14/12/15.
 */
public class Arrays {

    public static void minUndMax(int[] feld) {
        //zum Anfang ist das erste Element min und max
        int min = feld[0],
                max = min;
        //jetzt suchen wir nach den richtigen Werten
        for (int i = 0; i < feld.length; i++) {
            if(feld[i] < min) min = feld[i];
            if(feld[i] > max) max = feld[i];
        }

        System.out.println("Min: \t" + min + "\nMax: \t" + max);

    }

    public static int[] invertieren(int[] feld) {

        int[] inverted = new int[feld.length];
        //wir gehen vom Ende zum Anfang
        for (int i = 0; i < feld.length; i++) {
            inverted[i] = feld[feld.length - i - 1];
        }

        return inverted;

    }

    public static int[] schneiden(int[] feld, int laenge) {

        int[] res = new int[laenge];

        int i;

        for(i = 0; i < laenge; i++) {
            //wenn laenger groesser ist als feld.length, brechen wir die Schleife ab
            //und fuellen danach den restlichen Platz mit Nullen auf
            if(i == feld.length) break;

            res[i] = feld[i];

        }

        while (i < laenge) {
            res[i] = 0;
            i++;
        }

        return res;

    }

    public static int[] linearisieren(int[][] feld) {

        int length = 0;
        //da die Laenge der zweiten Dimension nicht immer gleich ist
        //berechne ich die gesamte Anzahl von Elementen indem ich
        //durch jede Zeile iteriere und ihre Anzahlen summiere
        for (int i = 0; i < feld.length; i++) {
            length += feld[i].length;
        }

        int[] res = new int[length];

        int counter = 0;

        for (int i = 0; i < feld.length; i++)
            for (int j = 0; j < feld[i].length; j++)
                res[counter++] = feld[i][j];

        return res;

    }

    public static void print(int[] feld) {
        for(int i = 0; i < feld.length; i++)
            System.out.print(feld[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {

        int[] feld = {2, 3, 1, 5, -1, 10};

        minUndMax(feld);

        int[] newFeld = schneiden(feld, 10);
        print(newFeld);

        newFeld = invertieren(feld);
        print(newFeld);

        int[][] twoD = {{1},{2,3},{4,5,6}};
        newFeld = linearisieren(twoD);
        print(newFeld);
    }

}
