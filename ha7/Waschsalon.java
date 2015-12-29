import java.util.LinkedList;

/**
 * Created by yuriyarabskyy on 01/12/15.
 */
public class Waschsalon {

    private LinkedList<Waschmaschine> maschinenListe = new LinkedList<>();

    public static final int Gruendlich = 0;
    public static final int Muehle = 1;
    public static final int Busch = 2;
//standard washing saloon constructor
    Waschsalon() {
        LinkedList<Waschmaschine> liste = new LinkedList<>();
        liste.add(new Waschmaschine("Gruendlich"));
        liste.add(new Waschmaschine("Muehle"));
        liste.add(new Waschmaschine("Busch"));
        maschinenListe = liste;
    }

    Waschsalon(LinkedList<Waschmaschine> liste) {
        maschinenListe = liste;
    }

    public LinkedList<Waschmaschine> getMaschinenListe() { return maschinenListe; }

    @Override
    public String toString() {
        String output = "";
        for(Waschmaschine machine : maschinenListe) output += machine;
        return output;
    }


    public static void main(String[] args) {

        Waschsalon waschsalon = new Waschsalon();
        //adding coins
        waschsalon.getMaschinenListe().get(Gruendlich).addMuenzen(100);
        //adding socks
        //you can also add lists os socks
        waschsalon.getMaschinenListe().get(Gruendlich).addSocke(new Socke(10,0.2));
        waschsalon.getMaschinenListe().get(Gruendlich).addSocke(new Socke(10,0.8));
        //using getter methods
        //you can also print out the whole waschsalon
        System.out.println(waschsalon.getMaschinenListe().get(Gruendlich));
        //you can wash the socks, which you've added to the machine
        //but be careful with Gruendlich, it can swallow some of your socks
        waschsalon.getMaschinenListe().get(Gruendlich).waschen();
        //after you've washed everything, you have to take the list of socks out and the money, that was left
        LinkedList<Muenze> coins = waschsalon.getMaschinenListe().get(Gruendlich).takeRest();
        LinkedList<Socke> socken = waschsalon.getMaschinenListe().get(Gruendlich).takeSocken();

        System.out.println(waschsalon.getMaschinenListe().get(Gruendlich));

    }

}
