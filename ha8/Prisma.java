/**
 * Created by yuriyarabskyy on 11/12/15.
 */
public class Prisma {

    private final Grundflaeche grundflaeche;
    private final double hoehe;

    Prisma(Grundflaeche grundflaeche, double h) {
        this.grundflaeche = grundflaeche;
        hoehe = h;
    }

    public double volumen() {
        return grundflaeche.flaeche() * hoehe;
    }

    public double oberflaeche() {
        return grundflaeche.flaeche() + grundflaeche.umfang() * hoehe;
    }

    @Override
    public String toString() {
        return String.format("Prisma\nVolumen: \t%,5.2f\nOberflaeche: \t%,5.2f\n",volumen(),oberflaeche())
                + grundflaeche.toString();
    }

    public boolean istWuerfel() {
        if(grundflaeche.istQuadrat() &&
                grundflaeche.zuQuadrat().getSeitenLaenge() == hoehe) return true;
        return false;
    }

}
