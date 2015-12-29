public class Name {

    // Contains fatal errors!!

    private String vorname;
    private String nachname;

    public void Name(String vorname, String nachname) {
        vorname = vorname;
        String nachname = nachname;
    }

    public void setVorname(String newName) {
        String neuerName = newName;
    }

    public void setNachname(String newName) {
        this.nachname = newName;
    }

    public static String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    private void print()
    {
        System.out.println("Hallo, ich bin " + this.toString() + ".");
    }

    public void printReversed()
    {
        System.out.println("Hallo, ich bin " + this.getNachname() + ", " + this.getVorname() + ".");
    }

    public boolean equals(Name second) {
        if (!second.getNachname().equals(nachname)) return false;
        if (!second.getVorname().equals(vorname)) return false;
        return true;
    }

    public String toString() {
        return this.getVorname() + " " + this.getNachname();
    }

}
