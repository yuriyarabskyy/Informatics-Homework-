/**
 * Created by yuriyarabskyy on 11/12/15.
 */
public class Kreis extends Grundflaeche {

    private double radius;

    public double getRadius() { return radius; }

    Kreis(double radius) { this.radius = radius; }

    @Override
    public double umfang() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double flaeche() {
        return Math.PI * Math.pow(getRadius(), 2);
    }

    @Override
    public String toString() {
        return "Grundflaeche: \tKreis\n" + super.toString();
    }

}
