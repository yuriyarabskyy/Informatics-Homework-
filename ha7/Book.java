import sun.awt.image.ImageWatched;

import java.util.LinkedList;

/**
 * Created by yuriyarabskyy on 01/12/15.
 */
public class Book {

    //Attributes
    private String title, author;

    //Getters
    public String getTitle() { return title; }

    public String getAuthor() { return author; }

    //Constructor
    Book(String author, String title) {

        this.title = title;

        this.author = author;

    }

    @Override
    public String toString() {
        return author + " -> " + title;
    }

    public int compareTo(Book other) {

        //if author the same, then compare titles
        if(this.getAuthor().equals(other.getAuthor())) return this.getTitle().compareTo(other.getTitle());

        else return this.getAuthor().compareTo(other.getAuthor());

    }

    public static void sort(LinkedList<Book> books) {

        for(int i = 1; i < books.size(); i++) {

            int j = i;

            while(j > 0 && books.get(j).compareTo(books.get(--j)) < 0) swap(books, j, j+1);

        }

    }

    private static void swap(LinkedList<Book> books, int i, int j) {
        Book  temp = books.get(i);
        books.set(i, books.get(j));
        books.set(j, temp);
    }

    public static void main(String[] args) {

        LinkedList<Book> books = new LinkedList<>();

        books.add(new Book("George Orwell", "1984"));
        books.add(new Book("Mark Twain", "The Adventures of Huckleberry Finn"));
        books.add(new Book("James Joyce", "Ulysses"));
        books.add(new Book("Fitzerald", "The Great Gatsby"));
        books.add(new Book("George Orwell", "Something else"));
        books.add(new Book("Franz Kafka", "The Metamorphosis"));

        //testing the getters
        System.out.printf("%s - %s\n", books.get(0).getAuthor(), books.get(0).getTitle());

        //testing the sorting
        Book.sort(books);
        System.out.println(books);

    }

}
