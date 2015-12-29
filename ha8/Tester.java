/**
 * Created by yuriyarabskyy on 11/12/15.
 */
public class Tester {

    public static void main(String[] args) {

        System.out.print("Ist Prisma aus Viereck mit Laenge 5 und hoehe 5 ein Wuerfel? ");
        //pruefe ob Prisma aus einem Viereck als Grundflaeche und gleich langer hoehe ein Wuerfel ist
        System.out.println(new Prisma(new NEck(4, 5), 5).istWuerfel() + "\n");

        //test zwei
        Grundflaeche[] grundflaechen = new Grundflaeche[3];

        grundflaechen[0] = new Kreis(5);
        grundflaechen[1] = new Rechteck(40, 40);
        grundflaechen[2] = new NEck(25, 2);

        for(Grundflaeche g : grundflaechen) System.out.println(g + "\n");

        //test3
        Quadrat quadrat = grundflaechen[1].zuQuadrat();
        System.out.println(quadrat);

        //test4
        quadrat = grundflaechen[2].zuQuadrat();
        System.out.println("\nSoll eine null Referenz sein: " + quadrat + "\n");

        //test5
        Prisma prisma = new Prisma(grundflaechen[0], 10);
        System.out.println(prisma);
    }

}
