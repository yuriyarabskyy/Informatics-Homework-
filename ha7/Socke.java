/**
 * Created by yuriyarabskyy on 02/12/15.
 */
public class Socke {

    private int groesse;

    private double grauwert;
//getters
    public int getGroesse() { return groesse; }

    public double getGrauwert() { return grauwert; }
//setters
    public void setGrauwert(double grauwert) { this.grauwert = grauwert; }

    public void setGroesse(int groesse) { this.groesse = groesse; }

    Socke(int groesse, double grauwert) {
        this.groesse = groesse;
        this.grauwert = grauwert;
    }

}
