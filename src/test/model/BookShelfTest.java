package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BookShelfTest {

    private BookShelf books1;
    private Book book1;
    private Book book2;
    private Book book3;


    @BeforeEach
    void runBefore() {

        books1 = new BookShelf();
        String title1 = "Meditations";
        String author1 = "Marcus Aurelius";
        String summary1 = "Marcus Aurelius was a Roman emperor, and this is his journal.";

        book1 = new Book(title1, author1, summary1);

        String title2 = "The Art of War";
        String author2 = "Sun Tzu";
        String summary2 = "this book teaches us about the different forms of war.";

        book2 = new Book(title2, author2, summary2);

        String title3 = "48 laws of power";
        String author3 = "Robert Green";
        String summary3 = "thirst for power has dominated the world for centuries...";

        book3 = new Book(title3, author3, summary3);

        books1.addBook(book1);
        books1.addBook(book2);
        books1.addBook(book3);

    }

    @Test
    void addBookTest() {

        String title4 = "Maze Runner";
        String author4 = "James Dashner";
        String summary4 = "The story is set in a distant future, where a group of teens are mysteriously" +
                " teleported into a giant, stone maze. ";

        Book newBook = new Book(title4, author4, summary4);
        books1.addBook(newBook);

        assertTrue(books1.getBooks().contains(newBook));
        assertTrue(books1.getBooks().contains(book1));


    }

    @Test
    void removeBookTest() {
        books1.removeBook("Meditations");
        assertFalse(books1.getBooks().contains(book1));
        assertEquals("Book not found!",books1.removeBook("Harry Potter"));

    }

    @Test
    void editSummaryTest() {

        books1.editBookSummary("Meditations","Marcus Aurelius wasn't the greatest roman emperor.");
        assertEquals("Marcus Aurelius wasn't the greatest roman emperor.", book1.getSummary());

        String o = books1.editBookSummary("Harry Potter",
                "Harry wasn't the greatest roman emperor.");
        assertEquals("Book not found!", o);

        books1.editBookSummary("The Art of War", "There are 13 stages of war, siege is the worst." );
        assertEquals("There are 13 stages of war, siege is the worst." , book2.getSummary());

        books1.editBookSummary("48 laws of power", "Never outshine the master.");
        assertEquals("Never outshine the master.", book3.getSummary());


    }

    @Test
    void SortBooksTest() {
        String title1 = "Meditations";
        String title2 = "The Art of War";
        String title3 = "48 laws of power";
        String title4 = "Maze Runner";

        String author4 = "James Dashner";
        String summary4 = "The story is set in a distant future, where a group of teens are mysteriously" +
                " teleported into a giant, stone maze. ";
        Book book4 = new Book(title4, author4, summary4);
        ArrayList<String> sorted = new ArrayList<>();

        sorted.add(title3);
        sorted.add(title4);
        sorted.add(title1);
        sorted.add(title2);

        books1.addBook(book4);
        assertEquals(sorted, books1.sortBooksByTitle());
    }

    @Test
    public void printBooksTest() {
        String title1 = "Meditations";
        String title2 = "The Art of War";
        String title3 = "48 laws of power";
        String title4 = "Maze Runner";

        ArrayList<String> veiw = new ArrayList<>();

        veiw.add(title1);
        veiw.add(title2);
        veiw.add(title3);

        assertEquals(veiw, books1.printBooks());

    }



    @Test
    public void FindBookTest() {


        String output = "Book name: " + "Meditations" + "\n" + "Author: " + "Marcus Aurelius" + "\n"
                + "Summary: " + "Marcus Aurelius was a Roman emperor, and this is his journal.";
        String output1 = "Book name: " + "The Art of War" + "\n" + "Author: " + "Sun Tzu" + "\n"
                + "Summary: " + "this book teaches us about the different forms of war.";
        String output2 = "Book name: " + "48 laws of power" + "\n" + "Author: " + "Robert Green" + "\n"
                + "Summary: " + "thirst for power has dominated the world for centuries...";

        assertEquals(output, books1.findBook("Meditations"));
        assertEquals("Book not found!", books1.findBook("Harry potter"));
        assertEquals(output1, books1.findBook("The Art of War"));
        assertEquals(output2, books1.findBook("48 laws of power"));
        assertEquals("Book not found!",books1.findBook("50 shades of grey"));
    }
}



