package persistence;

import model.Book;
import model.BookShelf;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {
    @Test
    void testWriterNonExistentFile() {
        try {
            BookShelf bookShelf = new BookShelf();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterGeneralBookshelf() {
        try {
            BookShelf bookShelf = new BookShelf();
            bookShelf.addBook(new Book("a", "b", "c"));
            bookShelf.addBook(new Book("d", "e", "f"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralBookshelf.json");
            writer.open();
            writer.write(bookShelf);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralBookshelf.json");
            bookShelf = reader.read();
            List<Book> books = bookShelf.getBooks();
            assertEquals(2, books.size());
            assertEquals("a", books.get(0).getName());
            assertEquals("b", books.get(0).getAuthor());
            assertEquals("c", books.get(0).getSummary());
            assertEquals("d", books.get(1).getName());
            assertEquals("e", books.get(1).getAuthor());
            assertEquals("f", books.get(1).getSummary());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
