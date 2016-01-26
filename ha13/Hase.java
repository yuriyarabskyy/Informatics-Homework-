import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yuriyarabskyy on 26/01/16.
 */
public class Hase extends Thread {

    public static List<Hase> hasenPopulation = new LinkedList<>();

    public static List<String> males    = new LinkedList<>();
    public static List<String> females  = new LinkedList<>();

    public static int nahrung = 2000000000;
    public static int anfang  = 2;

    int maxPopulation = 0;
    public static int maleCount = 0;
    public static int femaleCount = 0;

    public int age = 0;
    public int doomsDay = (int)(Math.random() * 400);
    //0: males, 1: female
    public int sex;
    public String name;
    public int hoursTillEat = 0;
    public boolean dead = false;

    public Hase() {

        if ((int)(Math.random() * 100) + 1 > 55) sex = 0;
        else    sex = 1;

        if (sex == 0) {
            name = males.get((int) (Math.random() * males.size()));
            maleCount++;
        }
        else {
            name = females.get((int) (Math.random() * females.size()));
            femaleCount++;
        }

        System.out.println(name + " \twas born");

        if (maxPopulation < maleCount + femaleCount) maxPopulation = maleCount + femaleCount;

        start();

    }

    public void run() {

        int daysTillSex = (int)(Math.random()*30) + 24;
        int daysTillBirth = (int)(Math.random()*15) + 24;

        int time = 0;

        while (age < doomsDay) {

            try {
                Thread.sleep(1);
            } catch (Exception e) { e.printStackTrace(); }

            //controlling the time
            time++;

            if (time % 24 == 0) {
                if (daysTillSex > 0)
                daysTillSex--;
                age++;
            }

            //eating or dying
            hoursTillEat--;
            if (hoursTillEat <= 0) eat();

            if(dead) break;

            if (daysTillSex <= 0 && daysTillBirth > 0 && maleCount > 0 && femaleCount > 0 && time % 24 == 0) {
                daysTillBirth--;
            }

            if (daysTillSex <= 0 && daysTillBirth <= 0) {
                daysTillSex = (int)(Math.random()*30) + 12;
                daysTillBirth = (int)(Math.random()*15) + 25;
                hasenPopulation.add(new Hase());
            }

        }

        if (sex == 0) maleCount--;
        else femaleCount--;
        System.out.println(name + " \tjust fucking died");

    }

    private synchronized void eat() {
        if (nahrung > 1)
            nahrung--;
        else
            dead = true;
        hoursTillEat += 5;

    //    if (!dead) System.out.println(name + " \tjust ate");

    }

    public static void main(String[] args) throws IOException {

        String[] sex = { "src/males.txt", "src/females.txt" };

        for (int i = 0; i < 2; i++) {

            BufferedReader fileReader = new BufferedReader(new FileReader(new File(sex[i])));

            String line;

            while ((line = fileReader.readLine()) != null) {

                if (i == 0)
                    males.add(line);
                else
                    females.add(line);

            }

            fileReader.close();

        }

        for (int i = 0; i < anfang; i++) {
            hasenPopulation.add(new Hase());
        }


    }

}
