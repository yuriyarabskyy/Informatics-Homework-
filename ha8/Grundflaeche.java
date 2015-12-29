/**
 * Created by yuriyarabskyy on 11/12/15.
 */
abstract class Grundflaeche {

    abstract double umfang();

    abstract double flaeche();

    //standard value for this method is false
    public boolean istQuadrat() { return false; }

    public Quadrat zuQuadrat() {
        return null;
    }

@Override
    public String toString() {
        String output = String.format("Umfang: \t%,5.2f\nFlaeche: \t%,5.2f", umfang(), flaeche());
        return output;
    }

}
