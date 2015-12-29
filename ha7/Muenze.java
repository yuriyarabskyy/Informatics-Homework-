import java.util.LinkedList;

/**
 * Created by yuriyarabskyy on 01/12/15.
 */
public class Muenze {
//existing coins
    public static final int EINS = 1;
    public static final int FUENF = 5;
    public static final int ZEHN = 10;
    public static final int ZWANZIG = 20;
    public static final int FUENFZIG = 50;
//value of the coin
    private final int wert;

    private final int[] moeglicheWerte = {EINS, FUENF, ZEHN, ZWANZIG, FUENFZIG};
    //the getter method checks if the value of the coin exists
    //if it doesn't it returns -1
    public int getWert() {
        //check if the value is possible
        boolean passend = false;

        for(int x : moeglicheWerte) {

            if(x == wert) {
                passend = true;
                break;
            }

        }

        if(!passend) {
            System.out.println("Solche Muenze existiert nicht");
            return -1;
        }

        return  wert;
    }

    //constructor doesn't check for the correct value, because I found it practical
    //to use the wechsel() method from this class to give out das Restgeld
    //and in this situation the value is sometimes going to be the sum of the coins
    Muenze(int wert) {
        this.wert = wert;
    }

    @Override
    public String toString() { return Integer.toString(this.getWert()); }

    public LinkedList<Muenze> wechsel() {

        LinkedList<Muenze> list = new LinkedList<>();

        int typ = moeglicheWerte.length;

        int temporalerWert = this.wert;
        //looking for the coins type
        for(int i = 0; i < moeglicheWerte.length; i++) {

            if(moeglicheWerte[i] == temporalerWert) {

                typ = i;

                break;

            }

        }
        //if type = 0 then the valueof the coin is 1, so just return it
        if(typ == 0) {
            list.add(this);
            return list;
        }


        while(temporalerWert > 0) {

            typ--;
            //start giving back the rest with the next biggest coin type
            int n = temporalerWert / moeglicheWerte[typ];

            for(int i = 0; i < n; i++) list.add(new Muenze(moeglicheWerte[typ]));

            temporalerWert -= n * moeglicheWerte[typ];

        }

        return list;

    }

    public static void main(String[] args) {

        Muenze coin = new Muenze(20);

        System.out.print(coin.wechsel());

    }

}
