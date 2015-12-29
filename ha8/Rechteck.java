/**
 * Created by yuriyarabskyy on 11/12/15.
 */
public class Rechteck extends Grundflaeche {

    private double breite, laenge;

    public double getBreite() { return breite; }
    public double getLaenge() { return laenge; }

    Rechteck(double breite, double laenge) {
        this.breite = breite;
        this.laenge = laenge;
    }

    @Override
    public double umfang() {
        return 2 * (breite + laenge);
    }

    @Override
    public double flaeche() {
        return breite * laenge;
    }

    @Override
    public String toString() {
        return "Grundflaeche: \tRechteck\n" + super.toString();
    }

    @Override
    public boolean istQuadrat() {
        if(breite == laenge) return true;
        return false;
    }

    @Override
    public Quadrat zuQuadrat() {
        if(istQuadrat()) return new Quadrat(breite);
        return null;
    }
}
