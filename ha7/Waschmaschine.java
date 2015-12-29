import java.util.LinkedList;

/**
 * Created by yuriyarabskyy on 01/12/15.
 */
public class Waschmaschine {

    public static LinkedList<Waschmaschine> katalog;
//creating a library of standard washing machines
    static {
        katalog = new LinkedList<>();
        katalog.add(new Waschmaschine("Muehle", 81, 8, false));
        katalog.add(new Waschmaschine("Busch", 12, 9, true));
        katalog.add(new Waschmaschine("Gruendlich", 91, 10, false));
    }

    private String marke;

    private int sockenAnzahl = 0, preis = 0, maxSockenAnzahl = 0, muenzenDrin = 0;
    private LinkedList<Muenze> rest;
    private LinkedList<Socke> socken = new LinkedList<>();
    //for Gruendlich, because some socks can get lost in there
    private LinkedList<Socke> lostSocks = new LinkedList<>();

    //ist der Preis fuer jede Socke oder pro Waschgang
    private boolean isPreisProSocke = true;
    //take the rest after the washing is done
    public LinkedList<Muenze> takeRest() {
        LinkedList<Muenze> res = rest;
        rest = null;
        muenzenDrin = 0;
        return res;
    }
    //take out the socks after the washing
    public LinkedList<Socke> takeSocken() {
        LinkedList<Socke> res;
        sockenAnzahl = 0;
        res = socken;
        socken = new LinkedList<>();
        return res;
    }

    public int getMuenzenDrin() { return muenzenDrin; }

    public void addMuenzen(int muenze) { muenzenDrin += muenze; }

    public int getSockenAnzahl() { return sockenAnzahl; }

    public int getPreis() { return preis; }

    public int getMaxSockenAnzahl() { return maxSockenAnzahl; }

    public String getMarke() { return marke; }

    public void addSocke(Socke socke) {
        if(maxSockenAnzahl == sockenAnzahl) {
            System.out.println("Nicht genug Platz fuer die Socke");
            return;
        }
        socken.add(socke);
        sockenAnzahl++;
    }

    public void addSocken(LinkedList<Socke> sockenList) {
        int i = 0;
        while(maxSockenAnzahl != sockenAnzahl) {
            socken.add(sockenList.get(i));
            sockenAnzahl++;
            i++;
        }
        if(sockenList.size() > i) System.out.println("Nicht genug Platz fuer die Socke");
    }

    public void waschen() {

        System.out.println(marke + " is washing");

        if(muenzenDrin < preis) {
            System.out.println("Nicht genug geld dadrin");
            return;
        }

        int toPay;

        if(isPreisProSocke) {
            toPay = preis * sockenAnzahl;
        } else toPay = preis;

        rest = (new Muenze(muenzenDrin - toPay)).wechsel();

        System.out.println("Ihr Restgeld: " + rest);
        System.out.println("Vergessen Sie nicht Ihr Restgeld zu nehmen");
        //calculating the average Grauwert and setting it to all washed socks
        double averageGrau = 0;

        for(int i = 0; i < socken.size(); i++) averageGrau += socken.get(i).getGrauwert();

        averageGrau /= socken.size();

        for(Socke socke : socken) {
            socke.setGrauwert(averageGrau);
            socke.setGroesse(socke.getGroesse() - 1);
        }

        //special fall for Gruendlich
        if(marke == "Gruendlich") {

            //setting the correct grauwert, to be continued in the next loops
            averageGrau *= socken.size();

            for(Socke socke : lostSocks) {
                socke.setGroesse(socke.getGroesse() - 1);
                //removing socks which are smaller than 2
                if(socke.getGrauwert() < 2) lostSocks.remove(socke);

                //continuing with the setting of the grauwert
                averageGrau += socke.getGrauwert();
            }

            averageGrau /= (socken.size() + lostSocks.size());

            for(Socke socke : socken) {
                socke.setGrauwert(averageGrau);
            }

            //15% possibility
            boolean lost = false;
            if((int)(Math.random()*100) < 15) lost = true;

            if(lost) {
                lostSocks.add(socken.get((int)(Math.random()*socken.size())));
                System.out.println("Eine Socke wurde verloren in der Waschmaschine Gruendlich");
            }

        }

        System.out.println("Grauwert aller gewaschten Socken wurde zu " + averageGrau + " geaendert" +
                " und ihre Groesse wurde um eins verkleinert");

        muenzenDrin -= toPay;

    }

//constructor, which looks after the name of the washing machine from the library
    Waschmaschine(String marke) {
        this.marke = marke;
        for(Waschmaschine waschmaschine : katalog) {
            if(waschmaschine.marke.equals(marke)) {
                this.preis = waschmaschine.preis;
                this.maxSockenAnzahl = waschmaschine.maxSockenAnzahl;
                this.isPreisProSocke = waschmaschine.isPreisProSocke;
                break;
            }
        }
    }
    //for constructing new models
    //I use the last parameter to tell me if the price is pro washing session or pro sock
    Waschmaschine(String marke, int preis, int maxSockenAnzahl, boolean isPreisProSocke) {
        this.marke = marke;
        this.preis = preis;
        this.maxSockenAnzahl = maxSockenAnzahl;
        this.isPreisProSocke = isPreisProSocke;
    }

    @Override
    public String toString() {
        String output = "\nMarke: " + marke + "\n" + sockenAnzahl + " Socken drin\n" +
                muenzenDrin + " Muenzen drin \n" + maxSockenAnzahl + " max Sockenanzahl pro ";
        if(isPreisProSocke) output += "Socke";
        else output += "Waschgang";
        return output + "\n";
    }

}
