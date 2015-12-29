import java.util.LinkedList;

/**
 * Created by yuriyarabskyy on 08/12/15.
 */
public class BinaereSucheClient {

    public static void main(String[] args) {
        BinaereSuche binaereSuche = new BinaereSuche();
        //Books...
        LinkedList<Book> books = new LinkedList<>();

        books.add(new Book("George Orwell", "1984"));
        books.add(new Book("Mark Twain", "Adventures of Huckleberry Finn"));
        books.add(new Book("James Joyce", "Ulysses"));
        books.add(new Book("Fitzerald", "Great Gatsby"));
        books.add(new Book("George Orwell", "Something else"));
        books.add(new Book("Franz Kafka", "Metamorphosis"));

        //testing the sorting
        Book.sort(books);

        System.out.println(binaereSuche.binarySearch(books, new Book("George Orwell", "1984")));

        //Integers..
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(7);
        System.out.print(binaereSuche.binarySearch(list, 2));
    }

}
