/**
 * Created by yuriyarabskyy on 11/12/15.
 */
public class NEck extends Grundflaeche {

    private int eckAnzahl;
    private double seitenLaenge;

    public int getEckAnzahl() { return eckAnzahl; }
    public double getSeitenLaenge() { return seitenLaenge; }

    NEck(int n, double laenge) {
        eckAnzahl = n;
        seitenLaenge = laenge;
    }

    @Override
    public double umfang() {
        return eckAnzahl * seitenLaenge;
    }

    @Override
    public double flaeche() {
        return (eckAnzahl * Math.pow(seitenLaenge, 2))/(4 * Math.tan(Math.PI / eckAnzahl));
    }

    @Override
    public String toString() {
        return "Grundflaeche: \tn-Eck\n" + super.toString();
    }

    @Override
    public boolean istQuadrat() {
        if(eckAnzahl == 4) return true;
        return false;
    }

    @Override
    public Quadrat zuQuadrat() {
        if(istQuadrat()) return new Quadrat(seitenLaenge);
        return null;
    }

}
