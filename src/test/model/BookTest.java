package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookTest {


    @Test
    void Bookstore() {
        String title = "Meditations";
        String author = "Marcus Aurelius";
        String summary = "Marcus Aurelius was a Roman emperor, and this is his journal.";

        Book book1 = new Book(title, author, summary);

        assertEquals(title, book1.getName());
        assertEquals(author, book1.getAuthor());
        assertEquals(summary, book1.getSummary());


    }

    @Test
    void setSummaryTest(){

        String title = "Meditations";
        String author = "Marcus Aurelius";
        String summary = "Marcus Aurelius was a Roman emperor, and this is his journal.";

        Book book1 = new Book(title, author, summary);

        String edited ="Marcus Aurelius was a Roman emperor, and this is his journal " +
                "He also fought Germania.";
        book1.setSummary(edited);

        assertEquals(book1.getSummary(), edited);


    }

}