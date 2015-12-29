
import java.util.LinkedList;

/**
 * Created by yuriyarabskyy on 01/12/15.
 */
public class Book implements Comparable {

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

        //if title the same, then compare titles
        if(this.getTitle().equals(other.getTitle())) return this.getAuthor().compareTo(other.getAuthor());

        else return this.getTitle().compareTo(other.getTitle());

    }

    public int compareTo(Object book) {

        if(this == book) return 0;

        Book other = (Book) book;

        return compareTo(other);
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

}
